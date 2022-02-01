package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.InstanceInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class InstancesCommand implements ControlCommand{

    public static final String COMMAND = "instance";

    private final InstancesCommandType subcommand;
    private final InstanceInfo instance;

    public InstancesCommand(InstancesCommandType subcommand, InstanceInfo instance) {
        this.subcommand = subcommand;
        this.instance = instance;
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        try {
            if (instance != null){
                JSONObject json = new JSONObject();
                json.put(ControlHelper.COMMAND_KEY, COMMAND);
                json.put("subcommand", subcommand.toString());
                json.put("instance", instance.getInstance());

                return json;
            } else {
                Log.e("buildCommand", "Can't construct " + COMMAND + " command, because instance is empty");
            }
        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }

        return null;
    }

    public enum InstancesCommandType{
        startInstance,
        stopInstance,
        switchTo
    }
}
