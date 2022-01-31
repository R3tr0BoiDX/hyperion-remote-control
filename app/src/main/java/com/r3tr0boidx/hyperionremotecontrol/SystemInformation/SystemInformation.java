package com.r3tr0boidx.hyperionremotecontrol.SystemInformation;

public class SystemInformation {

    private final HyperionInfo hyperion;
    private final SystemInfo system;

    public SystemInformation(
            HyperionInfo hyperion,
            SystemInfo system) {
        this.hyperion = hyperion;
        this.system = system;
    }

    public String concatenatePrintableString() {
        return hyperion.printableString() + System.lineSeparator() +
                system.printableString() + System.lineSeparator();
    }

}
