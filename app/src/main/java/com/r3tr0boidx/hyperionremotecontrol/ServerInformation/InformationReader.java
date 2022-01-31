//TODO: Subscribe, for no need to poll/pull: https://docs.hyperion-project.org/en/json/subscribe.html

package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.UnknownHostException;

//https://github.com/hyperion-project/hyperion.ng/tree/master/libsrc/api/JSONRPC_schema
//https://github.com/hyperion-project/hyperion.ng/blob/master/libsrc/api/JsonAPI.cpp

public class InformationReader {

    //region Basics
    public static ServerInfos readResponse(Response serverResponse) {
        try {
            if (serverResponse.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (serverResponse.getResponseBody().getString("command").equals("serverinfo")  //Check it was the right command
                    && serverResponse.getResponseBody().getBoolean("success")) {                //Check if it was successful

                    JSONObject infos = serverResponse.getResponseBody().getJSONObject("info");
                    return readInfos(infos);
                } else {
                    Log.w("readInformations", "Received no Server Informations");
                }
            }
        } catch (JSONException e) {
            Log.e("readInformations", "Not able to parse received data");
            e.printStackTrace();
        }
        return null;
    }

    static ServerInfos readInfos(JSONObject _object) {
        try {
            ComponentInfo[] components = readComponents(JSONHelper.getArray(_object,"components"));
            AdjustmentsInfo[] adjustments = readAdjustments(JSONHelper.getArray(_object,"adjustment"));
            EffectInfo[] effects = readEffects(JSONHelper.getArray(_object,"effects"));
            PriorityInfo[] priorities = readPriorities(JSONHelper.getArray(_object,"priorities"));
            InstanceInfo[] instances = readInstances(JSONHelper.getArray(_object,"instance"));
            LEDInfo[] leds = readLEDs(JSONHelper.getArray(_object,"leds"));
            SessionInfo[] sessions = readSessions(JSONHelper.getArray(_object,"sessions"));
            ActiveEffectInfo[] activeEffects = readActiveEffects(JSONHelper.getArray(_object,"activeEffects"));
            GrabbersInfo grabbers = readGrabbers(JSONHelper.getObject(_object,"grabbers"));
            TransformInfo[] transforms = readTransfroms(JSONHelper.getArray(_object,"transform"));

            ServerInfos.ImageToLedMappingTypes ledMappingType = readLedMappingType(_object);
            ServerInfos.VideoModes videoMode = readVideoMode(_object);
            String hostname = readHostname(_object);
            Boolean prioritiesAutoSelect = readPrioritiesAutoSelect(_object);
            Boolean cec = readCEC(JSONHelper.getObject(_object, "cec"));
            Integer[] activeColors = readActiveColors(JSONHelper.getArray(_object, "activeLedColor"));
            String[] ledDevices = readLEDDevices(JSONHelper.getObject(_object, "ledDevices"));

            return new ServerInfos(
                    activeEffects,
                    activeColors,
                    adjustments,
                    cec,
                    components,
                    effects,
                    grabbers,
                    hostname,
                    ledMappingType,
                    instances,
                    ledDevices,
                    leds,
                    priorities,
                    prioritiesAutoSelect,
                    sessions,
                    transforms,
                    videoMode);
        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Transform
    static TransformInfo[] readTransfroms(JSONArray _array) throws JSONException {
        if (_array != null){
            TransformInfo[] transform = new TransformInfo[_array.length()];
            for (int i = 0; i < transform.length; i++) {
                transform[i] = readTransfrom(_array.getJSONObject(i));
            }
            return transform;
        }
        return new TransformInfo[0];
    }

    private static TransformInfo readTransfrom(JSONObject _object) {
        return new TransformInfo(
                JSONHelper.getString(_object, "id"),
                JSONHelper.getInteger(_object, "luminanceGain"),
                JSONHelper.getInteger(_object, "luminanceMinimum"),
                JSONHelper.getInteger(_object, "saturationGain"),
                JSONHelper.getInteger(_object, "saturationLGain"),
                JSONHelper.getInteger(_object, "valueGain"),
                readDoubleArray(JSONHelper.getArray(_object, "blacklevel")),
                readDoubleArray(JSONHelper.getArray(_object, "whitelevel")),
                readDoubleArray(JSONHelper.getArray(_object, "gamma")),
                readDoubleArray(JSONHelper.getArray(_object, "threshold"))
        );
    }

    private static Double[] readDoubleArray(JSONArray _array){
        if (_array != null){
            Double[] result = new Double[_array.length()];
            try {
                for (int i = 0; i < result.length; i++) {
                    result[i] = _array.getDouble(i);
                }
            } catch (JSONException e) {
                Log.w("readDoubleArray", "Can't read " + _array);
                //e.printStackTrace();
            }
            return result;
        }
        return new Double[0];
    }
    //endregion

    //region Grabbers
    static GrabbersInfo readGrabbers(JSONObject _object){
        return new GrabbersInfo(
                readStringArray(_object, "active"),
                readStringArray(_object, "available")
        );
    }

    static String[] readStringArray(JSONObject _object, String _name){
        String[] result = new String[0];

        try {
            JSONArray array = _object.getJSONArray(_name);
            result = new String[array.length()];
            for (int i = 0; i < result.length; i++) {
                result[i] = array.getString(i);
            }
        } catch (Exception e) {
            Log.w("readStringArray", "Can't read " + _object.toString());
            //e.printStackTrace();
        }
        return result;
    }
    //endregion

    //region Active
    static Integer[] readActiveColors(JSONArray _array) throws JSONException {
        if (_array != null){
            Integer[] activeColors = new Integer[_array.length()];
            for (int i = 0; i < activeColors.length; i++) {
                activeColors[i] = readActiveColor(_array.getJSONObject(i));
            }
            return activeColors;
        }
        return new Integer[0];
    }

    static Integer readActiveColor(JSONObject _object) {
        //Get only RGB, since HSV is depending on RGB in Hyperion code - no need to get that as well
        JSONArray rgb = JSONHelper.getArray(_object, "RGB Value");

        if (rgb != null){
            return JSONHelper.castArrayToColor(rgb);
        }
        return null;
    }

    static ActiveEffectInfo[] readActiveEffects(JSONArray _array) throws JSONException {
        if (_array != null){
            ActiveEffectInfo[] effects = new ActiveEffectInfo[_array.length()];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = (ActiveEffectInfo) readEffect(_array.getJSONObject(i), true);
            }
            return effects;
        }
        return new ActiveEffectInfo[0];
    }
    //endregion

    //region Sessions
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
    //endregion

    //region LEDs
    static LEDInfo[] readLEDs(JSONArray _array) throws JSONException {
        if (_array != null){
            LEDInfo[] leds = new LEDInfo[_array.length()];
            for (int i = 0; i < leds.length; i++) {
                leds[i] = readLED(_array.getJSONObject(i));
            }
            return leds;
        }
        return new LEDInfo[0];
    }

    private static LEDInfo readLED(JSONObject _object) {
        return new LEDInfo(
                JSONHelper.getDouble(_object, "hmax"),
                JSONHelper.getDouble(_object, "hmin"),
                JSONHelper.getDouble(_object, "vmax"),
                JSONHelper.getDouble(_object, "vmin")
        );
    }
    //endregion

    //region Instances
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
    //endregion

    //region Priorities
    static PriorityInfo[] readPriorities(JSONArray _array) throws JSONException {
        if (_array != null){
            PriorityInfo[] priorities = new PriorityInfo[_array.length()];
            for (int i = 0; i < priorities.length; i++) {
                priorities[i] = readPriority(_array.getJSONObject(i));
            }
            return priorities;
        }
        return new PriorityInfo[0];
    }

    static PriorityInfo readPriority(JSONObject _object) {
        //Get only RGB, since HSV is depending on RGB in Hyperion code - no need to get that as well
        JSONObject values = JSONHelper.getObject(_object, "value");
        JSONArray rgb = null;
        if (values != null) {
            rgb = JSONHelper.getArray(values, "RGB");
        }

        return new PriorityInfo(
                JSONHelper.getInteger(_object, "priority"),
                JSONHelper.getInteger(_object, "duration_ms"),
                JSONHelper.getString(_object, "owner"),
                JSONHelper.getString(_object, "componentId"),
                JSONHelper.getString(_object, "origin"),
                JSONHelper.getBoolean(_object, "active"),
                JSONHelper.getBoolean(_object, "visible"),
                JSONHelper.castArrayToColor(rgb)
        );
    }
    //endregion

    //region Effects
    static EffectInfo[] readEffects(JSONArray _array) throws JSONException {
        if (_array != null){
            EffectInfo[] effects = new EffectInfo[_array.length()];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = readEffect(_array.getJSONObject(i), false);
            }
            return effects;
        }
        return new EffectInfo[0];
    }

