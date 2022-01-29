package com.r3tr0boidx.hyperionremotecontrol;

import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {

    public static Boolean getBoolean(JSONObject _object, String _name){
        try {
            return _object.getBoolean(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static Integer getInteger(JSONObject _object, String _name){
        try {
            return _object.getInt(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static Double getDouble(JSONObject _object, String _name){
        try {
            return _object.getDouble(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static String getString(JSONObject _object, String _name){
        try {
            return _object.getString(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getObject(JSONObject _object, String _name){
        try {
            return _object.getJSONObject(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getArray(JSONObject _object, String _name){
        try {
            return _object.getJSONArray(_name);
        } catch (JSONException e) {
            Log.w("JSONHelper", "Didnt found " + _name);
            //e.printStackTrace();
        }
        return null;
    }

    public static Boolean castEntryToBoolean(JSONObject _entry) throws JSONException {
        String entryState = _entry.getString("enabled");
        return Boolean.parseBoolean(entryState.toLowerCase());
    }

    public static Integer castArrayToColor(JSONArray _entry) {
        if (_entry != null){
            try {
                if (_entry.length() == 3){
                    int red = _entry.getInt(0);
                    int green = _entry.getInt(1);
                    int blue = _entry.getInt(2);

                    return Color.rgb(red, green, blue);
                } else {
                    throw new IllegalArgumentException("Not a color");
                }
            } catch (JSONException e) {
                Log.w("castEntryToColor", "Can't construct color");
                //e.printStackTrace();
            }
        }
        return null;
    }
}
