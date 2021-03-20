package com.example.team8app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;

/* @Purpose: This class handles all navigation within the app, including sidebar and button clicks
 * @Created  by Lyubomir Tsvetkov
 * @Since   08/04/2020
 * @Changed by Nathan on *15/04/2020*
 * @Changed by Matthew since *16/04/2020*
 * @Changed by Lyubo on *17/04/2020*
 */
public class NavigationBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Navigation bar set up
    private DrawerLayout drawer;

    // Method runs when application starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load navigation bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar);

        // Create a new toolbar so that the navigation bar can overlap it instead of the pre-made toolbar being stuck ontop
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialise the Navigation bar drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Navigation bar can be open or closed, on selection of an option it should close
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //Able to rotate device and rotate navigation
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Revert back to the welcome page if app closed, includes navigation bar reverted
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_welcome);
        }
    }

    //If menu option selected, take the user to that specified page
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Switch case to find out which button was pressed and what to do.
        switch (item.getItemId()) {
            case R.id.nav_welcome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();
                break;
            case R.id.nav_directions:
                // E.g. load fragment for Directions page. 'addToBackStack' allows backwards navigation between pages
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DirectionsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_tour:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TourFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_faq:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FaqFragment()).addToBackStack(null).commit();
                break;
        }
        // Close navigation drawer when new screen loaded
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method to handle back button presses
    @Override
    public void onBackPressed() {
        // Close drawer if it is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        // Otherwise, if there are poges on the back stack, go back a page
        else if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        // Otherwise exit
        else {
            super.onBackPressed();
        }

    }

    // Button handler for buttons on home screen
    public void sendToHome(View view) {
        // Pass information about which button clicked via a 'Bundle'
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.openDay:
                // E.g. if 'OpenDay' button pressed pass and ID of 1.
                bundle.putInt("id", 1);
                break;
            case R.id.postAppVisit:
                bundle.putInt("id", 2);
                break;
        }

        // Initialise home page fragment with information bundle
        Fragment fragment = new HomeFragment();
        fragment.setArguments(bundle);

        // Load the page
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

    }


    // Button handler for buttons on the 'Own Pace' screen
    public void selfTourClick(View view) {
        int buttonId = view.getId();
        // Choose which screen to open based on button press
        if (buttonId == R.id.presetButton){
            /*
            If button press is for the preset tour screen, must pass an 'ID' of 0
            as preset tour expects a button ID defined in 'tourClick' method, so add default ID here
             */
            Bundle bundle = new Bundle();
            bundle.putInt("id", 0);
            Fragment fragment = new PresetTour();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OwnPace()).addToBackStack(null).commit();
        }

    }

    // Button handler for buttons on the 'Own Pace' screen
    public void floorClick(View view) {
        // Define the next activity you 'intend' to open
        Intent intent = new Intent(this, Floor.class);

        // Pass the ID of the pressed button to the next activity
        intent.putExtra("floor", view.getId());

        // Pass the text fo the pressed button to the next activity
        Button button = (Button) view;
        intent.putExtra("text", button.getText());

        startActivity(intent);
    }

    // Button handler for the buttons on the "Preset Tour" screen
    public void tourClick(View view){
        // Create bundle to pass data of pressed button to the 'PresetTour' class
        Bundle bundle = new Bundle();
        bundle.putInt("id", view.getId());

        // Create the new screen fragment
        Fragment fragment = new PresetTour();
        fragment.setArguments(bundle);

        // Load the Preset Tour screen using bundle data and fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    // Button handler for the buttons on the "Directions" screen
    public void directionsClick(View view){
        // Define the next activity you 'intend' to open
        Intent intent = new Intent(this, MapsActivity.class);

        // Pass the ID of the pressed button to the next activity
        intent.putExtra("route", view.getId());

        startActivity(intent);
    }


}

