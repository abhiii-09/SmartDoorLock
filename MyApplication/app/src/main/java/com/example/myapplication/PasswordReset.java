package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PasswordReset extends AppCompatActivity {

    Button save_btn;
    EditText Password;
    String ph_no;
    String new_password;
    String np;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        Intent intent = getIntent();
        ph_no = intent.getStringExtra("message");

        Password = (EditText) findViewById(R.id.Password);
        save_btn = (Button) findViewById(R.id.save_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_password = Password.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("password",new_password);
                ref.child(ph_no).updateChildren(hashMap);

                Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PasswordReset.this, adminPanel.class);
                startActivity(intent);

           }
        });
    }
}