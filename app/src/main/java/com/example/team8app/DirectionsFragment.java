package com.example.team8app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.swing.text.View;

/* @Purpose: Class to display the directions to usb section of the app when that part of the navigation bar is selected
 * @Created  by Nathan Fish
 * @Since   08/04/2020
 * @Changed by Nathan on *12/04/2020*
 */
public class DirectionsFragment extends Fragment {

    // When Directions fragment requested, load appropriate xml file
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_directions, container, false);
    }
}