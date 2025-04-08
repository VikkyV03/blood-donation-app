// android-app/app/src/main/java/com/example/bloodapp/models/BloodRequest.java

package com.example.bloodapp.models;

public class BloodRequest {
    private String bloodType;
    private String hospitalName;
    private double[] location; // [longitude, latitude]
    private String reason;

    public BloodRequest() {}

    public BloodRequest(String bloodType, String hospitalName, double[] location, String reason) {
        this.bloodType = bloodType;
        this.hospitalName = hospitalName;
        this.location = location;
        this.reason = reason;
    }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public double[] getLocation() { return location; }
    public void setLocation(double[] location) { this.location = location; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
