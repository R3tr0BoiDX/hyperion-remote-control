package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;
import android.util.Log;

public class ServerInfos {
    private final ComponentInfos[] components;
    private final AdjustmentsInfos[] adjustments;
    private final EffectInfos[] effects;
    private final ImageToLedMappingTypes ledMappingType;
    private final VideoModes videoMode;
    private final String hostname;
    private final PriorityInfo[] priorities;
    private final Boolean prioritiesAutoSelect;
    private final InstanceInfos[] instances;
    private final LEDInfo[] leds;
    private final SessionInfo[] sessions;
    private final Integer[] activeColors;

    //TODO: Unify name of classes (singular and just "Info")
    public ServerInfos(
            ComponentInfos[] components,
            AdjustmentsInfos[] adjustments,
            EffectInfos[] effects,
            ImageToLedMappingTypes ledMappingType,
            VideoModes videoMode,
            String hostname,
            PriorityInfo[] priorities,
            Boolean prioritiesAutoSelect,
            InstanceInfos[] instances,
            LEDInfo[] leds,
            SessionInfo[] sessions,
            Integer[] activeColors) {
        this.components = components;
        this.adjustments = adjustments;
        this.effects = effects;
        this.ledMappingType = ledMappingType;
        this.videoMode = videoMode;
        this.hostname = hostname;
        this.priorities = priorities;
        this.prioritiesAutoSelect = prioritiesAutoSelect;
        this.instances = instances;
        this.leds = leds;
        this.sessions = sessions;
        this.activeColors = activeColors;
    }

    //TODO: Refactor to toString() method
    public void print() {
        ComponentInfos.printAll(components);
        AdjustmentsInfos.printAll(adjustments);
        Log.d("EffectInfos", EffectInfos.concatenatePrintableString(effects));
        PriorityInfo.printAll(priorities);

        Log.d("InstanceInfos", InstanceInfos.concatenatePrintableString(instances));
        Log.d("LEDInfo", LEDInfo.concatenatePrintableString(leds));
        Log.d("ActiveLedColor", concatenatePrintableActiveColorString(activeColors));


        Log.d("ServerInfos", "ledMappingType: " + ledMappingType.toString());
        Log.d("ServerInfos", "videoMode: " + videoMode.toString());
        Log.d("ServerInfos", "hostname: " + hostname);
        Log.d("ServerInfos", "prioritiesAutoSelect: " + prioritiesAutoSelect);
    }

    public static String concatenatePrintableActiveColorString(Integer[] _colors) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : _colors) {
             sb.append("===ActiveLedColor===") .append(System.lineSeparator());
             sb.append("activeColor: ").append(Color.valueOf(i)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    static ImageToLedMappingTypes castStringToLedMappingTyp(String _type) {
        if (_type != null) {
            switch (_type) {
                case "unicolor_mean":
                    return ImageToLedMappingTypes.unicolor_mean;
                case "multicolor_mean":
                    return ImageToLedMappingTypes.multicolor_mean;
                default:
                    return null;
            }
        }
        return null;
    }

    static VideoModes castStringToVideoMode(String _mode) {
        if (_mode != null) {
            switch (_mode) {
                case "2D":
                    return VideoModes.two_D;
                case "3DSBS":
                    return VideoModes.three_D_SBS;
                case "3DTAB":
                    return VideoModes.three_D_TAB;
                default:
                    return null;
            }
        }
        return null;
    }

    public ComponentInfos[] getComponents() {
        return components;
    }

    public AdjustmentsInfos[] getAdjustments() {
        return adjustments;
    }

    public EffectInfos[] getEffects() {
        return effects;
    }

    public ImageToLedMappingTypes getLedMappingType() {
        return ledMappingType;
    }

    public VideoModes getVideoMode() {
        return videoMode;
    }

    public String getHostname() {
        return hostname;
    }

    public PriorityInfo[] getPriorities() {
        return priorities;
    }

    public Boolean getPrioritiesAutoSelect() {
        return prioritiesAutoSelect;
    }

    public InstanceInfos[] getInstances() {
        return instances;
    }

    public LEDInfo[] getLeds() {
        return leds;
    }

    public SessionInfo[] getSessions() {
        return sessions;
    }

    enum ImageToLedMappingTypes {
        unicolor_mean,
        multicolor_mean
    }

    enum VideoModes {
        two_D,
        three_D_SBS,
        three_D_TAB
    }
}
