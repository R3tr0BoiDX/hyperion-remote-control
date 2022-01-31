//TODO: Subscribe, for no need to poll: https://docs.hyperion-project.org/en/json/subscribe.html

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
                    && serverResponse.getResponseBody().getBoolean("success")) {                    //Check if it was successful

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

    /* TODO: Things left
     * grabbers
     * ledDevices
     * transform
     */

    static ServerInfos readInfos(JSONObject _object) {
        try {
            //TODO: Should use JSONHelper.getArray as fell to be null-"safe"
            // => requires, that read functions check, that give array is not null
            ComponentInfos[] components = readComponents(_object.getJSONArray("components"));
            AdjustmentsInfos[] adjusts = readAdjustments(_object.getJSONArray("adjustment"));
            EffectInfos[] effects = readEffects(_object.getJSONArray("effects"));
            PriorityInfo[] priorities = readPriorities(_object.getJSONArray("priorities"));
            InstanceInfos[] instances = readInstances(_object.getJSONArray("instance"));
            LEDInfo[] leds = readLEDs(_object.getJSONArray("leds"));
            SessionInfo[] sessions = readSessions(_object.getJSONArray("sessions"));
            Integer[] activeColors = readActiveColors(_object.getJSONArray("activeLedColor"));
            ActiveEffectInfo[] activeEffects = readActiveEffects(_object.getJSONArray("activeEffects"));
            GrabbersInfo grabbers = readGrabbers(_object.getJSONObject("grabbers"));

            ServerInfos.ImageToLedMappingTypes ledMappingType = readLedMappingType(_object);
            ServerInfos.VideoModes videoMode = readVideoMode(_object);
            String hostname = readHostname(_object);
            Boolean prioritiesAutoSelect = readPrioritiesAutoSelect(_object);
            Boolean cec = readCEC(JSONHelper.getObject(_object, "cec"));

            return new ServerInfos(
                    components,
                    adjusts,
                    effects,
                    ledMappingType,
                    videoMode,
                    hostname,
                    priorities,
                    prioritiesAutoSelect,
                    instances,
                    leds,
                    sessions,
                    activeColors,
                    activeEffects,
                    cec, grabbers);
        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Grabbers

    static GrabbersInfo readGrabbers(JSONObject _object){
        //Read "active" part
        String[] activeGrabbers = new String[0];
        try {
            JSONArray active = _object.getJSONArray("active");
            activeGrabbers = new String[active.length()];
            for (int i = 0; i < activeGrabbers.length; i++) {
                activeGrabbers[i] = active.getString(i);
            }
        } catch (JSONException e) {
            Log.w("readGrabbers", "Can't read available grabbers");
            //e.printStackTrace();
        }

        //Read "available" part
        String[] availableGrabbers = new String[0];
        try {
            JSONArray available = _object.getJSONArray("available");
            availableGrabbers = new String[available.length()];
            for (int i = 0; i < availableGrabbers.length; i++) {
                availableGrabbers[i] = available.getString(i);
            }
        } catch (JSONException e) {
            Log.w("readGrabbers", "Can't read available grabbers");
            //e.printStackTrace();
        }

        return new GrabbersInfo(
                activeGrabbers,
                availableGrabbers
        );
    }

    //endregion

    //region Active
    static Integer[] readActiveColors(JSONArray _array) throws JSONException {
        Integer[] activeColors = new Integer[_array.length()];
        for (int i = 0; i < activeColors.length; i++) {
            activeColors[i] = readActiveColor(_array.getJSONObject(i));
        }
        return activeColors;
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
        ActiveEffectInfo[] effects = new ActiveEffectInfo[_array.length()];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = (ActiveEffectInfo) readEffect(_array.getJSONObject(i), true);
        }
        return effects;
    }
    //endregion

    //region Sessions
    static SessionInfo[] readSessions(JSONArray _array) throws JSONException {
        SessionInfo[] sessions = new SessionInfo[_array.length()];
        for (int i = 0; i < sessions.length; i++) {
            sessions[i] = readSession(_array.getJSONObject(i));
        }
        return sessions;
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
        LEDInfo[] leds = new LEDInfo[_array.length()];
        for (int i = 0; i < leds.length; i++) {
            leds[i] = readLED(_array.getJSONObject(i));
        }
        return leds;
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
    static InstanceInfos[] readInstances(JSONArray _array) throws JSONException {
        InstanceInfos[] instances = new InstanceInfos[_array.length()];
        for (int i = 0; i < instances.length; i++) {
            instances[i] = readInstance(_array.getJSONObject(i));
        }
        return instances;
    }

    private static InstanceInfos readInstance(JSONObject _object) {
        return new InstanceInfos(
                JSONHelper.getString(_object, "friendly_name"),
                JSONHelper.getInteger(_object, "instance"),
                JSONHelper.getBoolean(_object, "running")
        );
    }
    //endregion

    //region Priorities
    static PriorityInfo[] readPriorities(JSONArray _array) throws JSONException {
        PriorityInfo[] priorities = new PriorityInfo[_array.length()];
        for (int i = 0; i < priorities.length; i++) {
            priorities[i] = readPriority(_array.getJSONObject(i));
        }
        return priorities;
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
    static EffectInfos[] readEffects(JSONArray _array) throws JSONException {
        EffectInfos[] effects = new EffectInfos[_array.length()];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = readEffect(_array.getJSONObject(i), false);
        }
        return effects;
    }

    static EffectInfos readEffect(JSONObject _object, boolean _active) {
        if (_active){
            return new ActiveEffectInfo(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script"),
                    JSONHelper.getInteger(_object, "priority"),
                    JSONHelper.getInteger(_object, "timeout")
            );
        } else {
            return new EffectInfos(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "file"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script")
            );
        }
    }
    //endregion

    //region Adjustments
    static AdjustmentsInfos[] readAdjustments(JSONArray _array) throws JSONException {
        AdjustmentsInfos[] adjustments = new AdjustmentsInfos[_array.length()];
        for (int i = 0; i < adjustments.length; i++) {
            adjustments[i] = readAdjustment(_array.getJSONObject(i));
        }
        return adjustments;
    }

    static AdjustmentsInfos readAdjustment(JSONObject _object) {
        return new AdjustmentsInfos(
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
    static ComponentInfos[] readComponents(JSONArray _array) throws JSONException {
        ComponentInfos[] component = new ComponentInfos[_array.length()];
        for (int i = 0; i < component.length; i++) {
            component[i] = readComponent(_array.getJSONObject(i));
        }
        return component;
    }

    static ComponentInfos readComponent(JSONObject _object) {
        String type = JSONHelper.getString(_object, "name");
        if (type != null) {
            if (componentNameExists(type)) {
                return new ComponentInfos(
                        ComponentInfos.Component.valueOf(type.toUpperCase()),
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
        for (ComponentInfos.Component c : ComponentInfos.Component.values()) {
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
        return JSONHelper.getBoolean(_object, "enabled");
    }
    //endregion
}
