//TODO: Missing effect args support

package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class EffectCommand implements ControlCommand {

    static final String COMMAND = "effect";
    public static final int RECOMMENDED_PRIORITY = 50;

    //required
    private final int priority;
    private final String effect;

    //not required
    private Integer duration;
    private String origin;

    /**
     * Create a new command, set an effect
     * The official Hyperion documentation recommends priorities between 2 and 99, optimally 50
     * @param priority The priority of the command. Recommended is 50, min. is 1, max. is 253
     * @param effect   The effect, that is to set
     */
    public EffectCommand(int priority, String effect) {
        if (priority > 0 && priority < 254) {
            this.priority = priority;
            this.effect = effect;
        } else {
            throw new IllegalArgumentException("Priority was to high or low. Min. is 1, max. is 253. Given priority: " + priority);
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

            JSONObject effectContainer = new JSONObject();
            effectContainer.put("name", effect);
            json.put("effect", effectContainer);

            json.put("priority", priority);

            if (duration != null) {
                json.put("duration", duration);
            }

            if (origin != null) {
                json.put("origin", origin);
            }
            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build ColorCommand");
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Duration of effect in ms. Indefinite by default
     *
     * @param duration The duration is ms
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * A short name of your application. Max length is 20, min 4
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
