package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LEDInfo {
    private final Double hmax;
    private final Double hmin;
    private final Double vmax;
    private final Double vmin;

    LEDInfo(
            Double hmax,
            Double hmin,
            Double vmax,
            Double vmin) {
        this.hmax = hmax;
        this.hmin = hmin;
        this.vmax = vmax;
        this.vmin = vmin;
    }

    static LEDInfo[] readLEDs(JSONArray _array) throws JSONException {
        if (_array != null){
            LEDInfo[] leds = new LEDInfo[_array.length()];
            for (int i = 0; i < leds.length; i++) {
                leds[i] = readLED(_array.getJSONObject(i));
            }
            return leds;
        }
        return new LEDInfo[0];
    }

    private static LEDInfo readLED(JSONObject _object) {
        return new LEDInfo(
                JSONHelper.getDouble(_object, "hmax"),
                JSONHelper.getDouble(_object, "hmin"),
                JSONHelper.getDouble(_object, "vmax"),
                JSONHelper.getDouble(_object, "vmin")
        );
    }

    public static String concatenatePrintableString(LEDInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (LEDInfo led : _instances) {
            sb.append(led.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        return "===LEDInfo===" + System.lineSeparator() +
                "hmax: " + hmax + System.lineSeparator() +
                "hmin: " + hmin + System.lineSeparator() +
                "vmax: " + vmax + System.lineSeparator() +
                "vmin: " + vmin + System.lineSeparator();
    }

    public Double getHmax() {
        return hmax;
    }

    public Double getHmin() {
        return hmin;
    }

    public Double getVmax() {
        return vmax;
    }

    public Double getVmin() {
        return vmin;
    }
}
