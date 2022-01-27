//TODO: Subscribe, to not need to poll: https://docs.hyperion-project.org/en/json/subscribe.html

package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationReader {

    private final Response serverInfos;

    public InformationReader() throws JSONException, InterruptedException {
        serverInfos = getServerInfos();
        readResponse();
    }

    static Response getServerInfos() throws JSONException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("command", "serverinfo");
        return NetworkManager.getInstance().postQuery(json);
    }

    void readResponse() {
        try {
            if (serverInfos.getResponseCode() == HttpStatus.SC_OK) {
                if (serverInfos.getResponseBody().getString("command").equals("serverinfo")) {

                    JSONObject infos = serverInfos.getResponseBody().getJSONObject("info");
                    readInfos(infos);

                } else {
                    Log.w("readInformations", "Received no Server Informations");
                }
            }
        } catch (JSONException e) {
            Log.e("readInformations", "Not able to parse received data");
            e.printStackTrace();
        }
    }

    static void readInfos(JSONObject _object) {
        try {
            Log.d("base", _object.toString());


            JSONArray ar = _object.getJSONArray("adjustment");
            JSONObject ob = ar.getJSONObject(0); //Sind mehrer, schelife drüber mittels .length
            AdjustmentsInfos adjustments = readAdjustment(ob);
            adjustments.print();


            ComponentsInfos components = readComponents(_object.getJSONArray("components"));
            components.print();
        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
    }

    //TODO: https://docs.hyperion-project.org/en/json/ServerInfo.html#effect-list



    //region Components
    static AdjustmentsInfos[] readAdjustments(JSONArray _array) throws JSONException {
        AdjustmentsInfos[] adjustments = new AdjustmentsInfos[_array.length()];
        for (int i = 0; i < adjustments.length; i++){
            adjustments[i] = readAdjustment(_array.getJSONObject(i));
        }
        return adjustments;
    }

    static AdjustmentsInfos readAdjustment(JSONObject _object) {
        try {
            return new AdjustmentsInfos(
                    JSONHelper.getBoolean(_object, "backlightColored"),
                    JSONHelper.getInt(_object, "backlightThreshold"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("blue")),
                    JSONHelper.getInt(_object, "brightness"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("cyan")),
                    JSONHelper.getDouble(_object, "gammaBlue"),
                    JSONHelper.getDouble(_object, "gammaGreen"),
                    JSONHelper.getDouble(_object, "gammaRed"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("green")),
                    JSONHelper.getString(_object, "id"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("magenta")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("red")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("white")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("yellow"))
            );
        } catch (Exception e) {
            Log.e("readComponents", "Can't read adjustments");
            //e.printStackTrace();
        }
        return null;
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
