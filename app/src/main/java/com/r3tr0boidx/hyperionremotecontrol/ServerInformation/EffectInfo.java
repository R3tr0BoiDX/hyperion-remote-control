package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import com.r3tr0boidx.hyperionremotecontrol.Helper;
import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    EffectInfo(
            JSONObject args,
            String file,
            String name,
            String script) {
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

    static EffectInfo[] readEffects(JSONArray _array) throws JSONException {
        if (_array != null){
            EffectInfo[] effects = new EffectInfo[_array.length()];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = readEffect(_array.getJSONObject(i), false);
            }
            return effects;
        }
        return new EffectInfo[0];
    }

    //special case, must be default, because ActiveEffectInfo extends this class and needs access to this method
    static EffectInfo readEffect(JSONObject _object, boolean _active) {
        if (_active){
            return new ActiveEffectInfo(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script"),
                    JSONHelper.getInteger(_object, "priority"),
                    JSONHelper.getInteger(_object, "timeout")
            );
        } else {
            return new EffectInfo(
                    JSONHelper.getObject(_object, "args"),
                    JSONHelper.getString(_object, "file"),
                    JSONHelper.getString(_object, "name"),
                    JSONHelper.getString(_object, "script")
            );
        }
    }

    public static String concatenatePrintableString(EffectInfo[] _effects) {
        StringBuilder sb = new StringBuilder();
        for (EffectInfo ef : _effects) {
            sb.append(ef.printableString(false)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printableString(boolean _active) {
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

    public Boolean isSystemEffect(String _file) {
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

    /*
    //Works - do something with it
    List<EffectArg<?>> readEffectArgs(JSONObject _args){
        Iterator<String> keys = _args.keys();
        List<EffectArg<?>> args = new ArrayList<>();

        try {
            while(keys.hasNext()) {
                String key = keys.next();
                Object item = _args.get(key);

                if (item instanceof Boolean) {
                    EffectArg<Boolean> effectArg = new EffectArg<>(key, (Boolean) item);
                    args.add(effectArg);
                }

                if (item instanceof Integer) {
                    EffectArg<Integer> effectArg = new EffectArg<>(key, (Integer) item);
                    args.add(effectArg);
                }

                if (item instanceof Double) {
                    EffectArg<Double> effectArg = new EffectArg<>(key, (Double) item);
                    args.add(effectArg);
                }

                if (item instanceof String) {
                    EffectArg<String> effectArg = new EffectArg<>(key, (String) item);
                    args.add(effectArg);
                }

                //TODO: read array and object recursiv
                if (item instanceof JSONArray) {
                    EffectArg<JSONArray> effectArg = new EffectArg<>(key, (JSONArray) item);
                    args.add(effectArg);
                }

                if (item instanceof JSONObject) {
                    EffectArg<JSONObject> effectArg = new EffectArg<>(key, (JSONObject) item);
                    args.add(effectArg);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return args;
    }
     */
}