package com.example.myapplication.data;

public class Userhelperlogs {
    String phone, date;



    public Userhelperlogs( String date, String phone) {

        this.phone = phone;
        this.date = date;



    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {this.date = date; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

