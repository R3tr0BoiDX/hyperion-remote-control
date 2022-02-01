package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.PriorityInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class SourceSelectionCommand implements ControlCommand{

    public static final String COMMAND = "sourceselect";

    private final PriorityInfo priority;

    /**
     * Set a specific source as visible
     * The select priorities must be active!
     * priorities_autoselect switches to false, if successful
     * @param priority The priority that is to become visible
     */
    public SourceSelectionCommand(PriorityInfo priority) {
        if (priority.getActive()){
            this.priority = priority;
        } else {
            throw new IllegalArgumentException("Priority " + priority.getPriority() + " is not active!");
        }
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
            json.put("priority", priority.getPriority());

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }
}
