package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;

public class ServerInfos {
    //TODO: Should represent order of JSON response
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
    private final ActiveEffectInfo[] activeEffects;
    private final Boolean cec;
    private final GrabbersInfo grabbers;
    private final String[] ledDevices;
    private final TransformInfo[] transforms;

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
            Integer[] activeColors,
            ActiveEffectInfo[] activeEffects,
            Boolean cec,
            GrabbersInfo grabbers,
            String[] ledDevices,
            TransformInfo[] transforms) {
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
        this.activeEffects = activeEffects;
        this.cec = cec;
        this.grabbers = grabbers;
        this.ledDevices = ledDevices;
        this.transforms = transforms;
    }

    public String concatenatePrintableString() {
        return ComponentInfos.concatenatePrintableString(components) + System.lineSeparator() +
                AdjustmentsInfos.concatenatePrintableString(adjustments) + System.lineSeparator() +
                EffectInfos.concatenatePrintableString(effects) + System.lineSeparator() +
                PriorityInfo.concatenatePrintableString(priorities) + System.lineSeparator() +
                InstanceInfos.concatenatePrintableString(instances) + System.lineSeparator() +
                LEDInfo.concatenatePrintableString(leds) + System.lineSeparator() +

                concatenatePrintableActiveColorString(activeColors) + System.lineSeparator() +
                ActiveEffectInfo.concatenatePrintableString(activeEffects) + System.lineSeparator() +
                GrabbersInfo.concatenatePrintableString(grabbers) + System.lineSeparator() +
                TransformInfo.concatenatePrintableString(transforms) + System.lineSeparator() +

                "===Misc===" + System.lineSeparator() +
                "ledMappingType: " + ledMappingType.toString() + System.lineSeparator() +
                "videoMode: " + videoMode.toString() +System.lineSeparator() +
                "hostname: " + hostname +System.lineSeparator() +
                "prioritiesAutoSelect: " + prioritiesAutoSelect +System.lineSeparator() +
                "cec: " + cec;
    }

    public static String concatenatePrintableActiveColorString(Integer[] _colors) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : _colors) {
            sb.append("===ActiveLedColor===") .append(System.lineSeparator());
            sb.append("activeColor: ").append(Color.valueOf(i)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String concatenatePrintableLEDDevicesString(String[] _ledDevices) {
        StringBuilder sb = new StringBuilder();
        sb.append("===LedDevices===").append(System.lineSeparator());

        //Append available grabbers
        for (String s : _ledDevices) {
            sb.append("available: ").append(s).append(System.lineSeparator());
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

    public Integer[] getActiveColors() {
        return activeColors;
    }

    public ActiveEffectInfo[] getActiveEffects() {
        return activeEffects;
    }

    public Boolean getCec() {
        return cec;
    }

    public GrabbersInfo getGrabbers() {
        return grabbers;
    }

    public String[] getLedDevices() {
        return ledDevices;
    }

    public TransformInfo[] getTransforms() {
        return transforms;
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
