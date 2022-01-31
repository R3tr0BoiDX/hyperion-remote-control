package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

public class TransformInfo {
    private final String id;
    private final Integer luminanceGain;
    private final Integer luminanceMinimum;
    private final Integer saturationGain;
    private final Integer saturationLGain;
    private final Integer valueGain;

    private final Double[] blackLevel;
    private final Double[] whiteLevel;
    private final Double[] gamma;
    private final Double[] threshold;

    public TransformInfo(
            String id,
            Integer luminanceGain,
            Integer luminanceMinimum,
            Integer saturationGain,
            Integer saturationLGain,
            Integer valueGain,
            Double[] blackLevel,
            Double[] whiteLevel,
            Double[] gamma,
            Double[] threshold) {
        this.id = id;
        this.luminanceGain = luminanceGain;
        this.luminanceMinimum = luminanceMinimum;
        this.saturationGain = saturationGain;
        this.saturationLGain = saturationLGain;
        this.valueGain = valueGain;
        this.blackLevel = blackLevel;
        this.whiteLevel = whiteLevel;
        this.gamma = gamma;
        this.threshold = threshold;
    }

    public static String concatenatePrintableString(TransformInfo[] _transform) {
        StringBuilder sb = new StringBuilder();
        for (TransformInfo in : _transform) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString() {
        StringBuilder stringBuilderBlackLevel = new StringBuilder();
        for (Double d : blackLevel){
            stringBuilderBlackLevel.append(d).append(" ");
        }

        StringBuilder stringBuilderWhiteLevel = new StringBuilder();
        for (Double d : whiteLevel){
            stringBuilderWhiteLevel.append(d).append(" ");
        }

        StringBuilder stringBuilderGamma = new StringBuilder();
        for (Double d : gamma){
            stringBuilderGamma.append(d).append(" ");
        }

        StringBuilder stringBuilderThreshold = new StringBuilder();
        for (Double d : threshold){
            stringBuilderThreshold.append(d).append(" ");
        }

        return "===TransformInfo===" + System.lineSeparator() +
                "id: " + id + System.lineSeparator() +
                "luminanceGain: " + luminanceGain + System.lineSeparator() +
                "luminanceMinimum: " + luminanceMinimum + System.lineSeparator() +
                "saturationGain: " + saturationGain + System.lineSeparator() +
                "saturationLGain: " + saturationLGain + System.lineSeparator() +
                "valueGain: " + valueGain + System.lineSeparator() +

                "blackLevel: " + stringBuilderBlackLevel.toString() + System.lineSeparator() +
                "whiteLevel: " + stringBuilderWhiteLevel.toString() + System.lineSeparator() +
                "gamma: " + stringBuilderGamma.toString() + System.lineSeparator() +
                "threshold: " + stringBuilderThreshold.toString() + System.lineSeparator();
    }

    public String getId() {
        return id;
    }

    public Integer getLuminanceGain() {
        return luminanceGain;
    }

    public Integer getLuminanceMinimum() {
        return luminanceMinimum;
    }

    public Integer getSaturationGain() {
        return saturationGain;
    }

    public Integer getSaturationLGain() {
        return saturationLGain;
    }

    public Integer getValueGain() {
        return valueGain;
    }

    public Double[] getBlackLevel() {
        return blackLevel;
    }

    public Double[] getWhiteLevel() {
        return whiteLevel;
    }

    public Double[] getGamma() {
        return gamma;
    }

    public Double[] getThreshold() {
        return threshold;
    }
}
