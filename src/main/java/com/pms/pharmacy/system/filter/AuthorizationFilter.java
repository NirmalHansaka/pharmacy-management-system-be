package com.pms.pharmacy.system.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pms.pharmacy.system.model.RoleSubModuleAction;
import com.pms.pharmacy.system.repository.RoleSubModuleActionRepository;
import com.pms.pharmacy.system.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final RoleSubModuleActionRepository roleSubModuleActionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().equals(Constants.LOGIN_PATH)){
            // If request is for login, do nothing
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length());

                    // verify token
                    JWTVerifier verifier = JWT.require(Constants.JWT_SIGN_ALGORITHM).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    // get username and role
                    String username = decodedJWT.getSubject();
                    String role = decodedJWT.getClaim("role").asString();
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(role));
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    if(request.getServletPath().equals(Constants.REFRESH_TOKEN_PATH)) {
                        // If request is for refresh token, do nothing
                        filterChain.doFilter(request, response);
                    }else{
                        // Access checking
                        List<RoleSubModuleAction> roleSubModuleActions =
                                roleSubModuleActionRepository.findByRoleName(role, request.getServletPath(), request.getMethod());
                        if(roleSubModuleActions.size() > 0){
                            filterChain.doFilter(request, response);
                        }else{
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        }
                    }

                }catch (Exception exception){
                    log.error("Authorization Error: " + exception.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }
}
