package com.pms.pharmacy.system.utils;

import com.auth0.jwt.algorithms.Algorithm;

public class Constants {

    // Security
    private final static String SECRET_KEY = "SECRET";
    public final static Algorithm JWT_SIGN_ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());
    public final static Long MILLISECONDS_OF_HOUR = 60 * 60 * 1000L;
    public final static String LOGIN_PATH = "/api/auth/login";
    public final static String REFRESH_TOKEN_PATH = "/api/users/refresh-token";

    // Actions
    public final static String VIEW = "VIEW";
    public final static String ADD = "ADD";
    public final static String UPDATE = "UPDATE";
    public final static String DELETE = "DELETE";
}
