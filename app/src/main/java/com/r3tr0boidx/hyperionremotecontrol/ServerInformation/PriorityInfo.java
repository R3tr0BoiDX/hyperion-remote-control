package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Someone could also names this "sources"
public class PriorityInfo {
    private final Integer priority;         //The priority of this source, an integer between 0 and 255
    private final Integer duration_ms;      //Actual duration in ms until this priority is automatically deleted. This is shown if source is color or effect AND a specific duration higher than 0 is set (0 means indefinite).
    private final String owner;             //Contains additional information related to the componentId
    private final String componentId;       //A key belonging to a specific component that indicates the kind of input. Correlates to the ComponentInfo
    private final String origin;            //A named external setter of this source for reference purposes. "System" is default
    private final Boolean active;           //If "true" it is selectable for manual source selection
    private final Boolean visible;          //If "true" this source is displayed and pushed to the led device
    private final Integer value;            //If the source is a color AND color data is available, this will be the color in RGB (spares the HSV value from the API)

    PriorityInfo(
            Integer priority,
            Integer duration_ms,
            String owner,
            String componentId,
            String origin,
            Boolean active,
            Boolean visible,
            Integer value) {
        this.priority = priority;
        this.duration_ms = duration_ms;
        this.owner = owner;
        this.componentId = componentId;
        this.origin = origin;
        this.active = active;
        this.visible = visible;
        this.value = value;
    }

    static PriorityInfo[] readPriorities(JSONArray _array) throws JSONException {
        if (_array != null){
            PriorityInfo[] priorities = new PriorityInfo[_array.length()];
            for (int i = 0; i < priorities.length; i++) {
                priorities[i] = readPriority(_array.getJSONObject(i));
            }
            return priorities;
        }
        return new PriorityInfo[0];
    }

    private static PriorityInfo readPriority(JSONObject _object) {
        //Get only RGB, since HSV is depending on RGB in Hyperion code - no need to get that as well
        JSONObject values = JSONHelper.getObject(_object, "value");
        JSONArray rgb = null;
        if (values != null) {
            rgb = JSONHelper.getArray(values, "RGB");
        }

        return new PriorityInfo(
                JSONHelper.getInteger(_object, "priority"),
                JSONHelper.getInteger(_object, "duration_ms"),
                JSONHelper.getString(_object, "owner"),
                JSONHelper.getString(_object, "componentId"),
                JSONHelper.getString(_object, "origin"),
                JSONHelper.getBoolean(_object, "active"),
                JSONHelper.getBoolean(_object, "visible"),
                JSONHelper.castArrayToColor(rgb)
        );
    }

    public static String concatenatePrintableString(PriorityInfo[] _priorities) {
        StringBuilder sb = new StringBuilder();
        for (PriorityInfo in : _priorities) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        String print = "===PriorityInfo===" + System.lineSeparator() +
                "priority: " + priority + System.lineSeparator() +
                "duration_ms: " + duration_ms + System.lineSeparator() +
                "owner: " + owner + System.lineSeparator() +
                "componentId: " + componentId + System.lineSeparator() +
                "origin: " + origin + System.lineSeparator() +
                "active: " + active + System.lineSeparator() +
                "visible: " + visible + System.lineSeparator();
        if (value != null){
            print += "value: " + Color.valueOf(value) + System.lineSeparator();
        }
        return print;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getDuration_ms() {
        return duration_ms;
    }

    public String getOwner() {
        return owner;
    }

    public String getComponentId() {
        return componentId;
    }

    public String getOrigin() {
        return origin;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Integer getValue() {
        return value;
    }
}
