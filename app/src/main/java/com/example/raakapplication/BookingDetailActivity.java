package com.example.raakapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingDetailActivity extends AppCompatActivity {

    Button booking;
    EditText fName, lName, pNumber, seats;
    String date = "2021-01-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            date = bundle.getString("Date");
        }

        booking = findViewById(R.id.booking);

        fName = findViewById(R.id.userName);
        lName = findViewById(R.id.userSurname);
        pNumber = findViewById(R.id.userContact);
        seats = findViewById(R.id.userNumSeats);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBooking(fName.getText().toString(),lName.getText().toString(),
                        "",pNumber.getText().toString(),
                        seats.getText().toString(),date);
            }
        });

    }

    private void createBooking(String fName, String lName, String email, String pNumber, String seats, String date){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Bookings")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(date)){
                            if(snapshot.child(date).hasChild(pNumber)) {
                                Toast.makeText(BookingDetailActivity.this, "Booking Already exists", Toast.LENGTH_LONG).show();
                            }else{
                                Booking booking = new Booking(fName, lName, email, seats);
                                FirebaseDatabase.getInstance().getReference().child("Bookings")
                                        .child(date).child(pNumber).setValue(booking).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(BookingDetailActivity.this, "Booking Successful", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(BookingDetailActivity.this, "Booking Failed", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }
                        }else{
                            Booking booking = new Booking(fName, lName, email, seats);
                            FirebaseDatabase.getInstance().getReference().child("Bookings")
                                    .child(date).child(pNumber).setValue(booking).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(BookingDetailActivity.this, "Booking Successful", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(BookingDetailActivity.this, "Booking Failed", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BookingDetailActivity.this, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });

    }

}