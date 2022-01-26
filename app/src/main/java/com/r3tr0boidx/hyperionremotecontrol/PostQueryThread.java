package com.r3tr0boidx.hyperionremotecontrol;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class PostQueryThread implements Runnable {

    private final JSONObject query;
    private final HttpURLConnection connection;

    //volatile, so "connection" gets written directly to memory instead of (protected) cache
    private volatile Response response = null;

    PostQueryThread(JSONObject _query, HttpURLConnection _connection) {
        query = _query;
        connection = _connection;
    }

    @Override
    public void run() {
        DataOutputStream outputStream = null;
        try {
            //Write query to output and close it
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(query.toString());
            outputStream.flush();
            outputStream.close();

            //Read body into StringBuilder
            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line.trim());
                }
            }

            //Create response
            int code = connection.getResponseCode();
            String message = connection.getResponseMessage();
            String body = result.toString();

            Log.v(this.getClass().getName(), "Got response: " + message + " - " + code);
            response = new Response(code, message, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response;
    }
}
