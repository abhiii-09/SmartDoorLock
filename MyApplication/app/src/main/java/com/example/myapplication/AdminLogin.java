package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button btn_aback, btn_alogin;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        btn_aback = (Button)findViewById(R.id.btn_aback);
        btn_alogin = (Button)findViewById(R.id.btn_alogin);
        Password = (EditText) findViewById(R.id.et_aPassword);

        btn_aback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        btn_alogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Password.getText().toString());
            }
        });
    }

        public void validate(String userPassword) {
            if (userPassword.equals("1234")) {
                Intent intent = new Intent(AdminLogin.this, adminPanel.class);
                startActivity(intent);
            }
            else if (userPassword.equals("")) {
                Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
            }
       }

    }
