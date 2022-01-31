package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {
    //TODO: remove "response" from name
    private final int responseCode;
    private final String responseMessage;
    private final JSONObject responseBody;

    public Response(int _code, String _message, String _body) throws JSONException {
        responseCode = _code;
        responseMessage = _message;
        responseBody = new JSONObject(_body);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public JSONObject getResponseBody() {
        return responseBody;
    }
}
