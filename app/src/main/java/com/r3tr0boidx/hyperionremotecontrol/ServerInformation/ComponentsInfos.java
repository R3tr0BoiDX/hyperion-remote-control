package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

public class ComponentsInfos {
    private final Boolean all;              //Hyperion Instance itself
    private final Boolean smoothing;        //Smoothing
    private final Boolean blackBorder;      //Detect black borders
    private final Boolean forwarder;        //Json/Proto forwarder
    private final Boolean boblightServer;   //Boblight server
    private final Boolean grabber;          //Platform capture
    private final Boolean v4l;              //Video4Linux USB capture device
    private final Boolean ledDevice;        //Led device start/stops output of the configured led device

    private Boolean color;          //All colors that has been set belongs to this component
    private Boolean effect;         //All effects belongs to this component
    private Boolean image;          //All single/solid images belongs to this. NOT for streaming
    private Boolean flatBuffer;     //All image stream sources from flatbuffer server
    private Boolean protoBuffer;    //All image stream sources from Protobuffer server

    public ComponentsInfos(Boolean all,
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
        Log.d("ComponentsInfos", "===Mandatory===");
        Log.d("ComponentsInfos", "all: " + all);
        Log.d("ComponentsInfos", "smoothing: " + smoothing);
        Log.d("ComponentsInfos", "blackborder: " + blackBorder);
        Log.d("ComponentsInfos", "forwarder: " + forwarder);
        Log.d("ComponentsInfos", "boblightServer: " + boblightServer);
        Log.d("ComponentsInfos", "grabber: " + grabber);
        Log.d("ComponentsInfos", "v4l: " + v4l);
        Log.d("ComponentsInfos", "ledDevice: " + ledDevice);

        Log.d("ComponentsInfos", "===Optional===");
        Log.d("ComponentsInfos", "color: " + color);
        Log.d("ComponentsInfos", "effect: " + effect);
        Log.d("ComponentsInfos", "image: " + image);
        Log.d("ComponentsInfos", "flatbuffer: " + flatBuffer);
        Log.d("ComponentsInfos", "protobuffer: " + protoBuffer);
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public void setEffect(Boolean effect) {
        this.effect = effect;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public void setFlatBuffer(Boolean flatBuffer) {
        this.flatBuffer = flatBuffer;
    }

    public void setProtoBuffer(Boolean protoBuffer) {
        this.protoBuffer = protoBuffer;
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

    public Boolean getColor() {
        return color;
    }

    public Boolean getEffect() {
        return effect;
    }

    public Boolean getImage() {
        return image;
    }

    public Boolean getFlatBuffer() {
        return flatBuffer;
    }

    public Boolean getProtoBuffer() {
        return protoBuffer;
    }
}
