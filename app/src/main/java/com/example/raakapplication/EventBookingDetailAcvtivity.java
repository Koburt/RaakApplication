package com.example.raakapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventBookingDetailAcvtivity extends AppCompatActivity {

    Button book;
    String date, date2;

    EditText email, fName, lName, pNumber, seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking_detail_acvtivity);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");



        book = findViewById(R.id.booking);

        email = findViewById(R.id.email);
        fName = findViewById(R.id.userName);
        lName = findViewById(R.id.userSurname);
        pNumber = findViewById(R.id.userContact);
        seats = findViewById(R.id.userNumSeats);

        setDetails();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBooking(fName.getText().toString(),lName.getText().toString(),
                        email.getText().toString(),pNumber.getText().toString(),
                        seats.getText().toString(),date);
            }
        });

    }

    private boolean checkDetails(){
        if(checkName() && checkNumber() && checkCheckSeats() && checkLName() && checkEmail()){
            return true;
        }else {
            return false;
        }
    }

    private boolean checkName(){
        if(!fName.getText().toString().equals("") && fName.getText().toString().matches("^[a-zA-Z]*$")){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkLName(){
        if(!lName.getText().toString().equals("") && lName.getText().toString().matches("^[a-zA-Z]*$")){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkNumber(){
        if(!pNumber.getText().toString().equals("") && pNumber.getText().toString().matches("^\\d{9}$")){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkEmail(){
        if(!email.getText().toString().equals("") && fName.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+")){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkCheckSeats(){
        if(!seats.getText().toString().equals("") && seats.getText().toString().matches("[0-9]+") && seats.getText().toString().length() <3){
            return true;
        }else{
            return false;
        }
    }

    public void setDetails(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatText = new SimpleDateFormat("dd MMMM");
        try{
            Date date1 = format.parse(date);
            date2 = simpleDateFormatText.format(date1);
        }catch (Exception e){

        }

        book.setText("Book for "+date2);
    }

    private void createBooking(String fName, String lName, String email, String pNumber, String seats, String date){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Bookings")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(date)){
                            if(snapshot.child(date).hasChild(pNumber)) {
                                Toast.makeText(EventBookingDetailAcvtivity.this, "Booking Already exists", Toast.LENGTH_LONG).show();
                            }else{
                                Booking booking = new Booking(fName, lName, email, seats);
                                FirebaseDatabase.getInstance().getReference().child("Bookings")
                                        .child(date).child(pNumber).setValue(booking).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(EventBookingDetailAcvtivity.this, "Booking Successful", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(EventBookingDetailAcvtivity.this, "Booking Failed", Toast.LENGTH_LONG).show();
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
                                                Toast.makeText(EventBookingDetailAcvtivity.this, "Booking Successful", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(EventBookingDetailAcvtivity.this, "Booking Failed", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EventBookingDetailAcvtivity.this, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });

    }

}