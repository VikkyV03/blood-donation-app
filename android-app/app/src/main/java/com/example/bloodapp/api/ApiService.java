package com.example.bloodapp.api;

import com.example.bloodapp.models.User;
import com.example.bloodapp.models.Donor;
import com.example.bloodapp.models.BloodRequest;
import com.example.bloodapp.models.LoginResponse; // <- Make sure this is here

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("auth/register")
    Call<LoginResponse> register(@Body User user); // <- Should return LoginResponse

    @POST("auth/login")
    Call<LoginResponse> login(@Body User user); // <- Should return LoginResponse

    @POST("donors")
    Call<Void> registerDonor(@Body Donor donor);

    @GET("donors/search")
    Call<List<Donor>> searchDonors(@Query("bloodType") String bloodType, @Query("city") String city);

    @POST("requests")
    Call<Void> createRequest(@Body BloodRequest request);

    @GET("requests")
    Call<List<BloodRequest>> getAllRequests();
}
