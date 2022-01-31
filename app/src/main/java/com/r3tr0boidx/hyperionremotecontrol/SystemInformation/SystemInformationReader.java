package com.r3tr0boidx.hyperionremotecontrol.SystemInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

//These information are static and won't change during runtime.
public class SystemInformationReader {

    //region Basics
    public static SystemInformation readResponse(Response serverResponse) {
        try {
            if (serverResponse.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (serverResponse.getResponseBody().getString("command").equals("sysinfo")  //Check it was the right command
                        && serverResponse.getResponseBody().getBoolean("success")) {                //Check if it was successful

                    JSONObject infos = serverResponse.getResponseBody().getJSONObject("info");
                    return readInfos(infos);
                } else {
                    Log.w("readInformations", "Received no system informations");
                }
            }
        } catch (JSONException e) {
            Log.e("readResponse", "Not able to parse received data");
            e.printStackTrace();
        }
        return null;
    }

    static SystemInformation readInfos(JSONObject _object) {
        try {
            HyperionInfo hyperion = HyperionInfo.readHyperion(JSONHelper.getObject(_object, "hyperion"));
            SystemInfo system = SystemInfo.readSystem(JSONHelper.getObject(_object, "system"));

            return new SystemInformation(
                    hyperion,
                    system);
        } catch (Exception e) {
            Log.e("readInfos", "Can't read system infos");
            e.printStackTrace();
        }
        return null;
    }
}
