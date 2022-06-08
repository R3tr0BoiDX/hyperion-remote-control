//70202894-ef46-4fc1-a9e9-42407137e417

package com.r3tr0boidx.hyperionremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.r3tr0boidx.hyperionremotecontrol.Control.AdjustmentCommand;
import com.r3tr0boidx.hyperionremotecontrol.Control.ColorCommand;
import com.r3tr0boidx.hyperionremotecontrol.Networking.HTTPConnection;
import com.r3tr0boidx.hyperionremotecontrol.Networking.NetworkManager;
import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;
import com.r3tr0boidx.hyperionremotecontrol.Networking.TCPSocketConnection;
import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.*;
import com.r3tr0boidx.hyperionremotecontrol.SystemInformation.SystemInformation;
import com.r3tr0boidx.hyperionremotecontrol.SystemInformation.SystemInformationReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String test_ip = "192.168.2.7";

    //ATTENTION: Very untidy!!! Contains a lot of lazy test code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //createConnection();

        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            TCPSocketConnection socketConnection = new TCPSocketConnection();
            socketConnection.connect(ip);

            ColorCommand command = new ColorCommand(50, Color.GREEN);
            socketConnection.write(command.buildCommand());
            Helper.Log(command.buildCommand().toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    void createConnection(){
        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            HTTPConnection httpConnection = new HTTPConnection(true);
            if (!NetworkManager.getInstance().establishConnection(ip, httpConnection)){
                Log.w("createConnection", "Not connected!");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //Somehow network, somehow JSON and somehow helper. Don't know where to TODO: put this => to ServerInfo
    static Response getServerInfo() {
        try {
            JSONObject json = new JSONObject();
            json.put("command", "serverinfo");
            return NetworkManager.getInstance().writeQuery(json);
        } catch (JSONException e) {
            Log.e("getServerInfo", "Can't create json query!");
            e.printStackTrace();
        }
        return null;
    }

    //Somehow network, somehow JSON and somehow helper. Don't know where to put this
    static Response getSystemInfo() {
        try {
            JSONObject json = new JSONObject();
            json.put("command", "sysinfo");
            return NetworkManager.getInstance().writeQuery(json);
        } catch (JSONException e) {
            Log.e("getSystemInfo", "Can't create json query!");
            //e.printStackTrace();
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

        ServerInfo infos = InformationReader.readResponse(getServerInfo());
        if (infos != null){
            text.setText(infos.concatenatePrintableString());
        }
    }

    void setSystemInfo(){
        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        SystemInformation info = SystemInformationReader.readResponse(getSystemInfo());
        if (info != null){
            text.setText(info.concatenatePrintableString());
        }
    }
}