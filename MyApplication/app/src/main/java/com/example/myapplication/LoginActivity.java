package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button btn_lregister, btn_llogin, btn_ladmin;
    EditText et_lpassword, et_lpno;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_lpassword = (EditText) findViewById(R.id.et_lpassword);
        et_lpno = (EditText) findViewById(R.id.et_lpno);
        btn_llogin = (Button) findViewById(R.id.btn_llogin);
        btn_lregister = (Button) findViewById(R.id.btn_lregister);
        btn_ladmin = (Button) findViewById(R.id.btn_ladmin);


        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_ladmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AdminLogin.class);
                startActivity(intent);
            }
        });


        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et_lpassword.getText().toString();
                String phone = et_lpno.getText().toString();

                Query checkUser = ref.orderByChild("phone").equalTo(phone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            et_lpassword.setError(null);

                            String passwordFromDB = dataSnapshot.child(phone).child("password").getValue(String.class);

                            if (passwordFromDB.equals(password)) {
                                String accessFromDB = dataSnapshot.child(phone).child("access").getValue(String.class);

                                if (accessFromDB.equals("YES")) {

                                    HashMap hashMap = new HashMap();
                                    hashMap.put("LED_STATUS", "ON");
                                    myRef.updateChildren(hashMap);

                                    Intent intent = new Intent(LoginActivity.this, WelcomeHomeActivity.class);
                                    startActivity(intent);


                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Access Denied",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                et_lpassword.setError("Wrong Password");
                                et_lpassword.requestFocus();
                            }

                        } else {
                            et_lpno.setError(" No Such phone Number exist");
                            et_lpno.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


            }
}


//    private Boolean validateUsername() {
//        String val = et_lusername.getText().toString();
//        if (val.isEmpty()) {
//            et_lusername.setError("Field cannot be empty");
//            return false;
//        } else {
//            et_lusername.setError(null);
//
//            return true;
//        }
//    }
//    private void isUser(){
//        String userEnteredUsername = et_lusername.getText().toString().trim();
//        String userEnteredPhone = et_lpno.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("name").equalTo(userEnteredUsername);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
////                    et_lusername.setError(null);
////                    et_lusername.setErrorEnabled(false);
//                    String phoneFromDB = snapshot.child(userEnteredUsername).child("phone").getValue(String.class);
//                    if(phoneFromDB.equals(userEnteredPhone)){
//                        et_lusername.setError(null);
//                        //et_lusername.setErrorEnabled(false);
////                       String namefromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
////                       String macfromDB = snapshot.child(userEnteredUsername).child("mac").getValue(String.class);
//
//                       Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                       startActivity(intent);
//                    }
//                    else{
//                        et_lpno.setError("Please enter the correct phone no.");
//                    }
//                }
//                else{
//                    et_lusername.setError("No such User exists!");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

