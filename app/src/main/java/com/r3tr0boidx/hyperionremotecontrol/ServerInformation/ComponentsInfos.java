package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

public class ComponentsInfos {
    private final boolean all;              //Hyperion Instance itself
    private final boolean smoothing;        //Smoothing
    private final boolean blackBorder;      //Detect black borders
    private final boolean forwarder;        //Forwarding
    private final boolean boblightServer;   //Boblight server
    private final boolean grabber;          //USB grabber
    private final boolean v4l;              //Video4Linux?
    private final boolean ledDevice;        //LED hardware

    public ComponentsInfos(boolean all,
                           boolean smoothing,
                           boolean blackBorder,
                           boolean forwarder,
                           boolean boblightServer,
                           boolean grabber,
                           boolean v4l,
                           boolean ledDevice) {
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
        Log.d("ComponentsInfos", "all: " + all);
        Log.d("ComponentsInfos", "smoothing: " + smoothing);
        Log.d("ComponentsInfos", "blackborder: " + blackBorder);
        Log.d("ComponentsInfos", "forwarder: " + forwarder);
        Log.d("ComponentsInfos", "boblightServer: " + boblightServer);
        Log.d("ComponentsInfos", "grabber: " + grabber);
        Log.d("ComponentsInfos", "v4l: " + v4l);
        Log.d("ComponentsInfos", "ledDevice: " + ledDevice);
    }

    public boolean getAll() {
        return all;
    }

    public boolean getSmoothing() {
        return smoothing;
    }

    public boolean getBlackBorder() {
        return blackBorder;
    }

    public boolean getForwarder() {
        return forwarder;
    }

    public boolean getBoblightServer() {
        return boblightServer;
    }

    public boolean getGrabber() {
        return grabber;
    }

    public boolean getV4l() {
        return v4l;
    }

    public boolean getLedDevice() {
        return ledDevice;
    }
}
