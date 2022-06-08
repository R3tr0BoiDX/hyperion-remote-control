package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class PostHTTPQueryThread implements Runnable {

    private final JSONObject query;
    private final HttpURLConnection connection;

    //volatile, so "connection" gets written directly to memory instead of (protected) cache
    private volatile Response response = null;

    PostHTTPQueryThread(JSONObject _query, HttpURLConnection _connection) {
        query = _query;
        connection = _connection;
    }

    @Override
    public void run() {
        DataOutputStream out;
        try {
            //Write query to output and close it
            out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(query.toString());
            out.flush();
            out.close();

            //Read body into StringBuilder
            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line.trim());
                }
            }

            //Create response
            int code = connection.getResponseCode();
            String message = connection.getResponseMessage();
            String body = result.toString();

            Log.v("PostHTTPQueryThread", "Got response: " + message + " - " + code);
            response = new Response(code, message, body);
        } catch (IOException e) {
            Log.e("PostHTTPQueryThread", "Problem with receiving data");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("PostHTTPQueryThread", "Not able to parse data into JSON object");
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response;
    }
}
