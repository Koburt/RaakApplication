package com.example.raakapplication;

public class Reservation {

    public String name, cellphone , time, seats;

    public Reservation(String fName, String number,String time, String seats) {
        this.cellphone = number;
        this.name = fName;
        this.time = time;
        this.seats = seats;
    }

}
