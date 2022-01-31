//TODO: Subscribe, for no need to poll/pull: https://docs.hyperion-project.org/en/json/subscribe.html

package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

//https://github.com/hyperion-project/hyperion.ng/tree/master/libsrc/api/JSONRPC_schema
//https://github.com/hyperion-project/hyperion.ng/blob/master/libsrc/api/JsonAPI.cpp

public class InformationReader {

    //region Basics
    public static ServerInfo readResponse(Response serverResponse) {
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

    static ServerInfo readInfos(JSONObject _object) {
        try {
            ComponentInfo[] components = ComponentInfo.readComponents(JSONHelper.getArray(_object,"components"));
            AdjustmentsInfo[] adjustments = AdjustmentsInfo.readAdjustments(JSONHelper.getArray(_object,"adjustment"));
            EffectInfo[] effects = EffectInfo.readEffects(JSONHelper.getArray(_object,"effects"));
            PriorityInfo[] priorities = PriorityInfo.readPriorities(JSONHelper.getArray(_object,"priorities"));
            InstanceInfo[] instances = InstanceInfo.readInstances(JSONHelper.getArray(_object,"instance"));
            LEDInfo[] leds = LEDInfo.readLEDs(JSONHelper.getArray(_object,"leds"));
            SessionInfo[] sessions = SessionInfo.readSessions(JSONHelper.getArray(_object,"sessions"));
            ActiveEffectInfo[] activeEffects = ActiveEffectInfo.readActiveEffects(JSONHelper.getArray(_object,"activeEffects"));
            GrabbersInfo grabbers = GrabbersInfo.readGrabbers(JSONHelper.getObject(_object,"grabbers"));
            TransformInfo[] transforms = TransformInfo.readTransfroms(JSONHelper.getArray(_object,"transform"));

            ServerInfo.ImageToLedMappingTypes ledMappingType = readLedMappingType(_object);
            ServerInfo.VideoModes videoMode = readVideoMode(_object);
            String hostname = readHostname(_object);
            Boolean prioritiesAutoSelect = readPrioritiesAutoSelect(_object);
            Boolean cec = readCEC(JSONHelper.getObject(_object, "cec"));
            Integer[] activeColors = readActiveColors(JSONHelper.getArray(_object, "activeLedColor"));
            String[] ledDevices = readLEDDevices(JSONHelper.getObject(_object, "ledDevices"));

            return new ServerInfo(
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

    //region Active Color
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
    //endregion

    //region Misc
    static ServerInfo.ImageToLedMappingTypes readLedMappingType(JSONObject _object) {
        String type = JSONHelper.getString(_object, "imageToLedMappingType");
        return ServerInfo.castStringToLedMappingTyp(type);
    }

    static ServerInfo.VideoModes readVideoMode(JSONObject _object) {
        String mode = JSONHelper.getString(_object, "videomode");
        return ServerInfo.castStringToVideoMode(mode);
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
