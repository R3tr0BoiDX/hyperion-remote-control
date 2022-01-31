package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import com.r3tr0boidx.hyperionremotecontrol.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class EffectInfo {
    protected final JSONObject args;  //Arguments for effects
    protected final String file;      //Where the JSON file of the effect is saved
    protected final String name;      //Name of the effect
    protected final String script;    //Where the Python file with the effect itself is saved

    /* if "file" begins with : it's a system provided effect,
     * whereas if the path begins with /, it's a user created effect
     */
    protected final Boolean systemEffect;

    public EffectInfo(JSONObject args, String file, String name, String script) {
        this.args = args;
        this.file = file;
        this.name = name;
        this.script = script;

        if (file != null) {
            systemEffect = isSystemEffect(file);
        } else {
            systemEffect = null;
        }

        //readEffectArgs(args);
    }

    public static String concatenatePrintableString(EffectInfo[] _effects) {
        StringBuilder sb = new StringBuilder();
        for (EffectInfo ef : _effects) {
            sb.append(ef.printableString(false)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString(boolean _active) {
        String printable;

        if (_active) {
            printable = "===ActiveEffectInfo===" + System.lineSeparator();
        } else {
            printable = "===EffectInfo===" + System.lineSeparator();
        }

        printable += "args: " + args.toString() + System.lineSeparator() +
                "file: " + file + System.lineSeparator() +
                "name: " + name + System.lineSeparator() +
                "script: " + script + System.lineSeparator() +
                "===Derived===" + System.lineSeparator() +
                "systemEffect: " + systemEffect + System.lineSeparator();

        return printable;
    }

    protected Boolean isSystemEffect(String _file) {
        return (_file.charAt(0) == ':');
    }

    public JSONObject getArgs() {
        return args;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getScript() {
        return script;
    }

    public Boolean getSystemEffect() {
        return systemEffect;
    }

    //Works - do something with it
    /*
    void readEffectArgs(JSONObject _args){
        Iterator<String> keys = _args.keys();
        try {
            while(keys.hasNext()) {
                String key = keys.next();
                Object item = _args.get(key);

                if (item instanceof Boolean) {
                }

                if (item instanceof Integer) {
                }

                if (item instanceof Double) {
                }

                if (item instanceof String) {
                }

                if (item instanceof JSONArray) {
                }

                if (item instanceof JSONObject) {
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
     */
}