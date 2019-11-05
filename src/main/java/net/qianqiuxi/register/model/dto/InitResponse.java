package net.qianqiuxi.register.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitResponse extends ServiceResponse{

    @JsonProperty(value = "token")
    private String token;

    public InitResponse(Integer code, Status status, String message, String token) {
        super(code, status, message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
