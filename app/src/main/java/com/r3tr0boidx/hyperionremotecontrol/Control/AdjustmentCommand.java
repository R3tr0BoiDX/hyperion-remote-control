package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class AdjustmentCommand implements ControlCommand {

    public static final String COMMAND = "adjustment";

    private String id;    //Short identifier

    private Integer red;      //Color
    private Integer green;    //Color
    private Integer blue;     //Color
    private Integer yellow;   //Color
    private Integer cyan;     //Color
    private Integer magenta;  //Color
    private Integer white;    //Color

    private Double gammaBlue;     //minimum: 0.1 maximum 5.0 step of 0.1
    private Double gammaGreen;    //minimum: 0.1 maximum 5.0 step of 0.1
    private Double gammaRed;      //minimum: 0.1 maximum 5.0 step of 0.1

    private Integer backlightThreshold;       //minimum: 0 maximum 100. Step of 1
    private Boolean backlightColored;     //If true the backlight is colored, false it's white

    private Integer brightness;               //minimum: 0 maximum 100 step of 1
    private Integer brightnessCompensation;   //minimum: 0 maximum 100 step of 1

    /**
     * Adjustments reflect the color calibration
     */
    public AdjustmentCommand() {
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        try {
            JSONObject adjustment = new JSONObject();

            if (id != null) {
                adjustment.put("id", id);
            }

            if (red != null) {
                adjustment.put("red", JSONHelper.castColorToArray(red));
            }

            if (green != null){
                adjustment.put("green", JSONHelper.castColorToArray(green));
            }

            if (blue != null){
                adjustment.put("blue", JSONHelper.castColorToArray(blue));
            }

            if (yellow != null){
                adjustment.put("yellow", JSONHelper.castColorToArray(yellow));
            }

            if (cyan != null){
                adjustment.put("cyan", JSONHelper.castColorToArray(cyan));
            }

            if (magenta != null){
                adjustment.put("magenta", JSONHelper.castColorToArray(magenta));
            }

            if (gammaBlue != null) {
                adjustment.put("gammaBlue", gammaBlue);
            }

            if (gammaGreen != null) {
                adjustment.put("gammaGreen", gammaGreen);
            }

            if (gammaRed != null){
                adjustment.put("gammaRed", gammaRed);
            }

            if (backlightThreshold != null){
                adjustment.put("backlightThreshold", backlightThreshold);
            }

            if (backlightColored != null){
                adjustment.put("backlightColored", backlightColored);
            }

            if (brightness != null){
                adjustment.put("brightness", brightness);
            }

            if (brightnessCompensation != null){
                adjustment.put("brightnessCompensation", brightnessCompensation);
            }

            JSONObject json = new JSONObject();
            json.put(ControlHelper.COMMAND_KEY, COMMAND);
            json.put(COMMAND, adjustment);

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }

        return null;
    }

    /**
     * ID is a short identifier
     * @param id The identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set what is supposed to be red
     * @param red Adjusted red value
     */
    public void setRed(int red) {
        this.red = red;
    }

    /**
     * Set what is supposed to be green
     * @param green Adjusted red value
     */
    public void setGreen(int green) {
        this.green = green;
    }

    /**
     * Set what is supposed to be blue
     * @param blue Adjusted red value
     */
    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     * Set what is supposed to be yellow
     * @param yellow Adjusted red value
     */
    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    /**
     * Set what is supposed to be cyan
     * @param cyan Adjusted red value
     */
    public void setCyan(int cyan) {
        this.cyan = cyan;
    }

    /**
     * Set what is supposed to be magenta
     * @param magenta Adjusted red value
     */
    public void setMagenta(int magenta) {
        this.magenta = magenta;
    }

    /**
     * Set what is supposed to be white
     * @param white Adjusted red value
     */
    public void setWhite(int white) {
        this.white = white;
    }

    /**
     * Set the gamma blue value. Inputs must be between 0.1 and 5 in steps of 0.1
     * @param gammaBlue The new gamma blue value
     */
    public void setGammaBlue(double gammaBlue) {
        if (ControlHelper.checkRange(gammaBlue, 0.1, 5.0)){
            this.gammaBlue = gammaBlue;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(gammaBlue, 0.1, 5.0, "Gamma blue")
            );
        }
    }

    /**
     * Set the gamma green value. Inputs must be between 0.1 and 5 in steps of 0.1
     * @param gammaGreen The new gamma green value
     */
    public void setGammaGreen(double gammaGreen) {
        if (ControlHelper.checkRange(gammaGreen, 0.1, 5.0)){
            this.gammaGreen = gammaGreen;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(gammaGreen, 0.1, 5, "Gamma green")
            );
        }
    }

    /**
     * Set the gamma red value. Inputs must be between 0.1 and 5 in steps of 0.1
     * @param gammaRed The new gamma red value
     */
    public void setGammaRed(double gammaRed) {
        if (ControlHelper.checkRange(gammaRed, 0.1, 5.0)){
            this.gammaRed = gammaRed;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(gammaRed, 0.1, 5, "Gamma red")
            );
        }
    }

    /**
     * Set the backlight threshold. Pay attention to your minimum brightness.
     * Must be between 0 and 100.
     * Disabled for effect/color/image
     * @param backlightThreshold The backlight threshold, that is to set
     */
    public void setBacklightThreshold(int backlightThreshold) {
        if (ControlHelper.checkRange(backlightThreshold, 0, 100)){
            this.backlightThreshold = backlightThreshold;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(backlightThreshold, 0, 100, "Backlight threshold")
            );
        }
    }

    /**
     * Set if the backlight is colored.
     * Disabled for effect/color/image
     * @param backlightColored If true the backlight is colored, false it's white.
     */
    public void setBacklightColored(boolean backlightColored) {
        this.backlightColored = backlightColored;
    }

    /**
     * Set the brightness.
     * Must be between 0 and 100.
     * @param brightness The brightness, that is to set
     */
    public void setBrightness(int brightness) {
        if (ControlHelper.checkRange(brightness, 0, 100)){
            this.brightness = brightness;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(brightness, 0, 100, "Brightness")
            );
        }
    }

    /**
     * Set the brightness compensation.
     * Must be between 0 and 100.
     * @param brightnessCompensation The brightness compensation, that is to set
     */
    public void setBrightnessCompensation(int brightnessCompensation) {
        if (ControlHelper.checkRange(brightnessCompensation, 0, 100)){
            this.brightnessCompensation = brightnessCompensation;
        } else {
            throw new IllegalArgumentException(
                    ControlHelper.getRangeErrorMessage(brightnessCompensation, 0, 100, "Brightness compensation")
            );
        }
    }
}
