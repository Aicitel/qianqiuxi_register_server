package net.qianqiuxi.register.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
    public enum Status{
       SUCCEED, FAIL
    }

    @JsonProperty(value = "code")
    private Integer code;
    @JsonProperty(value = "message")
    private String message;
    @JsonProperty(value = "status")
    private Status status;

    public ServiceResponse(Integer code, Status status, String message) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer errorCode) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
