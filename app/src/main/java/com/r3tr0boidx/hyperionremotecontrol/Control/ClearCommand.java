package com.r3tr0boidx.hyperionremotecontrol.Control;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONObject;

public class ClearCommand implements ControlCommand {

    static final String COMMAND = "";

    //required
    private final int priority;

    public ClearCommand(int priority) {

        this.priority = priority;
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        return null;
    }
}
