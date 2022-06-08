package com.r3tr0boidx.hyperionremotecontrol.Networking;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class PostTCPSocketQueryThreed implements Runnable{

    private final JSONObject query;
    private PrintWriter out;
    private BufferedReader in;

    private volatile String response;

    public PostTCPSocketQueryThreed(JSONObject query, PrintWriter out, BufferedReader in) {
        this.query = query;
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            out.write(query.toString());
            response = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }
}
