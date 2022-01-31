//70202894-ef46-4fc1-a9e9-42407137e417

package com.r3tr0boidx.hyperionremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    String test_ip = "192.168.2.7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTextView();
    }

    //Somehow network, somehow JSON and somehow helper. Don't know where to put this
    static Response getServerInfos() throws JSONException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("command", "serverinfo");
        return NetworkManager.getInstance().postQuery(json);
    }

    public void refresh (View _view){
        setTextView();
    }

    void setTextView(){
        TextView text = findViewById(R.id.textView);
        text.setMovementMethod(new ScrollingMovementMethod());

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            NetworkManager.getInstance().establishConnection(ip, true);

            ServerInfos infos = InformationReader.readResponse(getServerInfos());
            if (infos != null){
                text.setText(infos.concatenatePrintableString());
            }

        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }
}