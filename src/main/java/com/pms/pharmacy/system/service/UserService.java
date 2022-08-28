package com.pms.pharmacy.system.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.pharmacy.system.model.*;
import com.pms.pharmacy.system.model.Module;
import com.pms.pharmacy.system.repository.*;
import com.pms.pharmacy.system.utils.Constants;
import com.pms.pharmacy.system.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleSubModuleActionRepository roleSubModuleActionRepository;
    private final ActionRepository actionRepository;
    private final ModuleRepository moduleRepository;
    private final SubModuleRepository subModuleRepository;
    private final SubModuleActionRepository subModuleActionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found");

        // set roles
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority((user.getRole().getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        try{

            // decode token
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                String token = authorizationHeader.substring("Bearer ".length());
                JWTVerifier verifier = JWT.require(Constants.JWT_SIGN_ALGORITHM).build();
                String username = verifier.verify(token).getSubject();

                User user = userRepository.findByUsername(username);

                // tokens generate and add to response body
                Map<String, String> tokens = JWTUtils.generateTokens(user.getUsername(), request.getRequestURL().toString(),
                        user.getRole().getName());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }

        }catch (Exception exception){
            log.error("Authentication Error: " + exception.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public Role saveRole(Role role){
        return roleRepository.saveAndFlush(role);
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public void initUserData(){

        long userCount = userRepository.count();

        if(userCount == 0){

            // save default admin and roles
            Role admin = saveRole(new Role(null, "ADMIN", null, null));
            saveRole(new Role(null, "CASHIER", null, null));

            saveUser(new User(null, "defaultadmin", "123", "default",
                    "admin", "961111111v", "0711231231", admin));

            // Save Admin default privileges with modules
            Module userManagement = new Module(null, "User Management", null);
            SubModule user = new SubModule(null, "User", userManagement, null);

            Action view = new Action(null, Constants.VIEW, null);
            Action add = new Action(null, Constants.ADD, null);
            Action update = new Action(null, Constants.UPDATE, null);
            Action delete = new Action(null, Constants.DELETE, null);

            SubModuleAction userView = new SubModuleAction(null, "/api/users", HttpMethod.GET.name(), user, view, null);
            SubModuleAction userAdd = new SubModuleAction(null, "/api/users", HttpMethod.POST.name(), user, add, null);
            SubModuleAction userUpdate = new SubModuleAction(null, "/api/users", HttpMethod.PUT.name(), user, update, null);
            SubModuleAction userDelete = new SubModuleAction(null, "/api/users", HttpMethod.DELETE.name(), user, delete, null);

            RoleSubModuleAction adminUserView = new RoleSubModuleAction(null, userView, admin);
            RoleSubModuleAction adminUserAdd = new RoleSubModuleAction(null, userAdd, admin);
            RoleSubModuleAction adminUserUpdate = new RoleSubModuleAction(null, userUpdate, admin);
            RoleSubModuleAction adminUserDelete = new RoleSubModuleAction(null, userDelete, admin);

            List<RoleSubModuleAction> roleSubModuleActions = List.of(adminUserView, adminUserAdd, adminUserUpdate, adminUserDelete);

            moduleRepository.save(userManagement);
            subModuleRepository.save(user);

            for (RoleSubModuleAction roleSubModuleAction: roleSubModuleActions) {
                actionRepository.save(roleSubModuleAction.getSubModuleAction().getAction());
                subModuleActionRepository.save(roleSubModuleAction.getSubModuleAction());
                roleSubModuleActionRepository.save(roleSubModuleAction);
            }
        }

    }
}
