package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

public class AdjustmentsInfos {
    private final boolean backlightColored;
    private final int backlightThreshold;
    private final int blue;                     //Color
    private final int brightness;
    private final int cyan;                     //Color
    private final double gammaBlue;
    private final double gammaGreen;
    private final double gammaRed;
    private final int green;                    //Color
    private final String id;
    private final int magenta;                  //Color
    private final int red;                      //Color
    private final int white;                    //Color
    private final int yellow;                   //Color

    public AdjustmentsInfos(boolean backlightColored,
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
        Log.d("AdjustmentsInfos", "backlightColored" + backlightColored);
        Log.d("AdjustmentsInfos", "backlightThreshold" + backlightThreshold);
        Log.d("AdjustmentsInfos", "blue" + Color.valueOf(blue));
        Log.d("AdjustmentsInfos", "brightness" + brightness);
        Log.d("AdjustmentsInfos", "brightness" + brightness);
        Log.d("AdjustmentsInfos", "cyan" + Color.valueOf(cyan));
        Log.d("AdjustmentsInfos", "gammaBlue" + gammaBlue);
        Log.d("AdjustmentsInfos", "gammaGreen" + gammaGreen);
        Log.d("AdjustmentsInfos", "gammaRed" + gammaRed);
        Log.d("AdjustmentsInfos", "green" + Color.valueOf(green));
        Log.d("AdjustmentsInfos", "id" + id);
        Log.d("AdjustmentsInfos", "magenta" + Color.valueOf(magenta));
        Log.d("AdjustmentsInfos", "red" + Color.valueOf(red));
        Log.d("AdjustmentsInfos", "white" + Color.valueOf(white));
        Log.d("AdjustmentsInfos", "yellow" + Color.valueOf(yellow));
    }

    public boolean isBacklightColored() {
        return backlightColored;
    }

    public int getBacklightThreshold() {
        return backlightThreshold;
    }

    public int getBlue() {
        return blue;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getCyan() {
        return cyan;
    }

    public double getGammaBlue() {
        return gammaBlue;
    }

    public double getGammaGreen() {
        return gammaGreen;
    }

    public double getGammaRed() {
        return gammaRed;
    }

    public int getGreen() {
        return green;
    }

    public String getId() {
        return id;
    }

    public int getMagenta() {
        return magenta;
    }

    public int getRed() {
        return red;
    }

    public int getWhite() {
        return white;
    }

    public int getYellow() {
        return yellow;
    }
}
