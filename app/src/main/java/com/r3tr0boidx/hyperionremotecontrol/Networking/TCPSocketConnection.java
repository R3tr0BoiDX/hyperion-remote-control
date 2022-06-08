package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Helper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;

public class TCPSocketConnection implements HyperionSocketConnection {

    static final int TCP_SOCKET_PORT = 19444;

    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public boolean connect(Inet4Address _ip) {
        try {
            EstablishTCPSocketConnection connectionThread = new EstablishTCPSocketConnection(_ip);
            Thread thread = new Thread(connectionThread);
            thread.start();
            thread.join();

            client = connectionThread.getClient();
            out = connectionThread.getOut();
            in = connectionThread.getIn();

            Log.v("establishConnection", "Connected to " + _ip.getHostAddress() + ":" + TCP_SOCKET_PORT + " via TCP socket");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Response write(JSONObject _query) {
        try {
            PostTCPSocketQueryThreed connectionThread = new PostTCPSocketQueryThreed(_query, out, in);
            Thread thread = new Thread(connectionThread);
            thread.start();
            thread.join();

            Helper.Log(connectionThread.getResponse());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public boolean disconnect() {
        try {
            in.close();
            out.close();
            client.close();
            return true;
        } catch (IOException e) {
            Log.w("disconnect", "Not able to disconnect TCP socket connection");
            //e.printStackTrace();
        }
        return false;
    }
}
