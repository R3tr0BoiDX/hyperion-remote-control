package com.r3tr0boidx.hyperionremotecontrol.SystemInformation;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONObject;

//Name is confusing - Tell me a better one
public class SystemInfo {
    private final String architecture;
    private final String cpuHardware;
    private final String cpuModelName;
    private final String cpuModelType;
    private final String cpuRevision;
    private final String domainName;
    private final String hostName;
    private final Boolean isUserAdmin;
    private final String kernelType;
    private final String kernelVersion;
    private final String prettyName;
    private final String productType;
    private final String productVersion;
    private final String pyVersion;
    private final String qtVersion;
    private final String wordSize;

    public SystemInfo(
            String architecture,
            String cpuHardware,
            String cpuModelName,
            String cpuModelType,
            String cpuRevision,
            String domainName,
            String hostName,
            Boolean isUserAdmin,
            String kernelType,
            String kernelVersion,
            String prettyName,
            String productType,
            String productVersion,
            String pyVersion,
            String qtVersion,
            String wordSize) {
        this.architecture = architecture;
        this.cpuHardware = cpuHardware;
        this.cpuModelName = cpuModelName;
        this.cpuModelType = cpuModelType;
        this.cpuRevision = cpuRevision;
        this.domainName = domainName;
        this.hostName = hostName;
        this.isUserAdmin = isUserAdmin;
        this.kernelType = kernelType;
        this.kernelVersion = kernelVersion;
        this.prettyName = prettyName;
        this.productType = productType;
        this.productVersion = productVersion;
        this.pyVersion = pyVersion;
        this.qtVersion = qtVersion;
        this.wordSize = wordSize;
    }

    public String printableString() {
        return "===SystemInfo===" + System.lineSeparator() +
                "architecture: " + architecture + System.lineSeparator() +
                "cpuHardware: " + cpuHardware + System.lineSeparator() +
                "cpuModelName: " + cpuModelName + System.lineSeparator() +
                "cpuModelType: " + cpuModelType + System.lineSeparator() +
                "cpuRevision: " + cpuRevision + System.lineSeparator() +
                "domainName: " + domainName + System.lineSeparator() +
                "hostName: " + hostName + System.lineSeparator() +
                "isUserAdmin: " + isUserAdmin + System.lineSeparator() +
                "kernelType: " + kernelType + System.lineSeparator() +
                "kernelVersion: " + kernelVersion + System.lineSeparator() +
                "prettyName: " + prettyName + System.lineSeparator() +
                "productType: " + productType + System.lineSeparator() +
                "productVersion: " + productVersion + System.lineSeparator() +
                "pyVersion: " + pyVersion + System.lineSeparator() +
                "qtVersion: " + qtVersion + System.lineSeparator() +
                "wordSize: " + wordSize + System.lineSeparator();
    }

    static SystemInfo readSystem(JSONObject _object) {
        return new SystemInfo(
                JSONHelper.getString(_object, "architecture"),
                JSONHelper.getString(_object, "cpuHardware"),
                JSONHelper.getString(_object, "cpuModelName"),
                JSONHelper.getString(_object, "cpuModelType"),
                JSONHelper.getString(_object, "cpuRevision"),
                JSONHelper.getString(_object, "domainName"),
                JSONHelper.getString(_object, "hostName"),
                JSONHelper.getBoolean(_object, "isUserAdmin"),
                JSONHelper.getString(_object, "kernelType"),
                JSONHelper.getString(_object, "kernelVersion"),
                JSONHelper.getString(_object, "prettyName"),
                JSONHelper.getString(_object, "productType"),
                JSONHelper.getString(_object, "productVersion"),
                JSONHelper.getString(_object, "pyVersion"),
                JSONHelper.getString(_object, "qtVersion"),
                JSONHelper.getString(_object, "wordSize")
        );
    }
}