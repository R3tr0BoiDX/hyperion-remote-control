//TODO: Subscribe, to not need to poll: https://docs.hyperion-project.org/en/json/subscribe.html

package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationReader {

    public static ServerInfos readResponse(Response serverResponse) {
        try {
            if (serverResponse.getResponseCode() == HttpStatus.SC_OK) {
                if (serverResponse.getResponseBody().getString("command").equals("serverinfo")) {

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
            Log.d("base", _object.toString());

            ComponentsInfos components = readComponents(_object.getJSONArray("components"));
            AdjustmentsInfos[] adjusts = readAdjustments(_object.getJSONArray("adjustment"));
            EffectInfos[] effects = readEffects(_object.getJSONArray("effects"));

            ServerInfos.ImageToLedMappingTypes ledMappingType = readLedMappingType(_object);
            ServerInfos.VideoModes videoMode = readVideoMode(_object);
            String hostname = readHostname(_object);

            return new ServerInfos(
                    components,
                    adjusts,
                    effects,
                    ledMappingType,
                    videoMode,
                    hostname);

        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
        return null;
    }

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
                JSONHelper.getBoolean(_object, "backlightColored"),
                JSONHelper.getInt(_object, "backlightThreshold"),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "blue")),
                JSONHelper.getInt(_object, "brightness"),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "cyan")),
                JSONHelper.getDouble(_object, "gammaBlue"),
                JSONHelper.getDouble(_object, "gammaGreen"),
                JSONHelper.getDouble(_object, "gammaRed"),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "green")),
                JSONHelper.getString(_object, "id"),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "magenta")),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "red")),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "white")),
                JSONHelper.castEntryToColor(JSONHelper.getArray(_object, "yellow"))
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
}
