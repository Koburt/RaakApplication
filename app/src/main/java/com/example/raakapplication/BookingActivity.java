package com.example.raakapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookingActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button buttonBooking;
    CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        nav();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendarView = findViewById(R.id.calendarView);
        buttonBooking = findViewById(R.id.bookDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month, dayOfMonth);

                calendarView.setDate(calendar.getTimeInMillis());
            }
        });

        buttonBooking.setOnClickListener(v -> {
            Intent moveOn = new Intent(BookingActivity.this,BookingDetailActivity.class);
            moveOn.putExtra("Date",simpleDateFormat.format(new Date(calendarView.getDate())));
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