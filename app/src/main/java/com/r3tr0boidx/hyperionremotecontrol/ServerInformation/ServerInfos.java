package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

public class ServerInfos {
    private final ComponentsInfos components;
    private final AdjustmentsInfos[] adjustments;
    private final EffectInfos[] effects;
    private final ImageToLedMappingTypes ledMappingType;
    private final VideoModes videoMode;

    public ServerInfos(
            ComponentsInfos components,
            AdjustmentsInfos[] adjustments,
            EffectInfos[] effects,
            ImageToLedMappingTypes ledMappingType,
            VideoModes videoMode) {
        this.components = components;
        this.adjustments = adjustments;
        this.effects = effects;
        this.ledMappingType = ledMappingType;
        this.videoMode = videoMode;
    }

    void print(){
        components.print();
        AdjustmentsInfos.printAll(adjustments);
        EffectInfos.printAll(effects);

        Log.d("ServerInfos", ledMappingType.toString());
        Log.d("ServerInfos", videoMode.toString());
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
