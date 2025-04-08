// android-app/app/src/main/java/com/example/bloodapp/api/ApiClient.java

package com.example.bloodapp.api;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor((Interceptor.Chain chain) -> {
                    Request original = chain.request();
                    SharedPreferences prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                    String token = prefs.getString("token", null);

                    Request.Builder builder = original.newBuilder()
                        .header("Content-Type", "application/json");
                    if (token != null) {
                        builder.header("Authorization", "Bearer " + token);
                    }

                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .build();

            retrofit = new Retrofit.Builder()
                .baseUrl("https://blood-donation-app-3efn.onrender.com/api/") // For Android emulator. Use your LAN IP if testing on real device.
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        }
        return retrofit;
    }
}
