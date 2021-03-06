package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EstablishHTTPConnectionThread implements Runnable{

    private final Inet4Address ip;
    private final boolean unsecure;

    //volatile, so "connection" gets written directly to memory instead of (protected) cache
    private volatile HttpURLConnection connection = null;

    EstablishHTTPConnectionThread(Inet4Address _ip, boolean _unsecure){
        ip = _ip;
        unsecure = _unsecure;
    }

    /**
     * Connect to server
     */
    @Override
    public void run() {
        try {
            if (unsecure){
                connection = getUnsecureConnection(ip);
            } else {
                connection = getSecureConnection(ip);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpURLConnection getUnsecureConnection(Inet4Address _ip) throws IOException {
        URL serverURL = getServerURL(_ip, HTTPConnection.HTTP_PORT, "http");
        return getConnection(serverURL);
    }

    //TODO: Not working right now - SSL handshakes fails (Trust anchor for certification path not found.)
    public static HttpsURLConnection getSecureConnection(Inet4Address _ip) throws IOException {
        URL serverURL = getServerURL(_ip, HTTPConnection.HTTPS_PORT, "https");
        return (HttpsURLConnection) getConnection(serverURL);
    }

    //TODO: try-catch, and then return null if cannot connect
    private static HttpURLConnection getConnection(URL _url) throws IOException {
        HttpURLConnection con;
        if (_url.getProtocol().equals("http")){
            con = (HttpURLConnection) _url.openConnection();
        } else {
            con = (HttpsURLConnection) _url.openConnection();
        }
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.setRequestProperty("Accept", "application/json, text/plain, */*");
        con.setDoOutput(true);
        con.connect();
        Log.v("getConnection", con + "; " + con.getPermission());
        return con;
    }

    private static URL getServerURL(Inet4Address _ip, int _port, String _protocol) throws MalformedURLException {
        final URL url = new URL(_protocol, _ip.getHostAddress(), _port, HTTPConnection.URL_FILE);
        Log.v("getServerURL", url.toString());
        return url;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }
}
