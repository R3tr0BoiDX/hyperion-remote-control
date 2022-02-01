package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Types;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class LedMappingCommand implements ControlCommand{

    private final String COMMAND = "processing";

    private final Types.ImageToLedMappingTypes type;

    /**
     * Switch the image to led mapping mode.
     * @param type Possible values are unicolor_mean (led color based on whole picture color) and multicolor_mean (led colors based on led layout)
     */
    public LedMappingCommand(Types.ImageToLedMappingTypes type) {
        this.type = type;
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        try {
            JSONObject json = new JSONObject();
            json.put(ControlHelper.COMMAND_KEY, COMMAND);
            json.put("mappingType", type.toString());

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }
}
