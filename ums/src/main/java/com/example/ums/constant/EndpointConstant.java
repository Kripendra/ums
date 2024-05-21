package com.example.ums.constant;

public class EndpointConstant {

    public static final String CREATE_USER = "/api/users";
    public static final String USER_FROM_USERNAME = "/api/users/{username}";

    public static final String USERS_FROM_FIRSTNAME = "/api/users/first-name/{firstName}";

    public static final String USERS_FROM_LASTNAME = "/api/users/last-name{lastName}";

    public static final String USER_FROM_EMAIL = "/api/users/email/{email}";

    public static final String UPDATE_USER = "/api/users/{id}";

    public static final String DELETE_USER = "/api/users/{id}";
}
