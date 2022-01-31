package com.r3tr0boidx.hyperionremotecontrol.SystemInformation;

import com.r3tr0boidx.hyperionremotecontrol.JSONHelper;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.InstanceInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HyperionInfo {
    private final String build;
    private final String gitRemote;
    private final String id;
    private final Boolean readOnlyMode;
    private final String rootPath;
    private final String time;
    private final String version;

    public HyperionInfo(
            String build,
            String gitRemote,
            String id,
            Boolean readOnlyMode,
            String rootPath,
            String time,
            String version) {
        this.build = build;
        this.gitRemote = gitRemote;
        this.id = id;
        this.readOnlyMode = readOnlyMode;
        this.rootPath = rootPath;
        this.time = time;
        this.version = version;
    }

    static HyperionInfo readHyperion(JSONObject _object) {
        return new HyperionInfo(
                JSONHelper.getString(_object, "build"),
                JSONHelper.getString(_object, "gitremote"),
                JSONHelper.getString(_object, "id"),
                JSONHelper.getBoolean(_object, "readOnlyMode"),
                JSONHelper.getString(_object, "rootPath"),
                JSONHelper.getString(_object, "time"),
                JSONHelper.getString(_object, "version")
                );
    }

    public String printableString() {
        return "===HyperionInfo===" + System.lineSeparator() +
                "build: " + build + System.lineSeparator() +
                "gitRemote: " + gitRemote + System.lineSeparator() +
                "id: " + id + System.lineSeparator() +
                "readOnlyMode: " + readOnlyMode + System.lineSeparator() +
                "rootPath: " + rootPath + System.lineSeparator() +
                "time: " + time + System.lineSeparator() +
                "version: " + version + System.lineSeparator();
    }

    public String getBuild() {
        return build;
    }

    public String getGitRemote() {
        return gitRemote;
    }

    public String getId() {
        return id;
    }

    public Boolean getReadOnlyMode() {
        return readOnlyMode;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getTime() {
        return time;
    }

    public String getVersion() {
        return version;
    }
}
