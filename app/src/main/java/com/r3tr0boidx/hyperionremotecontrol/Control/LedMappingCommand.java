package com.r3tr0boidx.hyperionremotecontrol.Control;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONObject;

public class LedMappingCommand implements ControlCommand{



    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        return null;
    }
}
