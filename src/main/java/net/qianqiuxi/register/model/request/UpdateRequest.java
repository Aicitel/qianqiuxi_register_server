package net.qianqiuxi.register.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UpdateRequest implements Serializable {

    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "opponent")
    private String opponent;

    public String getToken() {
        return token;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getUsername() {
        return username;
    }
}
