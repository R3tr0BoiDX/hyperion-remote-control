package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstanceInfo {
    private final String friendlyName;
    private final Integer instance;
    private final Boolean running;

    public InstanceInfo(
            String friendlyName,
            Integer instance,
            Boolean running) {
        this.friendlyName = friendlyName;
        this.instance = instance;
        this.running = running;
    }

    static InstanceInfo[] readInstances(JSONArray _array) throws JSONException {
        if (_array != null){
            InstanceInfo[] instances = new InstanceInfo[_array.length()];
            for (int i = 0; i < instances.length; i++) {
                instances[i] = readInstance(_array.getJSONObject(i));
            }
            return instances;
        }
        return new InstanceInfo[0];
    }

    private static InstanceInfo readInstance(JSONObject _object) {
        return new InstanceInfo(
                JSONHelper.getString(_object, "friendly_name"),
                JSONHelper.getInteger(_object, "instance"),
                JSONHelper.getBoolean(_object, "running")
        );
    }

    public static String concatenatePrintableString(InstanceInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (InstanceInfo in : _instances) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        return "===InstanceInfo===" + System.lineSeparator() +
                "friendlyName: " + friendlyName + System.lineSeparator() +
                "instance: " + instance + System.lineSeparator() +
                "running: " + running + System.lineSeparator();
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public Integer getInstance() {
        return instance;
    }

    public Boolean getRunning() {
        return running;
    }
}
