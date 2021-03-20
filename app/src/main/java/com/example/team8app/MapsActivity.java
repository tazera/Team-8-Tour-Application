package com.example.team8app;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/* @Purpose: The Maps Activity class handles drawing Routes and Map markers on our USB directions screen
 * @Created  Matthew Pearson
 * @Since   10/04/2020
 * @Changed Jharik Richardson
 * @Since 15/04/2020
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Google Maps object to manipulate
    private GoogleMap mMap;
    // DataBase object and logging TAG
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Routes";

    // When map page is requested, load the map and start the asynchronous call to get the map with a call back of onMapReady
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get the intent variable passed by the calling activity
        Intent intent = getIntent();
        // Get the ID and name of the selected route from intent
        int routeId = intent.getIntExtra("route", 0);
        String routeName;
        // Load the appropriate route name into the variable
        switch(routeId){
            case(R.id.main_campus_btn):
                routeName = getString(R.string.main_campus_text);
                break;
            case(R.id.haymarket_btn):
                routeName = getString(R.string.haymarket_text);
                break;
            case(R.id.central_station_btn):
                routeName = getString(R.string.central_station_text);
                break;
            case(R.id.eldon_square_btn):
                routeName = getString(R.string.eldon_square_text);
                break;
            default:
                routeName = "not found";
        }

        // Retrieve route information from database and draw the route based on selected route
        getAndDrawRoute(routeName);
    }

    /**
     *  This method takes a string routeName and queries the database for the corresponding route,
     *  the method serialises the result object and draws the route
     * @param routeName
     */
    private void getAndDrawRoute(String routeName) {
        // Get the information about the specified Route
        db.collection("Routes")
                .whereEqualTo("routeName", routeName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    // The database is asynchronous, therefore requires a listener for completion
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Iterate through the resultant documents (will only return one)
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Convert data document to Route object and draw the Route
                                Route route =  document.toObject(Route.class);
                                drawRoute(route);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            // Error state
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     * This method takes a route object and draws it to the map
     * @param route
     */
    private void drawRoute(Route route){
        // Iterate through the Route data, and convert 'GeoPoints' to 'LatLngs'
        List<LatLng> points = new ArrayList<LatLng>();
        for(GeoPoint geoPoint: route.getStops()){
            points.add(new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude()));
        }

        // Create a Google maps 'Polyline' to draw the route using the LatLng 'points'
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(10).color(Color.RED);
        for(int i=0; i < points.size() - 1; i++){
            polylineOptions.add(points.get(i), points.get(i+1));
        }
        // Initialise route line
        mMap.addPolyline(polylineOptions);

        // Add markers for start and end point using origin and destination data
        LatLng origin = points.get(0);
        LatLng destination = points.get(points.size() - 1);
        // Draw origin
        mMap.addMarker(new MarkerOptions()
                .position(origin)
                .title(route.getOriginTitle())
                .snippet(route.getOriginSnippet())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        // Draw destination
        mMap.addMarker(new MarkerOptions()
                .position(destination)
                .title(route.getDestinationTitle())
                .snippet(route.getDestinationSnippet())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        // Move camera map to destination
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 15));
    }

    /* This is an onClickHandler for the points of interest buttons, i.e. 'Food and Drink', 'Accommodation' etc
       which will draw received stops to the screen
     */
    public void showStopsClick(View view){
        Button button = (Button) view;
        // Query database for information about the specified Route
        db.collection("Stops")
                // Use selected button to get stop type
                .whereEqualTo("type", button.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    // The database is asynchronous, therefore requires a listener for completion
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Iterate through the resultant documents
                            List<Stop> stops = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Convert data document to Stop object and add it to the list of stops
                                Stop stop =  document.toObject(Stop.class);
                                stops.add(stop);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }

                            // Draw the resultant stops to the screen
                            drawStops(stops);
                        } else {
                            // Error state
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     * This method takes a list of stops and draws them to the screen
     * @param stops
     */
    private void drawStops(List<Stop> stops){
        // Iterate through stops
        for (Stop stop: stops){
            // Add to the screen using Stop information
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(stop.getLocation().getLatitude(), stop.getLocation().getLongitude()))
                    .title(stop.getName())
                    .snippet(stop.getDescription()));
        }
    }

}
