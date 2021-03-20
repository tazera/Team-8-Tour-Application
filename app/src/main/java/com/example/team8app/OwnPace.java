package com.example.team8app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* @Purpose: The OwnPace class is used as the activity for all the 'own pace' screen
 * @Created  Matthew Pearson
 * @ Changed by Lyubomir
 * @Since   06/04/2020
 */



public class OwnPace extends Fragment {

    // When the OwnPace screen is requested, this method loads the correct XML file
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_own_pace, container, false);

    }



}