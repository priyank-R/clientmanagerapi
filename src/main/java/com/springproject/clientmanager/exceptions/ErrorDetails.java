package com.springproject.clientmanager.exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String requestUrl;

    public ErrorDetails(Date timestamp, String message, String requestUrl) {
        this.timestamp = timestamp;
        this.message = message;
        this.requestUrl = requestUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
