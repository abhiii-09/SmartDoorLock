package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.data.Userhelperlogs;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    Button  btn_llogin, back;
    EditText et_lpassword, et_lpno;
    double long_address;
    double lat_address;
    double destination_latitute = 18.632590699999998;
    double destination_longitude = 73.79767749999999;
    FusedLocationProviderClient fusedLocationProviderClient;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase rootNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        et_lpassword = (EditText) findViewById(R.id.et_lpassword);
        et_lpno = (EditText) findViewById(R.id.et_lpno);
        btn_llogin = (Button) findViewById(R.id.btn_llogin);
        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });


        if (ActivityCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lat_address <= (destination_latitute + 1) && lat_address >=
                        (destination_latitute - 1) && long_address <= (destination_longitude + 1) &&
                        long_address >= (destination_longitude - 1)) {

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

                                        rootNode = FirebaseDatabase.getInstance();
                                        reference = rootNode.getReference("log");

                                        Calendar cal = Calendar.getInstance();
                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                        String date_str = df.format(cal.getTime());

                                        Userhelperlogs helperClass = new Userhelperlogs(date_str, phone);
                                        reference.child(phone).push().setValue(helperClass);

                                        Intent intent = new Intent(LoginActivity.this, WelcomeHomeActivity.class);
                                        startActivity(intent);


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Access Denied", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
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



                } else{
                    Toast.makeText(getApplicationContext(), "OUT OF RANGE!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(LoginActivity.this,
                                Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        long_address = addresses.get(0).getLongitude();
                        lat_address = addresses.get(0).getLatitude();
                        Log.d("ccc", "onComplete: Longitude = " + long_address);
                        Log.d("ccc1", "onComplete: Latitude = " + lat_address);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

