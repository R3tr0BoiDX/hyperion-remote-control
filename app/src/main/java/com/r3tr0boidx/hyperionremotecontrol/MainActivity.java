//70202894-ef46-4fc1-a9e9-42407137e417

package com.r3tr0boidx.hyperionremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.r3tr0boidx.hyperionremotecontrol.Control.ColorCommand;
import com.r3tr0boidx.hyperionremotecontrol.Control.ComponentsCommand;
import com.r3tr0boidx.hyperionremotecontrol.Control.InstancesCommand;
import com.r3tr0boidx.hyperionremotecontrol.Control.SourceSelectionCommand;
import com.r3tr0boidx.hyperionremotecontrol.Control.VideoModeCommand;
import com.r3tr0boidx.hyperionremotecontrol.Networking.NetworkManager;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.*;
import com.r3tr0boidx.hyperionremotecontrol.SystemInformation.SystemInformation;
import com.r3tr0boidx.hyperionremotecontrol.SystemInformation.SystemInformationReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String test_ip = "192.168.2.7";

    //ATTENTION: Very untidy!!! Contains a lot of lazy test code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            NetworkManager.getInstance().establishConnection(ip, true);

            Response r = getServerInfo();
            ServerInfo info = InformationReader.readResponse(r);
            assert info != null;

            Helper.Log(info.getInstances()[0].printableString());

            InstancesCommand command = new InstancesCommand(InstancesCommand.InstancesCommandType.stopInstance, info.getInstances()[0]);

            Helper.Log(command.buildCommand().toString());
            command.execute();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Somehow network, somehow JSON and somehow helper. Don't know where to put this => to extra class
    static Response getServerInfo() {
        try {
            JSONObject json = new JSONObject();
            json.put("command", "serverinfo");
            return NetworkManager.getInstance().postQuery(json);
        } catch (JSONException e) {
            Log.e("getServerInfo", "Can't create json query!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e("getServerInfo", "Network error! Can't recieve answer");
            e.printStackTrace();
        }
        return null;
    }

    //Somehow network, somehow JSON and somehow helper. Don't know where to put this
    static Response getSystemInfo() {
        try {
            JSONObject json = new JSONObject();
            json.put("command", "sysinfo");
            return NetworkManager.getInstance().postQuery(json);
        } catch (JSONException e) {
            Log.e("getSystemInfo", "Can't create json query!");
            //e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e("getSystemInfo", "Network error! Can't recieve answer");
            e.printStackTrace();
        }
        return null;
    }

    public void refreshServer (View _view){
        setServerInfo();
    }
    public void refreshSystem (View _view){
        setSystemInfo();
    }

    void setServerInfo(){
        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            NetworkManager.getInstance().establishConnection(ip, true);

            ServerInfo infos = InformationReader.readResponse(getServerInfo());
            if (infos != null){
                text.setText(infos.concatenatePrintableString());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setSystemInfo(){
        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            NetworkManager.getInstance().establishConnection(ip, true);

            SystemInformation info = SystemInformationReader.readResponse(Objects.requireNonNull(getSystemInfo()));
            if (info != null){
                text.setText(info.concatenatePrintableString());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}