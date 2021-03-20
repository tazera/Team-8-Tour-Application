package com.example.team8app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/* @Purpose: Class that defines what happens when the home option of the navigation bar is used
 * @Created  by Rory Baker
 * @Since   08/04/2020
 * @Changed by Matthew Pearson on *20/04/2020*
 */
public class HomeFragment extends Fragment  {

    private YouTubePlayer YPlayer;

    /*The home section will need to direct the user to a page dependant on the button they pressed on the welcome page.
      If it's the open day button, then the open day fragment should be shown, otherwise the post app day should be shown.
      In the event that no button is pressed on the welcome page, the default is set to open day. */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Get button pressed from argument bundle
        int id = getArguments().getInt("id", 0);

        if (id == 2){  // If post applicant button load post applicant day page
            return inflater.inflate(R.layout.fragment_nav_post_app, container, false);
        }
        else {
            // If open day page is chosen load open day page
            View openDayPage = inflater.inflate(R.layout.fragment_nav_open_day, container, false);

            // This page has a YouTube video embedded in it, which requires to be loaded programmatically

            // Initialise player fragment
            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

            // Open a fragment transaction to replace the 'YouTubePlayer' empty fragment in the XML file with the actual player
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            /* NOTE: The IDE shows an error on this line of code, however this code actually compiles and works correctly */
            transaction.replace(R.id.YouTubePlayer, youTubePlayerFragment);
            transaction.commit();

            // Using Google api key, initialise video
            youTubePlayerFragment.initialize(getString(R.string.google_api_key), new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean restored) {
                    if (!restored) {
                        // If the 'YouTube Player is successfully initialised, cue the video using the video code
                        YPlayer = youTubePlayer;
                        YPlayer.cueVideo(getString(R.string.youtube_video_id));
                        YPlayer.setFullscreenControlFlags(0);
                    }
                }
                @Override
                public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                    // If fail to load, simply allow the program to do nothing, the player will display no connection
                }
            });

            return openDayPage;
        }

    }
}