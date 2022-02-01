package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.Types;

import org.json.JSONException;
import org.json.JSONObject;

public class ComponentsCommand implements ControlCommand{

    private final String COMMAND = "componentstate";

    private final Types.Component component;
    private final boolean state;

    /**
     * Set turn a component on or off
     * @param component The component, that state it should change
     * @param state The new state, that is to set
     */
    public ComponentsCommand(Types.Component component, boolean state) {
        this.component = component;
        this.state = state;
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        try {
            JSONObject componentState = new JSONObject();
            componentState.put("component", component.toString());
            componentState.put("state", state);

            JSONObject json = new JSONObject();
            json.put(ControlHelper.COMMAND_KEY, COMMAND);
            json.put(COMMAND, componentState);

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }
}
