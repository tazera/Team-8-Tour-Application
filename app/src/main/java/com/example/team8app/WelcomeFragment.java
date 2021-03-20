package com.example.team8app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Welcome page is shown, has functionality to move to set which home page will be displayed based
 * on button clicks
 *
 * @Author Nathan Fish
 * @Since 08/04/2020
 * @Change Nathan on *15/04/2020*
 * @Change Lyubo on *18/04/2020
 */
public class WelcomeFragment extends Fragment{

    // This method is run when navigating to this screen. It simply loads the correct XML file
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_landing_pg, container, false);
    }


}
