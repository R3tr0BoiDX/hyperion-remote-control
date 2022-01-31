package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import org.json.JSONObject;

public class ActiveEffectInfo extends EffectInfos {

    private final Integer priority;
    private final Integer timeout;

    public ActiveEffectInfo(
            JSONObject args,
            String name,
            String script,
            Integer priority,
            Integer timeout) {
        super(args, null, name, script);
        this.priority = priority;
        this.timeout = timeout;
    }

    public static String concatenatePrintableString(ActiveEffectInfo[] _effects) {
        StringBuilder sb = new StringBuilder();
        for (ActiveEffectInfo ef : _effects) {
            sb.append(ef.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        String printable = super.printableString();
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
    protected Boolean isSystemEffect(String _file) {
        return null;
    }
}
