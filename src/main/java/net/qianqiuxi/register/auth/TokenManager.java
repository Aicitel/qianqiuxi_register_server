package net.qianqiuxi.register.auth;

import net.qianqiuxi.register.auth.token.Token;

public interface TokenManager {

    /**
     * After user login success, create a token to response.
     * @param userId login user
     * @return auth token
     */
    Token createTokenForUser(Integer userId);

    /**
     * Check whether a token is valid
     * @param token token with userId and token string
     * @return is token valid
     */
    boolean checkTokenForUser(Token token);
}
