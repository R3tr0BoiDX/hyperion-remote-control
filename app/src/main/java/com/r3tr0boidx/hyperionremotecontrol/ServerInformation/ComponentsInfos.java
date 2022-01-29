package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

//TODO: Rebuild in a way, that entries are a enum. Then adapt to data structure to other Info classes


public class ComponentsInfos {

    //New structure
    /*
    private final enum type;
    private final Boolean enabeld;
     */

    private final Boolean all;              //Hyperion Instance itself
    private final Boolean smoothing;        //Smoothing
    private final Boolean blackBorder;      //Detect black borders
    private final Boolean forwarder;        //Json/Proto forwarder
    private final Boolean boblightServer;   //Boblight server
    private final Boolean grabber;          //Platform capture
    private final Boolean v4l;              //Video4Linux USB capture device
    private final Boolean ledDevice;        //Led device start/stops output of the configured led device

    public ComponentsInfos(
            Boolean all,
            Boolean smoothing,
            Boolean blackBorder,
            Boolean forwarder,
            Boolean boblightServer,
            Boolean grabber,
            Boolean v4l,
            Boolean ledDevice) {
        this.all = all;
        this.smoothing = smoothing;
        this.blackBorder = blackBorder;
        this.forwarder = forwarder;
        this.boblightServer = boblightServer;
        this.grabber = grabber;
        this.v4l = v4l;
        this.ledDevice = ledDevice;
    }

    public void print() {
        Log.d("ComponentsInfos", "===Required===");
        Log.d("ComponentsInfos", "all: " + all);
        Log.d("ComponentsInfos", "smoothing: " + smoothing);
        Log.d("ComponentsInfos", "blackborder: " + blackBorder);
        Log.d("ComponentsInfos", "forwarder: " + forwarder);
        Log.d("ComponentsInfos", "boblightServer: " + boblightServer);
        Log.d("ComponentsInfos", "grabber: " + grabber);
        Log.d("ComponentsInfos", "v4l: " + v4l);
        Log.d("ComponentsInfos", "ledDevice: " + ledDevice);
    }

    public Boolean getAll() {
        return all;
    }

    public Boolean getSmoothing() {
        return smoothing;
    }

    public Boolean getBlackBorder() {
        return blackBorder;
    }

    public Boolean getForwarder() {
        return forwarder;
    }

    public Boolean getBoblightServer() {
        return boblightServer;
    }

    public Boolean getGrabber() {
        return grabber;
    }

    public Boolean getV4l() {
        return v4l;
    }

    public Boolean getLedDevice() {
        return ledDevice;
    }
}
