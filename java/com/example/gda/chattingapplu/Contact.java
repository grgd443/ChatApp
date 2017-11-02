package com.example.gda.chattingapplu;


public class Contact {

    private int contactID;
    private String name;

    public Contact(int id, String name) {
        this.contactID = id;
        this.name = name;
    }

    public int getContactID() {
        return this.contactID;
    }

    public String getName() {
        return this.name;
    }

}
