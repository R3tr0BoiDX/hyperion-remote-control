package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Modes;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoModeCommand implements ControlCommand{
    private final String COMMAND = "videomode";

    private final Modes.VideoModes mode;

    public VideoModeCommand(Modes.VideoModes mode) {
        this.mode = mode;
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

            String videoMode = JSONHelper.castVideoModeToString(mode);
            if (videoMode != null){
                json.put("videoMode", videoMode);
            } else {
                throw new IllegalArgumentException("Can't cast " + mode.toString() + " to a valid string");
            }

            return json;

        } catch (JSONException | IllegalArgumentException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            Log.e("buildCommand", e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }
}
