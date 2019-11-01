package net.qianqiuxi.register.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
    public enum Status{
       SUCCEED, FAIL
    }

    @JsonProperty(value = "errorCode")
    private Integer errorCode;
    @JsonProperty(value = "message")
    private String message;
    @JsonProperty(value = "status")
    private Status status;

    public ServiceResponse(Integer errorCode, Status status, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
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
