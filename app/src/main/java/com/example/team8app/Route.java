package com.example.team8app;


import com.google.firebase.firestore.GeoPoint;
import java.util.List;

/**
 * @Purpose Generic class to describe a Route to be displayed on a GoogleMap object.
 * @Author Jharik Richardson
 * @Date 8th April 2020
 */
public class Route {

    private String routeName;
    private List<GeoPoint> stops;
    private String originTitle;
    private String originSnippet;
    private String destinationTitle;
    private String destinationSnippet;

    /**
     * Empty constructor required for Firestore serialisation.
     */
    private Route () {}

    /**
     * Constructor to initialise values of a Stop.
     *
     * @param routeName the name of the route
     * @param stops the collection of stops on the map
     * @param originTitle the name of the origin
     * @param originSnippet a description of the origin
     * @param destinationTitle the name of the destination
     * @param destinationSnippet a description of the destination
     */
    public Route (String routeName, List<GeoPoint> stops, String originTitle, String originSnippet, String destinationTitle, String destinationSnippet){
        this.routeName = routeName;
        this.stops = stops;
        this.originTitle = originTitle;
        this.originSnippet = originSnippet;
        this.destinationTitle = destinationTitle;
        this.destinationSnippet = destinationSnippet;
    }

    // Basic getters and setters that are required for DataBase object serialisation

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<GeoPoint> getStops() {
        return stops;
    }

    public void setStops(List<GeoPoint> stops) {
        this.stops = stops;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public String getDestinationTitle() {
        return destinationTitle;
    }

    public void setDestinationTitle(String destinationTitle) {
        this.destinationTitle = destinationTitle;
    }

    public String getOriginSnippet() {
        return originSnippet;
    }

    public void setOriginSnippet(String originSnippet) {
        this.originSnippet = originSnippet;
    }

    public String getDestinationSnippet() {
        return destinationSnippet;
    }

    public void setDestinationSnippet(String destinationSnippet) {
        this.destinationSnippet = destinationSnippet;
    }
}
