package net.qianqiuxi.register.service;

import net.qianqiuxi.register.model.dto.ServiceResponse;

public interface LoginService {

    /**
     * Login response code begins with 2340
     */
    int SUCCEED = 234000;
    int NOT_MATCH = 234001;
    int TOKEN_NOT_VALID = 234002;
    int SERVER_ERROR = 2340233;

    /**
     * Login with username & password.
     * @param username
     * @param password
     * @return login result
     */
    ServiceResponse login(String username, String password);

    /**
     * Check token validation.
     * @param username
     * @param token
     * @return auth result
     */
    ServiceResponse auth(String username, String token);
}
