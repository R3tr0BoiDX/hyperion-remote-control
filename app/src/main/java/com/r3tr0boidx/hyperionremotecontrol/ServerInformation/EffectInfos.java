package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

import org.json.JSONObject;

public class EffectInfos {

    protected final JSONObject args;  //Arguments for effects
    protected final String file;      //Where the JSON file of the effect is saved
    protected final String name;      //Name of the effect
    protected final String script;    //Where the Python file with the effect itself is saved

    /* if "file" begins with : it's a system provided effect,
     * whereas if the path begins with /, it's a user created effect
     */
    protected final Boolean systemEffect;

    public EffectInfos(JSONObject args, String file, String name, String script) {
        this.args = args;
        this.file = file;
        this.name = name;
        this.script = script;

        if (file != null) {
            systemEffect = isSystemEffect(file);
        } else {
            systemEffect = null;
        }
    }

    public static String concatenatePrintableString(EffectInfos[] _effects) {
        StringBuilder sb = new StringBuilder();
        for (EffectInfos ef : _effects) {
            sb.append(ef.printableString(false)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String printableString(boolean _active) {
        String printable;

        if (_active) {
            printable = "===ActiveEffectInfo===" + System.lineSeparator();
        } else {
            printable = "===EffectInfos===" + System.lineSeparator();
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

    /* === Because of args ===
     * TODO: To parse unknown JSON structure: https://stackoverflow.com/a/19630271/7184809
     * TODO: To cast object from unknown type to proper one: https://stackoverflow.com/a/16094284/7184809
     * TODO: To actually use proper data type: https://stackoverflow.com/a/10531550/7184809
     *
     * TODO: Or this approach: https://stackoverflow.com/questions/15920212/how-to-check-the-type-of-a-value-from-a-jsonobject
     */
}
