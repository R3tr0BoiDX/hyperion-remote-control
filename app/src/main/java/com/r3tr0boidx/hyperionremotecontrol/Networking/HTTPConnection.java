package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.Inet4Address;

/**
 * With an HTTPConnection, you need to create a new connection every time
 */
public class HTTPConnection implements HyperionConnection {

    static final int HTTP_PORT = 8090;
    static final int HTTPS_PORT = 8092;
    static final String URL_FILE = "json-rpc";

    private final boolean unsecure;

    private HttpURLConnection connection = null; //TODO: No need to hold that. Make establishConnection() return an connection. Move disconnect() from HyperionConnection to HyperionSocketConnection
    private Inet4Address ip = null;

    public HTTPConnection(boolean unsecure) {
        this.unsecure = unsecure;
    }

    @Override
    public boolean connect(Inet4Address _ip) {
        ip = _ip;
        return true;
    }

    boolean establishConnection() {
        try {
            EstablishHTTPConnectionThread connectionThread = new EstablishHTTPConnectionThread(ip, unsecure);
            Thread thread = new Thread(connectionThread);
            thread.start();
            thread.join();

            connection = connectionThread.getConnection();
            Log.v("establishConnection", "Started network thread");
            return true;

        } catch (InterruptedException e) {
            Log.e("connect", "Can't establish a connection to " + ip.getHostAddress());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Response write(JSONObject _query) {
        //create a new connection every time, theres a query to post => need of HTTP connections, look up https://stackoverflow.com/a/11413107/7184809
        if (establishConnection()) {
            try {
                if (connection != null) {
                    PostHTTPQueryThread queryThread = new PostHTTPQueryThread(_query, connection);
                    Thread thread = new Thread(queryThread);
                    thread.start();
                    thread.join();

                    return queryThread.getResponse();
                } else {
                    throw new NullPointerException("Connection is not established");
                }
            } catch (InterruptedException e) {
                Log.e("write", "Can't join thread");
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean disconnect() {
        new Thread(() -> connection.disconnect());
        Log.v("disconnect", "Disconnect from server");
        return true;
    }
}
