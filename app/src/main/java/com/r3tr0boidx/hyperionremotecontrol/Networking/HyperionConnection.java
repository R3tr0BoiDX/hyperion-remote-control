package com.r3tr0boidx.hyperionremotecontrol.Networking;

import org.json.JSONObject;

import java.net.Inet4Address;

public interface HyperionConnection { //TODO: Turn into abstract class and introduce default constructor which accepts IP
    boolean connect(Inet4Address _ip);
    Response write(JSONObject _query);
    boolean disconnect();
}
