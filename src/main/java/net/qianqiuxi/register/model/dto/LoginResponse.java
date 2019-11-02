package net.qianqiuxi.register.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse extends ServiceResponse {

    @JsonProperty(value = "token")
    final private String token;

    /**
     * Login response constructor.
     * @param code login result code
     * @param status login result status
     * @param message login message
     * @param token user token if success
     */
    public LoginResponse(Integer code, Status status, String message, String token) {
        super(code, status, message);
        this.token = token;
    }

    final public String getToken() {
        return token;
    }
}
