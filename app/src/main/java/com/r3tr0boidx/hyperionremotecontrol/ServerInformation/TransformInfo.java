package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    TransformInfo(
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

    static TransformInfo[] readTransfroms(JSONArray _array) throws JSONException {
        if (_array != null){
            TransformInfo[] transform = new TransformInfo[_array.length()];
            for (int i = 0; i < transform.length; i++) {
                transform[i] = readTransfrom(_array.getJSONObject(i));
            }
            return transform;
        }
        return new TransformInfo[0];
    }

    private static TransformInfo readTransfrom(JSONObject _object) {
        return new TransformInfo(
                JSONHelper.getString(_object, "id"),
                JSONHelper.getInteger(_object, "luminanceGain"),
                JSONHelper.getInteger(_object, "luminanceMinimum"),
                JSONHelper.getInteger(_object, "saturationGain"),
                JSONHelper.getInteger(_object, "saturationLGain"),
                JSONHelper.getInteger(_object, "valueGain"),
                readDoubleArray(JSONHelper.getArray(_object, "blacklevel")),
                readDoubleArray(JSONHelper.getArray(_object, "whitelevel")),
                readDoubleArray(JSONHelper.getArray(_object, "gamma")),
                readDoubleArray(JSONHelper.getArray(_object, "threshold"))
        );
    }

    private static Double[] readDoubleArray(JSONArray _array){
        if (_array != null){
            Double[] result = new Double[_array.length()];
            try {
                for (int i = 0; i < result.length; i++) {
                    result[i] = _array.getDouble(i);
                }
            } catch (JSONException e) {
                Log.w("readDoubleArray", "Can't read " + _array);
                //e.printStackTrace();
            }
            return result;
        }
        return new Double[0];
    }

    public static String concatenatePrintableString(TransformInfo[] _transform) {
        StringBuilder sb = new StringBuilder();
        for (TransformInfo in : _transform) {
            sb.append(in.printableString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString() {
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

                "blackLevel: " + stringBuilderBlackLevel + System.lineSeparator() +
                "whiteLevel: " + stringBuilderWhiteLevel + System.lineSeparator() +
                "gamma: " + stringBuilderGamma + System.lineSeparator() +
                "threshold: " + stringBuilderThreshold + System.lineSeparator();
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
