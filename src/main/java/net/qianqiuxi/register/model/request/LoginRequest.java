package net.qianqiuxi.register.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "password")
    private String password;

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
