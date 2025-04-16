// BloodBank.java
package com.example.bloodapp.models;

public class BloodBank {
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;

    public BloodBank(String name, String address, String city, String state, String phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPhone() { return phone; }
    public String getContact() { return phone; }
}