package com.example.team8app;

import com.google.firebase.firestore.GeoPoint;


/**
 * @Purpose Generic class to describe a "Stop" (point of interest ie. restaurant, university building)
 * on the map.
 * @Authour Jharik A Richardson
 * @Since 7th April 2020
 */
public class Stop {

    private String address;
    private String description;
    private GeoPoint location;
    private String name;
    private String type;

    /**
     * Empty constructor required for Firestore serialisation.
     */
    public Stop(){ }

    /**
     * Constructor to initialise values of a Stop.
     *
     * @param name the name of the 'Stop'
     * @param type the type category of the 'Stop' (ie restaurant, university building)
     * @param address the physical address of the thr 'Stop'
     * @param description a brief description about the 'Stop'
     * @param location the geographic longitude and latitude of the 'Stop'
     */
    public Stop (String name, String type, String address, String description, GeoPoint location) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
