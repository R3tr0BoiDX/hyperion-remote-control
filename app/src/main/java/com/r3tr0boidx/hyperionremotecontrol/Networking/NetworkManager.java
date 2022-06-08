//TODO: try-cath aus Frage umsetzten https://stackoverflow.com/q/3584210/7184809 - noch relevant?

package com.r3tr0boidx.hyperionremotecontrol.Networking;

import android.util.Log;

import org.json.JSONObject;

import java.net.Inet4Address;

public class NetworkManager {

    private HyperionConnection connection;

    private static final NetworkManager instance = new NetworkManager();

    private NetworkManager() {
        Log.v("NetworkManager", "Created NetworkManager");
    }

    public boolean establishConnection(Inet4Address _ip, HyperionConnection _connection){
        connection = _connection;
        return _connection.connect(_ip);
    }

    public Response writeQuery(JSONObject _query) {
        if (connection != null){
            return connection.write(_query);
        }
        Log.e("writeQuery", "Connection is empty!");
        return null;
    }

    public boolean disconnect() {
        if (connection != null){
            return connection.disconnect();
        }
        Log.e("writeQuery", "Connection is empty!");
        return false;
    }

    public static NetworkManager getInstance() {
        return instance;
    }
}
