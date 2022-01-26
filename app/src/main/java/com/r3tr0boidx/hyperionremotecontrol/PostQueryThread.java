package com.r3tr0boidx.hyperionremotecontrol;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class PostQueryThread implements Runnable{

    private final JSONObject query;
    private final HttpURLConnection connection;

    private volatile Response response;

    PostQueryThread(JSONObject _query, HttpURLConnection _connection){
        query = _query;
        connection = _connection;
    }

    @Override
    public void run() {
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(connection.getOutputStream());

            outputStream.writeBytes(query.toString());
            outputStream.flush();
            outputStream.close();

            //response = new Response(connection.getResponseCode(), connection.getResponseMessage());

            Log.i("STATUS", connection.getResponseCode() + "");
            //Log.i("MSG" , connection.getResponseMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response;
    }
}
