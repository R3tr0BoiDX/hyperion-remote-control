package com.r3tr0boidx.hyperionremotecontrol;

public class Types {
    public enum ImageToLedMappingTypes {
        unicolor_mean,
        multicolor_mean
    }

    public enum VideoModes {
        two_D,
        three_D_SBS,
        three_D_TAB
    }

    //Warning: Must be all upper case, else Component.valueOf("String") isn't working
    public enum Component{
        ALL,                //Hyperion Instance itself
        SMOOTHING,          //Smoothing
        BLACKBORDER,        //Detect black borders
        FORWARDER,          //JSON/Proto forwarder
        BOBLIGHTSERVER,     //Boblight server
        GRABBER,            //Platform capture
        V4L,                //Video4Linux USB capture device
        LEDDEVICE           //Led device start/stops output of the configured led device
    }
}
