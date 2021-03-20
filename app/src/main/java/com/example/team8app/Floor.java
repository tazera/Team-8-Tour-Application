package com.example.team8app;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/* @Purpose: The Floor class represents a Floor of the USB, which contains all the rooms on the floor.
 * The class is connected to the activity_floor1.xml file and draws the selected floor plan to the screen.
 * @Created by  Matthew Pearson
 * @Since   06/04/2020
 *  @Changed by Lyubomir Tsvetkov on *20/04/2020*
 */
public class Floor extends FragmentActivity {

    // Member variables used for referencing the FireStore database.
    private static final String TAG = "Rooms";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Method that defines what happens when the activity for activity_floor1.xml is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the floor layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor1);

        // Get the intent variable passed by the calling activity (NavigationBar class)
        Intent intent = getIntent();
        // Get the ID and name of the selected floor from intent
        int floorId = intent.getIntExtra("floor", 0);
        String floorName = intent.getStringExtra("text");

        // Draw the floor plan image based on the information passed by intent
        drawFloorPlan(floorId, floorName);

        // Get room data and draw them to the screen
        getAndDrawRooms(floorName);
    }

    /**
     * Draws the floor plan based on the floor number and name
     * @param floorId
     * @param floorName
     */
    private void drawFloorPlan(int floorId, String floorName) {
        // Using the floor ID, make the corresponding floor plan visible
        ImageView floorPlan = (ImageView) Floor.this.findViewById(floorId);
        floorPlan.setVisibility(View.VISIBLE);

        // Change the title text to the name of the floor
        TextView titleText = (TextView)Floor.this.findViewById(R.id.titleText);
        titleText.setText(floorName);
    }

    /**
     * This method queries the database for rooms of a specific floor and draws them
     * The floor is specified by the parameter
     * @param floor
     */
    private void getAndDrawRooms(String floor) {
        // Get all rooms for the specific floor
        db.collection("Rooms")
            .whereEqualTo("floor", floor)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                // The database is asynchronous, therefore requires a listener for completion
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        // Initialise a list of Room objects
                        List<Room> rooms = new ArrayList<>();
                        // Iterate through the Query results
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Convert data document to Room object and add it to the list
                            rooms.add(document.toObject(Room.class));
                            // Log a success to the database
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                        // Draw the list of rooms to the screen
                        drawRooms(rooms);
                    } else {
                        // Log an error state
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
    }

    /**
     * This method takes a list of Room objects and draws them to the screen
     * @param rooms
     */
    private void drawRooms(List<Room> rooms) {
        // Retrieve the layout object and constraint set for the activity_floor1.xml file
        ConstraintLayout parentLayout = (ConstraintLayout)findViewById(R.id.floorPlan);
        ConstraintSet set = new ConstraintSet();

        // Iterate through rooms and draw each as an image
        for (final Room room : rooms){
            // Create image view with pin image
            ImageView roomImage = new ImageView(this);
            roomImage.setImageResource(R.drawable.pin);
            roomImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // On click listener creates a dialog about the room
                    openDialog(room);
                }
            });

            // Set view id, else getId() returns -1
            roomImage.setId(View.generateViewId());
            // Add image to layout and constraint set
            parentLayout.addView(roomImage, 0);
            set.clone(parentLayout);

            // Initialise constraints and positioning of image
            set.connect(roomImage.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP, 200);
            set.setTranslationX(roomImage.getId(), 100 + room.getxPos());
            set.setTranslationY(roomImage.getId(), 350 + room.getyPos());
            roomImage.bringToFront();

            // Apply constraints
            set.applyTo(parentLayout);
        }
    }

    /**
     * This method builds a message dialog for a Room object based on its data
     * Takes a Room object
     * @param room
     */
    private void openDialog(Room room) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Set title
        alertDialog.setTitle("Room number " + room.getRoomNo());
        // Set message
        alertDialog.setMessage(room.getDescription() + "\n\nSmart card required: " + (room.getSmartCard() ? "Required" : "Not Required"));
        // Set positive button
        alertDialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing on click to close dialog
            }
        });
        // Show the created dialog
        alertDialog.show();
    }

}
