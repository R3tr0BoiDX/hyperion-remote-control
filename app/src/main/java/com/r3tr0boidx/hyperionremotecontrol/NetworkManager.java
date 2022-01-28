package com.r3tr0boidx.hyperionremotecontrol;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkManager {
    public static final int HTTP_PORT = 8090;
    public static final int HTTPS_PORT = 8092;
    public static final String URL_FILE = "json-rpc";

    private HttpURLConnection connection = null;
    private static final NetworkManager instance = new NetworkManager();

    private NetworkManager(){
        Log.v("NetworkManager", "Created NetworkManager");
    }

    //TODO: try-cath aus Frage umsetzten https://stackoverflow.com/q/3584210/7184809

    public void establishConnection(Inet4Address _ip) throws InterruptedException {
        establishConnection(_ip, false);
    }

    public void establishConnection(Inet4Address _ip, boolean _unsecure) throws InterruptedException {
        EstablishConnectionThread establishConnectionThread = new EstablishConnectionThread(_ip, _unsecure);
        Thread thread = new Thread(establishConnectionThread);
        thread.start();
        thread.join();

        connection = establishConnectionThread.getConnection();
        Log.v("establishConnection", "Started network thread");
    }

    public Response postQuery(JSONObject _query) throws InterruptedException {
        if (connection != null){
            PostQueryThread queryThread = new PostQueryThread(_query, connection);
            Thread thread = new Thread(queryThread);
            thread.start();
            thread.join();

            return queryThread.getResponse();
        } else {
            throw new NullPointerException("Connection is not established");
        }
    }

    public void disconnect(){
        new Thread(() -> connection.disconnect());
    }

    public static NetworkManager getInstance() {
        return instance;
    }
}
