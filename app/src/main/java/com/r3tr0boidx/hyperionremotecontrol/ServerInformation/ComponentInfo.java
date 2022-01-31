package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ComponentInfo {

    private final Component type;
    private final Boolean state;

    ComponentInfo(
            Component type,
            Boolean state) {
        this.type = type;
        this.state = state;
    }

    static ComponentInfo[] readComponents(JSONArray _array) throws JSONException {
        if (_array != null){
            ComponentInfo[] component = new ComponentInfo[_array.length()];
            for (int i = 0; i < component.length; i++) {
                component[i] = readComponent(_array.getJSONObject(i));
            }
            return component;
        }
        return new ComponentInfo[0];
    }

    private static ComponentInfo readComponent(JSONObject _object) {
        String type = JSONHelper.getString(_object, "name");
        if (type != null) {
            if (componentNameExists(type)) {
                return new ComponentInfo(
                        ComponentInfo.Component.valueOf(type.toUpperCase()),
                        JSONHelper.getBoolean(_object, "enabled")
                );
            } else {
                Log.w("readComponents", "Component name doesn't exists");
            }
        } else {
            Log.w("readComponents", "Can't read component");
        }
        return null;
    }

    private static boolean componentNameExists(String test) {
        for (ComponentInfo.Component c : ComponentInfo.Component.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public static String concatenatePrintableString(ComponentInfo[] _component) {
        StringBuilder sb = new StringBuilder();
        for (ComponentInfo in : _component) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        return "===ComponentInfo===" + System.lineSeparator() +
                "type: " + type.toString() + System.lineSeparator() +
                "state: " + state + System.lineSeparator();
    }

    public Component getType() {
        return type;
    }

    public Boolean getState() {
        return state;
    }

    //Warning: Must be all upper case, else Component.valueOf("String") isn't working
    enum Component{
        ALL,                //Hyperion Instance itself
        SMOOTHING,          //Smoothing
        BLACKBORDER,        //Detect black borders
        FORWARDER,          //JSON/Proto forwarder
        BOBLIGHTSERVER,     //Boblight server
        GRABBER,            //Platform capture
        V4L,                //Video4Linux USB capture device
        LEDDEVICE           //Led device start/stops output of the configured led device
    }
}
