package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import java.net.Inet4Address;
import java.net.URL;

public class SessionInfo {

    //Are we sure on those data types ? - Seems we are: https://github.com/hyperion-project/hyperion.ng/blob/master/include/bonjour/bonjourrecord.h

    private final Inet4Address address;
    private final String domain;
    private final String host;
    private final String name;
    private final Integer port;
    private final String type;

    public SessionInfo(
            Inet4Address address,
            String domain,
            String host,
            String name,
            Integer port,
            String type) {
        this.address = address;
        this.domain = domain;
        this.host = host;
        this.name = name;
        this.port = port;
        this.type = type;
    }

    public static String concatenatePrintableString(SessionInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (SessionInfo se : _instances) {
            sb.append(se.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        return "===InstanceInfo===" + System.lineSeparator() +
                "address: " + address + System.lineSeparator() +
                "domain: " + domain + System.lineSeparator() +
                "host: " + host + System.lineSeparator() +
                "name: " + name + System.lineSeparator() +
                "port: " + port + System.lineSeparator() +
                "type: " + type + System.lineSeparator();
    }

    public Inet4Address getAddress() {
        return address;
    }

    public String getDomain() {
        return domain;
    }

    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public Integer getPort() {
        return port;
    }

    public String getType() {
        return type;
    }
}