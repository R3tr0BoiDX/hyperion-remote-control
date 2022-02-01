package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ClearCommand implements ControlCommand {

    static final String COMMAND = "clear";
    public static final int RECOMMENDED_PRIORITY = 50;

    //required
    private final int priority;

    /**
     * Create a new command, to set a image as source for the colors
     * The official Hyperion documentation recommends priorities between 2 and 99, optimally 50
     * @param priority The priority of the command. Recommended is 50, min. is -1, max. is 253.
     *                 Needs to be >= than the current effect/color/image. -1 clears all.
     */
    public ClearCommand(int priority) {
        if (priority > -2 && priority < 254) {
            this.priority = priority;
        } else {
            throw new IllegalArgumentException("Priority was to high or low. Min. is -1, max. is 253. Given priority: " + priority);
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
            json.put("priority", priority);

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;    }
}
