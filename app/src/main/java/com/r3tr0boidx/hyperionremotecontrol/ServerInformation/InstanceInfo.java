package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

public class InstanceInfo {
    private final String friendlyName;
    private final Integer instance;
    private final Boolean running;

    public InstanceInfo(
            String friendlyName,
            Integer instance,
            Boolean running) {
        this.friendlyName = friendlyName;
        this.instance = instance;
        this.running = running;
    }

    public static String concatenatePrintableString(InstanceInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (InstanceInfo in : _instances) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        return "===InstanceInfo===" + System.lineSeparator() +
                "friendlyName: " + friendlyName + System.lineSeparator() +
                "instance: " + instance + System.lineSeparator() +
                "running: " + running + System.lineSeparator();
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public Integer getInstance() {
        return instance;
    }

    public Boolean getRunning() {
        return running;
    }
}
