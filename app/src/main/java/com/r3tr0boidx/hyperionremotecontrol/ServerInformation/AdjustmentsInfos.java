package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

public class AdjustmentsInfos {

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

    public AdjustmentsInfos(
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

    public void print() {
        Log.d("AdjustmentsInfos", "===Optional===");
        Log.d("AdjustmentsInfos", "id: " + id);

        Log.d("AdjustmentsInfos", "red: " + Color.valueOf(red));
        Log.d("AdjustmentsInfos", "green: " + Color.valueOf(green));
        Log.d("AdjustmentsInfos", "blue: " + Color.valueOf(blue));
        Log.d("AdjustmentsInfos", "yellow: " + Color.valueOf(yellow));
        Log.d("AdjustmentsInfos", "cyan: " + Color.valueOf(cyan));
        Log.d("AdjustmentsInfos", "magenta: " + Color.valueOf(magenta));
        Log.d("AdjustmentsInfos", "white: " + Color.valueOf(white));

        Log.d("AdjustmentsInfos", "gammaBlue: " + gammaBlue);
        Log.d("AdjustmentsInfos", "gammaGreen: " + gammaGreen);
        Log.d("AdjustmentsInfos", "gammaRed: " + gammaRed);

        Log.d("AdjustmentsInfos", "backlightThreshold: " + backlightThreshold);
        Log.d("AdjustmentsInfos", "backlightColored: " + backlightColored);

        Log.d("AdjustmentsInfos", "brightness: " + brightness);
        Log.d("AdjustmentsInfos", "backlightThreshold" + backlightThreshold);
    }

    public static void printAll(AdjustmentsInfos[] _adjustments){
        for (AdjustmentsInfos ad : _adjustments){
            ad.print();
        }
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
