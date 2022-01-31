package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.graphics.Color;

public class ServerInfo {
    private final ActiveEffectInfo[] activeEffects;
    private final Integer[] activeColors;
    private final AdjustmentsInfo[] adjustments;
    private final Boolean cec;
    private final ComponentInfo[] components;
    private final EffectInfo[] effects;
    private final GrabbersInfo grabbers;
    private final String hostname;
    private final ImageToLedMappingTypes ledMappingType;
    private final InstanceInfo[] instances;
    private final String[] ledDevices;
    private final LEDInfo[] leds;
    private final PriorityInfo[] priorities;
    private final Boolean prioritiesAutoSelect;
    private final SessionInfo[] sessions;
    private final TransformInfo[] transforms;
    private final VideoModes videoMode;

    public ServerInfo(
            ActiveEffectInfo[] activeEffects,
            Integer[] activeColors,
            AdjustmentsInfo[] adjustments,
            Boolean cec,
            ComponentInfo[] components,
            EffectInfo[] effects,
            GrabbersInfo grabbers,
            String hostname,
            ImageToLedMappingTypes ledMappingType,
            InstanceInfo[] instances,
            String[] ledDevices,
            LEDInfo[] leds,
            PriorityInfo[] priorities,
            Boolean prioritiesAutoSelect,
            SessionInfo[] sessions,
            TransformInfo[] transforms,
            VideoModes videoMode) {
        this.activeEffects = activeEffects;
        this.activeColors = activeColors;
        this.adjustments = adjustments;
        this.cec = cec;
        this.components = components;
        this.effects = effects;
        this.grabbers = grabbers;
        this.hostname = hostname;
        this.ledMappingType = ledMappingType;
        this.instances = instances;
        this.ledDevices = ledDevices;
        this.leds = leds;
        this.priorities = priorities;
        this.prioritiesAutoSelect = prioritiesAutoSelect;
        this.sessions = sessions;
        this.transforms = transforms;
        this.videoMode = videoMode;
    }

    public String concatenatePrintableString() {
        return ComponentInfo.concatenatePrintableString(components) + System.lineSeparator() +
                AdjustmentsInfo.concatenatePrintableString(adjustments) + System.lineSeparator() +
                EffectInfo.concatenatePrintableString(effects) + System.lineSeparator() +
                PriorityInfo.concatenatePrintableString(priorities) + System.lineSeparator() +
                InstanceInfo.concatenatePrintableString(instances) + System.lineSeparator() +
                LEDInfo.concatenatePrintableString(leds) + System.lineSeparator() +

                concatenatePrintableActiveColorString(activeColors) + System.lineSeparator() +
                ActiveEffectInfo.concatenatePrintableString(activeEffects) + System.lineSeparator() +
                GrabbersInfo.concatenatePrintableString(grabbers) + System.lineSeparator() +
                concatenatePrintableLEDDevicesString(ledDevices) + System.lineSeparator() +
                TransformInfo.concatenatePrintableString(transforms) + System.lineSeparator() +

                "===Misc===" + System.lineSeparator() +
                "ledMappingType: " + ledMappingType.toString() + System.lineSeparator() +
                "videoMode: " + videoMode.toString() + System.lineSeparator() +
                "hostname: " + hostname + System.lineSeparator() +
                "prioritiesAutoSelect: " + prioritiesAutoSelect + System.lineSeparator() +
                "cec: " + cec;
    }

    public static String concatenatePrintableActiveColorString(Integer[] _colors) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : _colors) {
            sb.append("===ActiveLedColor===").append(System.lineSeparator());
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

    public ActiveEffectInfo[] getActiveEffects() {
        return activeEffects;
    }

    public Integer[] getActiveColors() {
        return activeColors;
    }

    public AdjustmentsInfo[] getAdjustments() {
        return adjustments;
    }

    public Boolean getCec() {
        return cec;
    }

    public ComponentInfo[] getComponents() {
        return components;
    }

    public EffectInfo[] getEffects() {
        return effects;
    }

    public GrabbersInfo getGrabbers() {
        return grabbers;
    }

    public String getHostname() {
        return hostname;
    }

    public ImageToLedMappingTypes getLedMappingType() {
        return ledMappingType;
    }

    public InstanceInfo[] getInstances() {
        return instances;
    }

    public String[] getLedDevices() {
        return ledDevices;
    }

    public LEDInfo[] getLeds() {
        return leds;
    }

    public PriorityInfo[] getPriorities() {
        return priorities;
    }

    public Boolean getPrioritiesAutoSelect() {
        return prioritiesAutoSelect;
    }

    public SessionInfo[] getSessions() {
        return sessions;
    }

    public TransformInfo[] getTransforms() {
        return transforms;
    }

    public VideoModes getVideoMode() {
        return videoMode;
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
