package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WelcomeHomeActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_home);

        Button btn_closedoor = (Button) findViewById(R.id.closedoor);

//        HashMap hashMap = new HashMap();
//        hashMap.put("LED_STATUS", "ON");
//        myRef.updateChildren(hashMap);
//
//        try { Thread.sleep(10000);   //Yaha pe he delay
//            HashMap hashMap1 = new HashMap();
//            hashMap1.put("LED_STATUS", "OFF");
//            myRef.updateChildren(hashMap1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10000ms
                HashMap hashMap = new HashMap();
                hashMap.put("LED_STATUS", "OFF");
                myRef.updateChildren(hashMap);

                Intent intent = new Intent(WelcomeHomeActivity.this, MainActivity3.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Door Closed", Toast.LENGTH_SHORT).show();
            }
        }, 10000);

        btn_closedoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap hashMap = new HashMap();
                hashMap.put("LED_STATUS", "OFF");
                myRef.updateChildren(hashMap);

                Intent intent = new Intent(WelcomeHomeActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}