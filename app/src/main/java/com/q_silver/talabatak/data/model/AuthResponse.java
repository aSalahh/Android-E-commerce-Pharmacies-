package com.q_silver.talabatak.data.model;

public class AuthResponse {
    int responseResult;

    public AuthResponse(int responseResult) {
        this.responseResult = responseResult;
    }

    public AuthResponse() {
    }

    public int getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(int responseResult) {
        this.responseResult = responseResult;
    }
}
