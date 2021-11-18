package com.example.raakapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button buttonBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        nav();

        buttonBooking = findViewById(R.id.bookDate);
        buttonBooking.setOnClickListener(v -> {
            Intent moveOn = new Intent(BookingActivity.this,BookingDetailActivity.class);
            startActivity(moveOn);
        });
    }

    public void nav(){
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.nav_booking);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_menu:
                    startActivity(new Intent(getApplicationContext(),
                            HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_booking:
                    return true;
                case R.id.nav_events:
                    startActivity(new Intent(getApplicationContext(),
                            EventsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}