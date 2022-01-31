//TODO: Missing color pattern

package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.NetworkManager;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ColorCommand implements ControlCommand {

    public static final String COMMAND = "color";
    public static final int RECOMMENDED_PRIORITY = 50;

    //required
    private final int priority;
    private final int color;

    //not required
    private Integer duration;
    private String origin;

    /**
     * Create a new command, to control the color
     *
     * @param priority The priority of the command. Recommended is 50, min. is 2, max. is 99
     * @param color    The color, that is to set.
     */
    public ColorCommand(int priority, int color) {
        if (priority > 1 && priority < 100) {
            this.priority = priority;
            this.color = color;
        } else {
            throw new IllegalArgumentException("Priority was to high or low. Min. is 2, max. is 99. Given priority: " + priority);
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
            json.put(Controller.COMMAND_KEY, COMMAND);
            json.put("color", JSONHelper.castColorToArray(color));
            json.put("priority", priority);

            if (duration != null) {
                json.put("duration", duration);
            }

            if (origin != null) {
                json.put("origin", origin);
            }
            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Duration of color in ms. Indefinite by default
     *
     * @param duration The duration is ms
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * A short name of your application.
     *
     * @param origin Name of application, that send command. Max length is 20, min 4
     */
    public void setOrigin(String origin) {
        if (origin.length() > 3 && origin.length() < 21) {
            this.origin = origin;
        } else {
            throw new IllegalArgumentException("Name was either to short or to long. Min. 4, max. 20 characters. Given length: " + origin.length());
        }
    }
}
