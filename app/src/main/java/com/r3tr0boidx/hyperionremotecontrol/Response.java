package com.r3tr0boidx.hyperionremotecontrol;

public class Response {
    private int responseCode;
    private String responseMessage;

    public Response(int _code, String _message){
        responseCode = _code;
        responseMessage = _message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
