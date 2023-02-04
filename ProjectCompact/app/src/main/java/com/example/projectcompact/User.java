package com.example.projectcompact;

public class User {
    String email, name, phone;

    // for our use
    public User(String email,String name,String phone)
    {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    //for firebase use
    public User()
    {
        //firebase uses this constructor to instantiate object and use getter setter to set values in database
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

