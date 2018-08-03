package com.example.m.test;

public class User {
    String id;
    String name;
    String category;
    String emergency;
    String password;
    Boolean verification;



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getEmergency() {
        return emergency;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getVerification() {
        return verification;
    }
}
