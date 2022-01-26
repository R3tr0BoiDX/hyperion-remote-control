//70202894-ef46-4fc1-a9e9-42407137e417

package com.r3tr0boidx.hyperionremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

            getServerInfo(text);
        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void getServerInfo(TextView _view) throws JSONException, IOException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("command", "serverinfo");
        Response r = NetworkManager.getInstance().postQuery(json);

        _view.setText(r.getResponseBody());
    }
}