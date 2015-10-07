package com.ajscanlan.databaseapp.person;

/**
 * Created by Alexander Scanlan on 07/10/2015
 */
public class Person {
    //State
    private String mEmail;
    private String mName;
    private String mPhoneNumber;
    private int mID;

    //Constructors
    public Person(){
        this(-1, null, null, null);
    }

    public Person(int ID, String name, String email, String phoneNumber){
        this.mEmail = email;
        this.mName = name;
        this.mPhoneNumber = phoneNumber;
        this.mID = ID;
    }

    //Getters and Setters
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }
}
