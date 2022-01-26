package com.r3tr0boidx.hyperionremotecontrol;

import android.util.Log;

public class Response {
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public Response(int _code, String _message, String _body){
        responseCode = _code;
        responseMessage = _message;
        responseBody = _body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
