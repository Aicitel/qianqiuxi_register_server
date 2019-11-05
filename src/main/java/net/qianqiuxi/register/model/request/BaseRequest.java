package net.qianqiuxi.register.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BaseRequest implements Serializable {

    @JsonProperty(value = "username")
    protected String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
