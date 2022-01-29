//TODO: Subscribe, for no need to poll: https://docs.hyperion-project.org/en/json/subscribe.html

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

    //TODO: https://docs.hyperion-project.org/en/json/ServerInfo.html#instance

    //region Basics
    public static ServerInfos readResponse(Response serverResponse) {
        try {
            if (serverResponse.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (serverResponse.getResponseBody().getString("command").equals("serverinfo")
                && serverResponse.getResponseBody().getBoolean("success")) {

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
            Log.d("JSON Base", _object.toString());

            ComponentsInfos components = readComponents(_object.getJSONArray("components"));
            AdjustmentsInfos[] adjusts = readAdjustments(_object.getJSONArray("adjustment"));
            EffectInfos[] effects = readEffects(_object.getJSONArray("effects"));
            PriorityInfo[] priorities = readPriorities(_object.getJSONArray("priorities"));

            ServerInfos.ImageToLedMappingTypes ledMappingType = readLedMappingType(_object);
            ServerInfos.VideoModes videoMode = readVideoMode(_object);
            String hostname = readHostname(_object);
            Boolean prioritiesAutoSelect = readPrioritiesAutoSelect(_object);

            return new ServerInfos(
                    components,
                    adjusts,
                    effects,
                    ledMappingType,
                    videoMode,
                    hostname,
                    priorities,
                    prioritiesAutoSelect
            );
        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Priorities
    static PriorityInfo[] readPriorities(JSONArray _array) throws JSONException {
        PriorityInfo[] priorities = new PriorityInfo[_array.length()];
        for (int i = 0; i < priorities.length; i++){
            priorities[i] = readPriority(_array.getJSONObject(i));
        }
        return priorities;
    }

    static PriorityInfo readPriority(JSONObject _object){

        //Get only RGB, since HSV is depending on RGB in Hyperion code - no need to get that as well
        JSONObject values = JSONHelper.getObject(_object, "value");
        JSONArray rgb = null;
        if (values != null){
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
        for (int i = 0; i < effects.length; i++){
            effects[i] = readEffect(_array.getJSONObject(i));
        }
        return effects;
    }

    static EffectInfos readEffect(JSONObject _object){
        return new EffectInfos(
                JSONHelper.getObject(_object, "args"),
                JSONHelper.getString(_object, "file"),
                JSONHelper.getString(_object, "name"),
                JSONHelper.getString(_object, "script")
        );
    }
    //endregion

    //region Adjustments
    static AdjustmentsInfos[] readAdjustments(JSONArray _array) throws JSONException {
        AdjustmentsInfos[] adjustments = new AdjustmentsInfos[_array.length()];
        for (int i = 0; i < adjustments.length; i++){
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
    static ComponentsInfos readComponents(JSONArray _array) {
        try {
            //For order see ComponentsInfos
            return new ComponentsInfos(
                    parseComponentEntryToBoolean(_array.getJSONObject(0)),
                    parseComponentEntryToBoolean(_array.getJSONObject(1)),
                    parseComponentEntryToBoolean(_array.getJSONObject(2)),
                    parseComponentEntryToBoolean(_array.getJSONObject(3)),
                    parseComponentEntryToBoolean(_array.getJSONObject(4)),
                    parseComponentEntryToBoolean(_array.getJSONObject(5)),
                    parseComponentEntryToBoolean(_array.getJSONObject(6)),
                    parseComponentEntryToBoolean(_array.getJSONObject(7))
            );
        } catch (JSONException e) {
            Log.e("readComponents", "Can't read components");
            //e.printStackTrace();
        }
        return null;
    }

    public static Boolean parseComponentEntryToBoolean(JSONObject _entry) {
        //Check if this returns a valid result...
        String name = JSONHelper.getString(_entry, "name");

        try {
            if (name != null) {
                return JSONHelper.castEntryToBoolean(_entry); //...if yes, then cast whatever it is...
            }
        } catch (JSONException e) {
            Log.e("parseComponentEntryToBoolean", "Not able to cast " + name);
            //e.printStackTrace();
        }
        return null; //...if not, return false
    }
    //endregion

    //region Misc
    static ServerInfos.ImageToLedMappingTypes readLedMappingType(JSONObject _object){
        String type = JSONHelper.getString(_object,"imageToLedMappingType");
        return ServerInfos.castStringToLedMappingTyp(type);
    }

    static ServerInfos.VideoModes readVideoMode(JSONObject _object){
        String mode = JSONHelper.getString(_object,"videomode");
        return ServerInfos.castStringToVideoMode(mode);
    }

    static String readHostname(JSONObject _object){
        return JSONHelper.getString(_object, "hostname");
    }

    static Boolean readPrioritiesAutoSelect(JSONObject _object){
        return JSONHelper.getBoolean(_object, "priorities_autoselect");
    }
    //endregion
}
