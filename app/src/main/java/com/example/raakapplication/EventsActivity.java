package com.example.raakapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity implements eventsAdaptor.OnItemClickListener {

    BottomNavigationView bottomNavigationView;

    public ArrayList<Event> events;
    RecyclerView recyclerViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        events = new ArrayList<>();
        recyclerViewEvents = findViewById(R.id.recyclerviewEvents);

        getEvents();
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

    public void setFragment(){
        recyclerViewEvents.setAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEvents.setLayoutManager(linearLayoutManager);
        eventsAdaptor eventsAdaptor = new eventsAdaptor(events);
        eventsAdaptor.setOnItemClickListener(this::onItemClicked);
        recyclerViewEvents.setAdapter(eventsAdaptor);
    }

    public void getEvents(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Events...");
        progressDialog.show();

        databaseReference.child("Events").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for(DataSnapshot data : task.getResult().getChildren()){

                            Event event = new Event(data.getKey().toString(), data.child("Description").getValue().toString(),
                                    data.child("EventName").getValue().toString(), data.child("ImagePath").getValue().toString(),
                                    data.child("SeatsAvailable").getValue().toString());
                            events.add(event);
                }
                progressDialog.dismiss();
                setFragment();
            }
        });

    }

    @Override
    public void onItemClicked(View view, String date) {
        Intent intent = new Intent(EventsActivity.this,EventBookingDetailAcvtivity.class);
        intent.putExtra("date", date);
        EventsActivity.this.startActivity(intent);
    }

}