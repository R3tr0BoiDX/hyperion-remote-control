//70202894-ef46-4fc1-a9e9-42407137e417

package com.r3tr0boidx.hyperionremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.r3tr0boidx.hyperionremotecontrol.ServerInformation.*;

import org.json.JSONException;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    String test_ip = "192.168.2.7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textView);

        try {
            Inet4Address ip = (Inet4Address) InetAddress.getByName(test_ip);
            NetworkManager.getInstance().establishConnection(ip, true);

            InformationReader information = new InformationReader();

        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }
}