package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class access extends AppCompatActivity {
    private RadioButton accept;
    private RadioButton deny;
    RadioGroup radioGroup;
    Button saveButton;
    Button backButton;
    Button password_change;
    String req;
    String str;
    String name;
    String phone;
    String info;
    String ph_no;

    private TextView txtInfo;
    Context context;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access1);

        context=this;
        txtInfo = (TextView)findViewById(R.id.txt_info);

        radioGroup = (RadioGroup) findViewById(R.id.groupradio);
        saveButton = (Button) findViewById(R.id.btn_save);
//        refreshButton = (Button) findViewById(R.id.btn_refresh);
        backButton = (Button) findViewById(R.id.btn_back);
        accept = (RadioButton) findViewById(R.id.radio_accept);
        deny = (RadioButton) findViewById(R.id.radio_deny);
        password_change = (Button) findViewById(R.id.btn_changepw);


        Intent intent = getIntent();
        str = intent.getStringExtra("message");
        ph_no = str;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(access.this, adminPanel.class);
                startActivity(intent);
            }
        });

        password_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(access.this, PasswordReset.class);
                intent.putExtra("message", ph_no);
                startActivity(intent);

            }
        });


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                information info  = dataSnapshot.getValue(information.class);
                String txt = info.getName() + info.getPhone();
                txtInfo.setText(txt);
                setContentView(txtInfo);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };

        ref.child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                phone = snapshot.child("phone").getValue(String.class);
                info = (name + "\n" + phone);
                txtInfo.setText(info);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                req = snapshot.child("access").getValue(String.class);
                if(req.equals("YES")){
                    accept.setChecked(true);
                   // Toast.makeText(getApplicationContext(),req,Toast.LENGTH_SHORT).show();

                }
                else{
                    deny.setChecked(true);
                   // Toast.makeText(getApplicationContext(),req,Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                //radioButton = findViewById(radioId);
                if (radioId == (findViewById(R.id.radio_accept).getId())) {
                    HashMap hashMap1 = new HashMap();
                    hashMap1.put("access", "YES");
                    ref.child(str).updateChildren(hashMap1);
                } else {
                    HashMap hashMap1 = new HashMap();
                    hashMap1.put("access", "NO");
                    ref.child(str).updateChildren(hashMap1);
                }
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();

            }
        });

    }
}




//package com.example.myapplication;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.HashMap;
//
//public class access extends AppCompatActivity {
//
//    //private Button accept;
////    private Button deny;
//    // private Switch sw_access;
//
//    RadioButton radioButton;
//    RadioGroup radioGroup;
//    String str;
//    Button saveButton;
//
//    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_access);
//
////        Intent intent = getIntent();
////        str = intent.getStringExtra("message");
//
////        sw_access = (Switch) findViewById(R.id.sw_access_access);
//
////        TextView txtInfo = (TextView) findViewById(R.id.txt_info);
////        txtInfo.setText("str");
////        setContentView(txtInfo);
//
//        radioGroup = (RadioGroup) findViewById(R.id.groupradio);
//        saveButton = (Button) findViewById(R.id.btn_save);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int radioId = radioGroup.getCheckedRadioButtonId();
//                //radioButton = findViewById(radioId);
//                if (radioId == (findViewById(R.id.radio_accept).getId())) {
//                    HashMap hashMap1 = new HashMap();
//                    hashMap1.put("access", "YES");
//                    ref.child(str).updateChildren(hashMap1);
//                } else {
//                    HashMap hashMap1 = new HashMap();
//                    hashMap1.put("access", "NO");
//                    ref.child(str).updateChildren(hashMap1);
//                }
//            }
//        });
//
//    }
//}
//
//
////    public void checkButton(View v){
//////        ViewGroup parent = (ViewGroup)listLayout.getParent();
//////        if(parent != null){
//////            parent.removeAllViews();
//////        }
////        int radioId = radioGroup.getCheckedRadioButtonId();
////        radioButton = findViewById(radioId);
////
////    }
////}
//
//
////        sw_access.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                if (isChecked) {
////                    HashMap hashMap1 = new HashMap();
////                    hashMap1.put("access", "YES");
////                    ref.child(str).updateChildren(hashMap1);
////                } else {
////                    HashMap hashMap1 = new HashMap();
////                    hashMap1.put("access", "NO");
////                    ref.child(str).updateChildren(hashMap1);
////                }
////            }
////        });
//
//
////        accept = findViewById(R.id.btn_accept);
////        deny = findViewById(R.id.btn_deny);
////
////        accept.setOnClickListener(new View.OnClickListener() {
////            @Override
//////            public void onClick(View v) {
////                HashMap hashMap1 = new HashMap();
////                hashMap1.put("access", "YES");
////                ref.child(str).updateChildren(hashMap1);
////            }
////        });
////
////        deny.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                HashMap hashMap1 = new HashMap();
////                hashMap1.put("access", "NO");
////                ref.child(str).updateChildren(hashMap1);
////            }
////        });
////    }
////}


