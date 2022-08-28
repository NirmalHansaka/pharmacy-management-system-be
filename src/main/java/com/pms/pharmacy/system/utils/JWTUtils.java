package com.pms.pharmacy.system.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTUtils {
    public static Map<String, String> generateTokens(String subject, String issuer, String role){

        Algorithm algorithm = Constants.JWT_SIGN_ALGORITHM;

        String accessToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.MILLISECONDS_OF_HOUR))
                .withIssuer(issuer)
                .withClaim("role", role)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 6 * Constants.MILLISECONDS_OF_HOUR))
                .withIssuer(issuer)
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return tokens;
    }
}
