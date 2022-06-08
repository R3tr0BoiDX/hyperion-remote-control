package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Networking.NetworkManager;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONObject;

//TODO: Implement a valiidate method, to check if command was successfull

public interface ControlCommand {

    /**
     * The Hyperion JSON command
     */
     String COMMAND = "";

    /**
     * Execute the command
     * @return The response, that was given to that command. Containing the HTTP code, HTTP message and the JSON answer
     */
    default Response execute(){
        JSONObject json = buildCommand();
        return NetworkManager.getInstance().writeQuery(json);
    }

    /**
     * Build the command
     * @return The executable JSON command
     */
    JSONObject buildCommand();
}
