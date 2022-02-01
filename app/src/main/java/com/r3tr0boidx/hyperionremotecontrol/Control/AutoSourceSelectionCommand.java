package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.PriorityInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class AutoSourceSelectionCommand implements ControlCommand{

    public static final String COMMAND = "sourceselect";

    private final boolean auto;

    /**
     * Enable or disable auto select. Might be useful, after you have chosen a specific priority with an SourceSelectionCommand
     * @param auto Determines, if the priorities should get selected automatically
     */
    public AutoSourceSelectionCommand(boolean auto) {
        this.auto = auto;
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
            json.put("auto", auto);

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }
}
