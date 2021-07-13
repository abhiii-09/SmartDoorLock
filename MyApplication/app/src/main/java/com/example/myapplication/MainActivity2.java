package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private EditText Password;
    private android.widget.Button Button;
    private TextView DispText;
    //private TextView textView;
//    String Lockurl;
//    String Unlockurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        //textView.setText("Your Device IP Address: " + ipAddress);
//        Unlockurl = "http://192.168.1.9/unlock";
//        Lockurl = "http://192.168.1.9/lock";

        //Log.d("ip", "onCreate: ipAddress = "+ ipAddress);
//        Log.d("URL1", "onCreate: url = " + Unlockurl);
//        Log.d("URL2", "onCreate: url = " + Lockurl);

        Password = (EditText) findViewById(R.id.password);
        Button = (Button) findViewById(R.id.button);
        DispText = (TextView) findViewById(R.id.dispText);


        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Password.getText().toString());

                //clicked_btn2(Googleurl);

            }
        });
    }

    private void validate(String userPassword) {
        if (userPassword.equals("1234")) {
            Intent intent = new Intent(MainActivity2.this, SecondActivity.class);
            startActivity(intent);
//            //clicked_btn(Unlockurl);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                Log.d("er", "validate: ERROR!!");
//            }
//            //clicked_btn2(Lockurl);
        } else if (userPassword.equals("")) {
            DispText.setText("Please enter the 4 digit password!");
        } else {
            DispText.setText("Incorrect Password! Please Try Again");
        }
    }

//    public void clicked_btn(String url) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        startActivity(intent);
//    }
}

//        public void clicked_btn2(String url2) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url2));
//        startActivity(intent);
//        }
//    }