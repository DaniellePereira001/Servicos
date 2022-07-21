package com.soulcode.Servicos.Controllers.Exceptions;

import java.time.Instant;

public class StandardError {

    private Instant Timestamp;
    private Integer status;
    private String error;

    private String message;
    private String path;

    public  StandardError(){}

    public Instant getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        Timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