    static EffectInfo readEffect(JSONObject _object, boolean _active) {
        if (_active){
            return new ActiveEffectInfo(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script"),
                    JSONHelper.getInteger(_object, "priority"),
                    JSONHelper.getInteger(_object, "timeout")
            );
        } else {
            return new EffectInfo(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "file"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script")
            );
        }
    }
    //endregion

    //region Adjustments
    static AdjustmentsInfo[] readAdjustments(JSONArray _array) throws JSONException {
        if (_array != null){
            AdjustmentsInfo[] adjustments = new AdjustmentsInfo[_array.length()];
            for (int i = 0; i < adjustments.length; i++) {
                adjustments[i] = readAdjustment(_array.getJSONObject(i));
            }
            return adjustments;
        }
        return new AdjustmentsInfo[0];
    }

    static AdjustmentsInfo readAdjustment(JSONObject _object) {
        return new AdjustmentsInfo(
                JSONHelper.getString(_object, "id"),

                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "red")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "green")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "blue")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "yellow")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "cyan")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "magenta")),
                JSONHelper.castArrayToColor(JSONHelper.getArray(_object, "white")),

                JSONHelper.getDouble(_object, "gammaBlue"),
                JSONHelper.getDouble(_object, "gammaGreen"),
                JSONHelper.getDouble(_object, "gammaRed"),

                JSONHelper.getInteger(_object, "backlightThreshold"),
                JSONHelper.getBoolean(_object, "backlightColored"),

                JSONHelper.getInteger(_object, "brightness"),
                JSONHelper.getInteger(_object, "brightnessCompensation")
        );
    }
    //endregion

    //region Components
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

    static ComponentInfo readComponent(JSONObject _object) {
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

    public static boolean componentNameExists(String test) {
        for (ComponentInfo.Component c : ComponentInfo.Component.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region Misc
    static ServerInfos.ImageToLedMappingTypes readLedMappingType(JSONObject _object) {
        String type = JSONHelper.getString(_object, "imageToLedMappingType");
        return ServerInfos.castStringToLedMappingTyp(type);
    }

    static ServerInfos.VideoModes readVideoMode(JSONObject _object) {
        String mode = JSONHelper.getString(_object, "videomode");
        return ServerInfos.castStringToVideoMode(mode);
    }

    static String readHostname(JSONObject _object) {
        return JSONHelper.getString(_object, "hostname");
    }

    static Boolean readPrioritiesAutoSelect(JSONObject _object) {
        return JSONHelper.getBoolean(_object, "priorities_autoselect");
    }

    static Boolean readCEC(JSONObject _object) {
        if (_object != null){
            return JSONHelper.getBoolean(_object, "enabled");
        }
        return null;
    }

    static String[] readLEDDevices(JSONObject _object){
        if (_object != null){
            String[] ledDevices = new String[0];
            try {
                JSONArray available = _object.getJSONArray("available");
                ledDevices = new String[available.length()];
                for (int i = 0; i < ledDevices.length; i++) {
                    ledDevices[i] = available.getString(i);
                }
            } catch (JSONException e) {
                Log.w("readLEDDevices", "Can't read available LED devices");
                //e.printStackTrace();
            }
            return ledDevices;
        }
        return new String[0];
    }
    //endregion
}
