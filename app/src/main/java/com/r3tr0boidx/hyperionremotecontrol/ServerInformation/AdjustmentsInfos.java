package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;

public class AdjustmentsInfos {

    private final Boolean backlightColored;     //If true the backlight is colored, false it's white. Disabled for effect/color/image
    private final int backlightThreshold;       //minimum: 0 maximum 100. Step of 1. (Minimum brightness!) Disabled for effect/color/image

    private final int brightness;               //minimum: 0 maximum 100 step of 1
    private int brightnessCompensation;   //minimum: 0 maximum 100 step of 1, optional

    private final int red;      //Color
    private final int yellow;   //Color
    private final int green;    //Color
    private final int cyan;     //Color
    private final int blue;     //Color
    private final int magenta;  //Color
    private final int white;    //Color

    private final double gammaBlue;     //minimum: 0.1 maximum 5.0 step of 0.1
    private final double gammaGreen;    //minimum: 0.1 maximum 5.0 step of 0.1
    private final double gammaRed;      //minimum: 0.1 maximum 5.0 step of 0.1

    private final String id;    //Short identifier

    public AdjustmentsInfos(
            Boolean backlightColored,
            int backlightThreshold,
            int blue,
            int brightness,
            int cyan,
            double gammaBlue,
            double gammaGreen,
            double gammaRed,
            int green,
            String id,
            int magenta,
            int red,
            int white,
            int yellow) {
        this.backlightColored = backlightColored;
        this.backlightThreshold = backlightThreshold;
        this.blue = blue;
        this.brightness = brightness;
        this.cyan = cyan;
        this.gammaBlue = gammaBlue;
        this.gammaGreen = gammaGreen;
        this.gammaRed = gammaRed;
        this.green = green;
        this.id = id;
        this.magenta = magenta;
        this.red = red;
        this.white = white;
        this.yellow = yellow;
    }

    public void print() {
        Log.d("AdjustmentsInfos", "===Mandatory===");
        Log.d("AdjustmentsInfos", "backlightColored: " + backlightColored);
        Log.d("AdjustmentsInfos", "backlightThreshold: " + backlightThreshold);
        Log.d("AdjustmentsInfos", "blue: " + Color.valueOf(blue));
        Log.d("AdjustmentsInfos", "brightness: " + brightness);
        Log.d("AdjustmentsInfos", "brightness: " + brightness);
        Log.d("AdjustmentsInfos", "cyan: " + Color.valueOf(cyan));
        Log.d("AdjustmentsInfos", "gammaBlue: " + gammaBlue);
        Log.d("AdjustmentsInfos", "gammaGreen: " + gammaGreen);
        Log.d("AdjustmentsInfos", "gammaRed: " + gammaRed);
        Log.d("AdjustmentsInfos", "green: " + Color.valueOf(green));
        Log.d("AdjustmentsInfos", "id: " + id);
        Log.d("AdjustmentsInfos", "magenta: " + Color.valueOf(magenta));
        Log.d("AdjustmentsInfos", "red: " + Color.valueOf(red));
        Log.d("AdjustmentsInfos", "white: " + Color.valueOf(white));
        Log.d("AdjustmentsInfos", "yellow: " + Color.valueOf(yellow));

        Log.d("AdjustmentsInfos", "===Optional===");
        Log.d("AdjustmentsInfos", "backlightThreshold" + backlightThreshold);
    }

    public static void printAll(AdjustmentsInfos[] _adjustments){
        for (AdjustmentsInfos ad : _adjustments){
            ad.print();
        }
    }

    public void setBrightnessCompensation(Integer brightnessCompensation) {
        this.brightnessCompensation = brightnessCompensation;
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
