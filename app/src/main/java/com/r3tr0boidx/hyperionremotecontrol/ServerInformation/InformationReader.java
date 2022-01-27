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

    public static void readInfos(JSONObject _object) {
        try {
            Log.d("base", _object.toString());

            JSONArray ar = _object.getJSONArray("adjustment");
            JSONObject ob = ar.getJSONObject(0); //Sind mehrer, schelife drüber mittels .length
            AdjustmentsInfos adjustments = readAdjustments(ob);
            adjustments.print();

            //ComponentsInfos components = readComponents(_object.getJSONArray("components"));
        } catch (JSONException e) {
            Log.e("readInfos", "Can't read server infos");
            e.printStackTrace();
        }
    }

    //TODO: https://docs.hyperion-project.org/en/json/ServerInfo.html#effect-list

    static AdjustmentsInfos readAdjustments(JSONObject _object){
        try {
            return new AdjustmentsInfos(
                    _object.getBoolean("backlightColored"),
                    _object.getInt("backlightThreshold"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("blue")),
                    _object.getInt("brightness"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("cyan")),
                    _object.getDouble("gammaBlue"),
                    _object.getDouble("gammaGreen"),
                    _object.getDouble("gammaRed"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("green")),
                    _object.getString("id"),
                    JSONHelper.castEntryToColor(_object.getJSONArray("magenta")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("red")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("white")),
                    JSONHelper.castEntryToColor(_object.getJSONArray("yellow"))
                    );
        } catch (Exception e){
            Log.e("readComponents", "Can't read adjustments");
            //e.printStackTrace();
        }
        return null;
    }

    static ComponentsInfos readComponents(JSONArray _array) {
        try {
            //For order see https://docs.hyperion-project.org/en/json/ServerInfo.html#components
            //or ComponentsInfos.java
            return new ComponentsInfos(
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(0)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(1)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(2)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(3)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(4)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(5)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(6)),
                    JSONHelper.castEntryToBoolean(_array.getJSONObject(7))
            );
        } catch (JSONException e) {
            Log.e("readComponents", "Can't read components");
            //e.printStackTrace();
        }
        return null;
    }
}
