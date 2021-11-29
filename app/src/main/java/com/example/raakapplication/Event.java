package com.example.raakapplication;

public class Event {

    public String date, description, eventName, imagePath, seatsAvailable;

    public Event(String date, String description, String eventName, String imagePath, String seatsAvailable){
        this.date = date;
        this.description =  description;
        this.eventName = eventName;
        this.imagePath = imagePath;
        this.seatsAvailable = seatsAvailable;
    }

}
