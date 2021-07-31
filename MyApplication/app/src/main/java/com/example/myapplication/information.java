package com.example.myapplication;

public class information {
    private String name;
    private String phone;
    private String access;
    private String password;

    public information(){
    }

    public information(String name, String phone){
        this.name = name;
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}