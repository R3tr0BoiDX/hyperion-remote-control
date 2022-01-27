package com.r3tr0boidx.hyperionremotecontrol;

import android.graphics.Color;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
    public static boolean castEntryToBoolean(JSONObject _entry) throws JSONException {
        String entryState = _entry.getString("enabled");
        return Boolean.parseBoolean(entryState.toLowerCase());
    }

    public static int castEntryToColor(JSONArray _entry) throws JSONException {
        if (_entry.length() == 3){
            int red = _entry.getInt(0);
            int green = _entry.getInt(1);
            int blue = _entry.getInt(2);

            return Color.rgb(red, green, blue);
        } else {
            throw new JSONException("Not a color");
        }
    }
}
