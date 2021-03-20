package com.example.team8app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* @Purpose: The PresetTour class is used to load any screens on the 'PresetTour' part of the app
 * @Created  Matthew Pearson
 * @Since   06/04/2020
 */
public class PresetTour extends Fragment {

    /* When a tour button is pressed, this method is called. It receives and ID from the pressed
        button from NavigationBar.java and uses this ID to choose the correct screen to load.
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch(getArguments().getInt("id",0)) {
            // E.g. if welcome button is pressed, open the welcome tour xml file
            case R.id.welcome_btn:
                return inflater.inflate(R.layout.tour_welcome, container, false);
            case R.id.buildings_btn:
                return inflater.inflate(R.layout.tour_buildings, container, false);
            case R.id.design_btn:
                return inflater.inflate(R.layout.tour_design, container, false);
            case R.id.history_btn:
                return inflater.inflate(R.layout.tour_history, container, false);
            case R.id.atrium_btn:
                return inflater.inflate(R.layout.tour_atrium, container, false);
            case R.id.openlab_btn:
                return inflater.inflate(R.layout.tour_openlab, container, false);
            case R.id.staff_btn:
                return inflater.inflate(R.layout.tour_staff, container, false);
            case R.id.flatfloor_btn:
                return inflater.inflate(R.layout.tour_flatfloor, container, false);
            case R.id.terrace_btn:
                return inflater.inflate(R.layout.tour_terrace, container, false);
            case R.id.research_btn:
                return inflater.inflate(R.layout.tour_research, container, false);
            default:
                // If ID of 0, we are navigating to the preset tour menu screen
                return inflater.inflate(R.layout.activity_preset_tour, container, false);
        }

    }

}
