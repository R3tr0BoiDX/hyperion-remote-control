package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class GrabbersInfo {
    private final String[] active;
    private final String[] available;

    GrabbersInfo(
            String[] active,
            String[] available) {
        this.active = active;
        this.available = available;
    }

    static GrabbersInfo readGrabbers(JSONObject _object){
        return new GrabbersInfo(
                readStringArray(_object, "active"),
                readStringArray(_object, "available")
        );
    }

    private static String[] readStringArray(JSONObject _object, String _name){
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

    static String concatenatePrintableString(GrabbersInfo _grabbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("===GrabbersInfo===").append(System.lineSeparator());

        //Append active grabbers
        for (String s : _grabbers.active) {
            sb.append("active: ").append(s).append(System.lineSeparator());
        }

        //Append available grabbers
        for (String s : _grabbers.available) {
            sb.append("available: ").append(s).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String[] getActive() {
        return active;
    }

    public String[] getAvailable() {
        return available;
    }
}
