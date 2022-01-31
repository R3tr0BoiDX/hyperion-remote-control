package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

public class GrabbersInfo {
    private final String[] active;
    private final String[] available;

    public GrabbersInfo(
            String[] active,
            String[] available) {
        this.active = active;
        this.available = available;
    }

    public static String concatenatePrintableString(GrabbersInfo _grabbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("===GrabbersInfo===").append(System.lineSeparator());

        //Append active grabbers
        for (String s : _grabbers.active) {
            sb.append("active: ").append(s).append(System.lineSeparator());
        }

        //Append available grabbers
        for (String s : _grabbers.available) {
            sb.append("available: ").append(s).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String[] getActive() {
        return active;
    }

    public String[] getAvailable() {
        return available;
    }
}
