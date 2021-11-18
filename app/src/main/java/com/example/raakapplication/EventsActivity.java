package com.example.raakapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        nav();
    }

    public void nav(){
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.nav_events);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_menu:
                    startActivity(new Intent(getApplicationContext(),
                            HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_booking:
                    startActivity(new Intent(getApplicationContext(),
                            BookingActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_events:
                    return true;
            }
            return false;
        });
    }
}