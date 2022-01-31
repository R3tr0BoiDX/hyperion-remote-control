package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.NetworkManager;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public interface ControlCommand {

    /**
     * The Hyperion JSON command
     */
    static final String COMMAND = "";

    /**
     * Execute the command
     * @return The response, that was given to that command. Containing the HTTP code, HTTP message and the JSON answer
     */
    public default Response execute(){
        JSONObject json = buildCommand();
        try {
            return NetworkManager.getInstance().postQuery(json);
        } catch (InterruptedException e) {
            Log.e("execute", "Can't execute " + COMMAND + " command!");
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Build the command
     * @return The executable JSON command
     */
    public JSONObject buildCommand();
}
