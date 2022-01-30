package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

//TODO: Rebuild in a way, that entries are a enum. Then adapt to data structure to other Info classes


public class ComponentInfos {

    private final Component type;
    private final Boolean state;

    public ComponentInfos(
            Component type,
            Boolean state) {
        this.type = type;
        this.state = state;
    }

    public static void printAll(ComponentInfos[] _components) {
        for (ComponentInfos co : _components) {
            co.print();
        }
    }

    public void print() {
        Log.d("ComponentsInfos", "===Required===");
        Log.d("ComponentsInfos", "type: " + type.toString());
        Log.d("ComponentsInfos", "state: " + state);
    }

    public Component getType() {
        return type;
    }

    public Boolean getState() {
        return state;
    }

    enum Component{
        all,                //Hyperion Instance itself
        smoothing,          //Smoothing
        blackborder,        //Detect black borders
        forwarder,          //JSON/Proto forwarder
        boblightServer,     //Boblight server
        grabber,            //Platform capture
        v4l,                //Video4Linux USB capture device
        ledDevice           //Led device start/stops output of the configured led device
    }
}
