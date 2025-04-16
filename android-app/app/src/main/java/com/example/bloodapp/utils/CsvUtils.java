package com.example.bloodapp.utils;

import android.content.Context;
import android.util.Log;

import com.example.bloodapp.models.BloodBank;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<BloodBank> readBloodBanksFromAssets(Context context, String filename) {
        List<BloodBank> bloodBanks = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open("blood-banks.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                if (tokens.length >= 5) {
                    String name = tokens[0].trim();
                    String address = tokens[1].trim();
                    String city = tokens[2].trim();
                    String state = tokens[3].trim();
                    String phone = tokens[4].trim();

                    BloodBank bank = new BloodBank(name, address, city, state, phone);
                    bloodBanks.add(bank);
                }
            }

            reader.close();
        } catch (Exception e) {
            Log.e("CsvUtils", "❌ Error reading CSV file", e);
        }

        return bloodBanks;
    }
}
