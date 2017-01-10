package com.ai.mycar.model;

import com.google.gson.Gson;

/**
 * Created by Piyush Agarwal(559339) on 1/9/17.
 */
public class APIResponse {

    private int status_code;

    private String message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {

        return new Gson().toJson(this, APIResponse.class);

    }
}
