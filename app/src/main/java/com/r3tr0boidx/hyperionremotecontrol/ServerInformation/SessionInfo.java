package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

public class SessionInfo {

    //Are we sure on those data types ? - Seems we are: https://github.com/hyperion-project/hyperion.ng/blob/master/include/bonjour/bonjourrecord.h

    private final Inet4Address address;
    private final String domain;
    private final String host;
    private final String name;
    private final Integer port;
    private final String type;

    SessionInfo(
            Inet4Address address,
            String domain,
            String host,
            String name,
            Integer port,
            String type) {
        this.address = address;
        this.domain = domain;
        this.host = host;
        this.name = name;
        this.port = port;
        this.type = type;
    }

    static SessionInfo[] readSessions(JSONArray _array) throws JSONException {
        if (_array != null){
            SessionInfo[] sessions = new SessionInfo[_array.length()];
            for (int i = 0; i < sessions.length; i++) {
                sessions[i] = readSession(_array.getJSONObject(i));
            }
            return sessions;
        }
        return new SessionInfo[0];
    }

    private static SessionInfo readSession(JSONObject _object) {
        //Convert received address to URL object
        Inet4Address ip = null;
        String address = JSONHelper.getString(_object, "address");
        if (address != null) {
            try {
                ip = (Inet4Address) Inet4Address.getByName(address);
            } catch (UnknownHostException e) {
                Log.w("readSession", "Can't read address");
                e.printStackTrace();
            }
        }

        return new SessionInfo(
                ip,
                JSONHelper.getString(_object, "domain"),
                JSONHelper.getString(_object, "host"),
                JSONHelper.getString(_object, "name"),
                JSONHelper.getInteger(_object, "port"),
                JSONHelper.getString(_object, "type")
        );
    }

    public static String concatenatePrintableString(SessionInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (SessionInfo se : _instances) {
            sb.append(se.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        return "===InstanceInfo===" + System.lineSeparator() +
                "address: " + address + System.lineSeparator() +
                "domain: " + domain + System.lineSeparator() +
                "host: " + host + System.lineSeparator() +
                "name: " + name + System.lineSeparator() +
                "port: " + port + System.lineSeparator() +
                "type: " + type + System.lineSeparator();
    }

    public Inet4Address getAddress() {
        return address;
    }

    public String getDomain() {
        return domain;
    }

    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public Integer getPort() {
        return port;
    }

    public String getType() {
        return type;
    }
}