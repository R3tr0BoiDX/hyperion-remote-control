package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

public class ServerInfos {
    private final ComponentsInfos components;
    private final AdjustmentsInfos[] adjustments;
    private final EffectInfos[] effects;
    private final ImageToLedMappingTypes ledMappingType;
    private final VideoModes videoMode;
    private final String hostname;
    private final PriorityInfo[] priorities;
    private final Boolean prioritiesAutoSelect;

    public ServerInfos(
            ComponentsInfos components,
            AdjustmentsInfos[] adjustments,
            EffectInfos[] effects,
            ImageToLedMappingTypes ledMappingType,
            VideoModes videoMode,
            String hostname,
            PriorityInfo[] priorities,
            Boolean prioritiesAutoSelect) {
        this.components = components;
        this.adjustments = adjustments;
        this.effects = effects;
        this.ledMappingType = ledMappingType;
        this.videoMode = videoMode;
        this.hostname = hostname;
        this.priorities = priorities;
        this.prioritiesAutoSelect = prioritiesAutoSelect;
    }

    //TODO: Refactor to toString() method
    public void print(){
        components.print();
        AdjustmentsInfos.printAll(adjustments);
        EffectInfos.printAll(effects);
        PriorityInfo.printAll(priorities);

        Log.d("ServerInfos", "ledMappingType: " + ledMappingType.toString());
        Log.d("ServerInfos", "videoMode: " + videoMode.toString());
        Log.d("ServerInfos", "hostname: " + hostname);
        Log.d("ServerInfos", "prioritiesAutoSelect: " + prioritiesAutoSelect);
    }

    static ImageToLedMappingTypes castStringToLedMappingTyp(String _type){
        if (_type != null){
            switch (_type){
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

    static VideoModes castStringToVideoMode(String _mode){
        if (_mode != null){
            switch (_mode){
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

    public ComponentsInfos getComponents() {
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

    enum ImageToLedMappingTypes{
        unicolor_mean,
        multicolor_mean
    }

    enum VideoModes{
        two_D,
        three_D_SBS,
        three_D_TAB
    }
}
