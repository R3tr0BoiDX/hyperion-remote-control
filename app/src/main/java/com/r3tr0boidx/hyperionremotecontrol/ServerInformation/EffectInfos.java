package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import android.util.Log;

import org.json.JSONObject;

public class EffectInfos {

    private final JSONObject args;  //Arguments for effects
    private final String file;      //Where the JSON file of the effect is saved
    private final String name;      //Name of the effect
    private final String script;    //Where the Python file with the effect itself is saved
    private final String imageData; //Unsure

    private final boolean systemEffect;
    /* if "file" begins with : it's a system provided effect,
     * whereas if the path begins with /, it's a user created effect
    */

    public EffectInfos(JSONObject args, String file, String name, String script, String imageData) {
        this.args = args;
        this.file = file;
        this.name = name;
        this.script = script;
        this.imageData = imageData;

        systemEffect = isSystemEffect(file);
    }

    public void print(){
        Log.d("EffectInfos", "===Mandatory===");
        Log.d("EffectInfos", "args: " + args.toString());
        Log.d("EffectInfos", "file: " + file);
        Log.d("EffectInfos", "name: " + name);
        Log.d("EffectInfos", "script: " + script);

        Log.d("EffectInfos", "===Derived===");
        Log.d("EffectInfos",  "systemEffect: " + systemEffect);
    }

    public static void printAll(EffectInfos[] _effects){
        for (EffectInfos ef : _effects){
            ef.print();
        }
    }

    private boolean isSystemEffect(String _file){
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

    public String getImageData() {
        return imageData;
    }

    /* === Because of args ===
     * TODO: To parse unknown JSON structure: https://stackoverflow.com/a/19630271/7184809
     * TODO: To cast object from unknown type to proper one: https://stackoverflow.com/a/16094284/7184809
     * TODO: To actually use proper data type: https://stackoverflow.com/a/10531550/7184809
     *
     * TODO: Or this approach: https://stackoverflow.com/questions/15920212/how-to-check-the-type-of-a-value-from-a-jsonobject
     */
}
