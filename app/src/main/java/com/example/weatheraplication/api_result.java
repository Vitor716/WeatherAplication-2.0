package com.example.weatheraplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class api_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_result);
    }

    private String AttratictionEntries() {

        final String word = toString();
        final String word_id = word.toLowerCase();

        return "https://localhost:44377";
    }

    private void getAttractions() {

    }
}