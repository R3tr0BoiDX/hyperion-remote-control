package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdjustmentsInfo {

    private final String id;    //Short identifier

    private final Integer red;      //Color
    private final Integer green;    //Color
    private final Integer blue;     //Color
    private final Integer yellow;   //Color
    private final Integer cyan;     //Color
    private final Integer magenta;  //Color
    private final Integer white;    //Color

    private final Double gammaBlue;     //minimum: 0.1 maximum 5.0 step of 0.1
    private final Double gammaGreen;    //minimum: 0.1 maximum 5.0 step of 0.1
    private final Double gammaRed;      //minimum: 0.1 maximum 5.0 step of 0.1

    private final Integer backlightThreshold;       //minimum: 0 maximum 100. Step of 1. (Minimum brightness!) Disabled for effect/color/image
    private final Boolean backlightColored;     //If true the backlight is colored, false it's white. Disabled for effect/color/image

    private final Integer brightness;               //minimum: 0 maximum 100 step of 1
    private final Integer brightnessCompensation;   //minimum: 0 maximum 100 step of 1, optional

    AdjustmentsInfo(
            String id,
            Integer red,
            Integer green,
            Integer blue,
            Integer yellow,
            Integer cyan,
            Integer magenta,
            Integer white,
            Double gammaBlue,
            Double gammaGreen,
            Double gammaRed,
            Integer backlightThreshold,
            Boolean backlightColored,
            Integer brightness,
            Integer brightnessCompensation) {
        this.id = id;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.yellow = yellow;
        this.cyan = cyan;
        this.magenta = magenta;
        this.white = white;
        this.gammaBlue = gammaBlue;
        this.gammaGreen = gammaGreen;
        this.gammaRed = gammaRed;
        this.backlightThreshold = backlightThreshold;
        this.backlightColored = backlightColored;
        this.brightness = brightness;
        this.brightnessCompensation = brightnessCompensation;
    }

    static AdjustmentsInfo[] readAdjustments(JSONArray _array) throws JSONException {
        if (_array != null){
            AdjustmentsInfo[] adjustments = new AdjustmentsInfo[_array.length()];
            for (int i = 0; i < adjustments.length; i++) {
                adjustments[i] = readAdjustment(_array.getJSONObject(i));
            }
            return adjustments;
        }
        return new AdjustmentsInfo[0];
    }

    private static AdjustmentsInfo readAdjustment(JSONObject _object) {
        return new AdjustmentsInfo(
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

    public static String concatenatePrintableString(AdjustmentsInfo[] _adjustments) {
        StringBuilder sb = new StringBuilder();
        for (AdjustmentsInfo in : _adjustments) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        return "===AdjustmentsInfo===" + System.lineSeparator() +
                "id: " + id + System.lineSeparator() +

                "red: " + Color.valueOf(red) + System.lineSeparator() +
                "green: " + Color.valueOf(green) + System.lineSeparator() +
                "blue: " + Color.valueOf(blue) + System.lineSeparator() +
                "yellow: " + Color.valueOf(yellow) + System.lineSeparator() +
                "cyan: " + Color.valueOf(cyan) + System.lineSeparator() +
                "magenta: " + Color.valueOf(magenta) + System.lineSeparator() +
                "white: " + Color.valueOf(white) + System.lineSeparator() +

                "gammaBlue: " + gammaBlue + System.lineSeparator() +
                "gammaGreen: " + gammaGreen + System.lineSeparator() +
                "gammaRed: " + gammaRed + System.lineSeparator() +

                "backlightThreshold: " + backlightThreshold + System.lineSeparator() +
                "backlightColored: " + backlightColored + System.lineSeparator() +

                "brightness: " + brightness + System.lineSeparator() +
                "brightnessCompensation: " + brightnessCompensation + System.lineSeparator();
    }

    public Boolean getBacklightColored() {
        return backlightColored;
    }

    public Integer getBacklightThreshold() {
        return backlightThreshold;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public Integer getBrightnessCompensation() {
        return brightnessCompensation;
    }

    public Integer getRed() {
        return red;
    }

    public Integer getYellow() {
        return yellow;
    }

    public Integer getGreen() {
        return green;
    }

    public Integer getCyan() {
        return cyan;
    }

    public Integer getBlue() {
        return blue;
    }

    public Integer getMagenta() {
        return magenta;
    }

    public Integer getWhite() {
        return white;
    }

    public Double getGammaBlue() {
        return gammaBlue;
    }

    public Double getGammaGreen() {
        return gammaGreen;
    }

    public Double getGammaRed() {
        return gammaRed;
    }

    public String getId() {
        return id;
    }
}
