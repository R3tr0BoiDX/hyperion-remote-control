package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActiveEffectInfo extends EffectInfo {

    private final Integer priority;
    private final Integer timeout;

    ActiveEffectInfo(
            JSONObject args,
            String name,
            String script,
            Integer priority,
            Integer timeout) {
        super(args, null, name, script);
        this.priority = priority;
        this.timeout = timeout;
    }

    static ActiveEffectInfo[] readActiveEffects(JSONArray _array) throws JSONException {
        if (_array != null){
            ActiveEffectInfo[] effects = new ActiveEffectInfo[_array.length()];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = (ActiveEffectInfo) EffectInfo.readEffect(_array.getJSONObject(i), true);
            }
            return effects;
        }
        return new ActiveEffectInfo[0];
    }

    public static String concatenatePrintableString(ActiveEffectInfo[] _effects) {
        StringBuilder sb = new StringBuilder();
        for (ActiveEffectInfo ef : _effects) {
            sb.append(ef.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
        String printable = super.printableString(true);
        printable += "===Active Attributes===" + System.lineSeparator() +
                "priority: " + priority + System.lineSeparator() +
                "timeout: " + timeout + System.lineSeparator();

        return printable;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getTimeout() {
        return timeout;
    }

    //Not given as active effect
    @Override
    public String getFile() {
        return null;
    }

    //systemEffects bases on file
    @Override
    public Boolean isSystemEffect(String _file) {
        return null;
    }
}
