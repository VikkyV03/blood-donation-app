// android-app/app/src/main/java/com/example/bloodapp/models/Donor.java

package com.example.bloodapp.models;

public class Donor {
    private String bloodType;
    private String city;
    private String lastDonated;
    private boolean availabilityStatus;
    private double latitude;
    private double longitude;

    public Donor() {}

    public Donor(String bloodType, String city, String lastDonated, boolean availabilityStatus, double latitude, double longitude) {
        this.bloodType = bloodType;
        this.city = city;
        this.lastDonated = lastDonated;
        this.availabilityStatus = availabilityStatus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

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
