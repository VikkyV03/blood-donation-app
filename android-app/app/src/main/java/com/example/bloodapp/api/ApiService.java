// android-app/app/src/main/java/com/example/bloodapp/api/ApiService.java

package com.example.bloodapp.api;

import com.example.bloodapp.models.User;
import com.example.bloodapp.models.Donor;
import com.example.bloodapp.models.BloodRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("auth/register")
    Call<User> register(@Body User user);

    @POST("auth/login")
    Call<User> login(@Body User user);

    @POST("donors")
    Call<Void> registerDonor(@Body Donor donor);

    @GET("donors/search")
    Call<List<Donor>> searchDonors(@Query("bloodType") String bloodType, @Query("city") String city);

    @POST("requests")
    Call<Void> createRequest(@Body BloodRequest request);

    @GET("requests")
    Call<List<BloodRequest>> getAllRequests();
}
