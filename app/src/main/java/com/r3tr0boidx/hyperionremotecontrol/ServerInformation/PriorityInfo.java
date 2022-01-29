package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

//Someone could also names this "sources"
public class PriorityInfo {
    private final Integer priority;         //The priority of this source, an integer between 0 and 255
    private final Integer duration_ms;      //Actual duration in ms until this priority is automatically deleted. This is shown if source is color or effect AND a specific duration higher than 0 is set (0 means indefinite).
    private final String owner;             //Contains additional information related to the componentId
    private final String componentId;       //A key belonging to a specific component that indicates the kind of input. Correlates to the ComponentInfos
    private final String origin;            //A named external setter of this source for reference purposes. "System" is default
    private final Boolean active;           //If "true" it is selectable for manual source selection
    private final Boolean visible;          //If "true" this source is displayed and pushed to the led device
    private final Integer value;            //If the source is a color AND color data is available, this will be the color in RGB (spares the HSV value from the API)

    public PriorityInfo(
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

    public static void printAll(PriorityInfo[] _priorities) {
        for (PriorityInfo pr : _priorities) {
            pr.print();
        }
    }

    public void print() {
        Log.d("PriorityInfo", "===Required===");
        Log.d("PriorityInfo", "priority: " + priority);
        Log.d("PriorityInfo", "duration_ms: " + duration_ms);
        Log.d("PriorityInfo", "owner: " + owner);
        Log.d("PriorityInfo", "componentId: " + componentId);
        Log.d("PriorityInfo", "origin: " + origin);
        Log.d("PriorityInfo", "active: " + active);
        Log.d("PriorityInfo", "visible: " + visible);
        if (value != null){
            Log.d("PriorityInfo", "value: " + Color.valueOf(value));
        }
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
