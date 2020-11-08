package com.emobile.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericResponse {

    private Boolean success;
    private List<String> messages;
    private Object response;


    public Object getResponse() {
        return response;
    }

    public void setResponse(Object responseObject) {
        this.response = responseObject;
    }

    public GenericResponse() {
        this.success = Boolean.TRUE;
        this.messages = null;
    }

    public GenericResponse(Boolean success) {
        this.success = success;
        this.messages = null;
    }

    public static GenericResponse createSuccessResponse() {
        GenericResponse response = new GenericResponse();
        return response;
    }
    public static GenericResponse createSuccessResponse(String messageText) {
        GenericResponse response = new GenericResponse();
        response.addMessage(messageText);
        return response;
    }

    public static GenericResponse createErrorResponse(String messageText) {
        GenericResponse response = new GenericResponse(Boolean.FALSE);
        response.addMessage(messageText);
        return response;
    }

    public void addMessage(String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }

    public void failResponse() {
        this.success = Boolean.FALSE;
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<String> getMessages() {
        return messages;
    }

}

