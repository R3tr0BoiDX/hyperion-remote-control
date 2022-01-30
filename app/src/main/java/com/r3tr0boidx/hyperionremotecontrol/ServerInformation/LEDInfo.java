package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

public class LEDInfo {
    private final Double hmax;
    private final Double hmin;
    private final Double vmax;
    private final Double vmin;

    public LEDInfo(Double hmax, Double hmin, Double vmax, Double vmin) {
        this.hmax = hmax;
        this.hmin = hmin;
        this.vmax = vmax;
        this.vmin = vmin;
    }

    public static String concatenatePrintableString(LEDInfo[] _instances) {
        StringBuilder sb = new StringBuilder();
        for (LEDInfo led : _instances) {
            sb.append(led.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        return "===LEDInfo===" + System.lineSeparator() +
                "hmax: " + hmax + System.lineSeparator() +
                "hmin: " + hmin + System.lineSeparator() +
                "vmax: " + vmax + System.lineSeparator() +
                "vmin: " + vmin + System.lineSeparator();
    }

    public Double getHmax() {
        return hmax;
    }

    public Double getHmin() {
        return hmin;
    }

    public Double getVmax() {
        return vmax;
    }

    public Double getVmin() {
        return vmin;
    }
}
