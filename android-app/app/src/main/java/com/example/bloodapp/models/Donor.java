package com.example.bloodapp.models;

public class Donor {
    private String name;
    private String phone;
    private String bloodType;
    private String city;
    private String lastDonated;
    private boolean availabilityStatus;
    private double latitude;
    private double longitude;

    public Donor() {}

    public Donor(String name, String phone, String bloodType, String city, String lastDonated,
                 boolean availabilityStatus, double latitude, double longitude) {
        this.name = name;
        this.phone = phone;
        this.bloodType = bloodType;
        this.city = city;
        this.lastDonated = lastDonated;
        this.availabilityStatus = availabilityStatus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getLastDonated() { return lastDonated; }
    public void setLastDonated(String lastDonated) { this.lastDonated = lastDonated; }

    public boolean isAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(boolean availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
