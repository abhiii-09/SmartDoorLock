package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class SecondActivity extends AppCompatActivity {

    private Button Back;
//    private TextView textView;
//    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        textView = findViewById(R.id.getIPAddress);
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
//        textView.setText("Your Device IP Address: " + ipAddress);
//        url = ipAddress+"/unlock";
//        Log.d("ip", "onCreate: ipAddress = "+ ipAddress);
//        Log.d("URL", "onCreate: url = "+ url);

        Back = (Button)findViewById(R.id.backbtn);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    private void goBack(){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
}