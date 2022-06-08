package com.r3tr0boidx.hyperionremotecontrol.Networking;

import com.r3tr0boidx.hyperionremotecontrol.Helper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Socket;

public class EstablishTCPSocketConnection implements Runnable{

    private final Inet4Address ip;

    private volatile Socket client;
    private volatile PrintWriter out;
    private volatile BufferedReader in;

    EstablishTCPSocketConnection(Inet4Address _ip){
        ip = _ip;
    }

    @Override
    public void run() {
        try {
            client = new Socket(ip.getHostAddress(), TCPSocketConnection.TCP_SOCKET_PORT);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
}
