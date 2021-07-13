package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {
    //DatabaseHelper databaseHelper;

    EditText et_username, et_password, et_pno;
    Button btn_register, btn_login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //databaseHelper = new DatabaseHelper(this);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_pno = findViewById(R.id.et_pno);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);

        getSupportActionBar().setDisplayShowCustomEnabled(true);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = et_username.getText().toString();
                String password = et_password.getText().toString();
                String phone = et_pno.getText().toString();


                UserHelperClass helperClass = new UserHelperClass(name, password, phone);
                reference.child(phone).setValue(helperClass);
                HashMap hashMap = new HashMap();
                hashMap.put("access", "NO");
                reference.child(phone).updateChildren(hashMap);

                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();

            }
        });

    }}