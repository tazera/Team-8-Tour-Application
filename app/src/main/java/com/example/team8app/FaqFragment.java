package com.example.team8app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * @Purpose: Display's the FAQ sections of the app. Based on the on the FAQ selected, the appropriate
 * answer is retrieved from a FireStore database and then displayed to the user.
 *
 * @Created  by Nathan Fish
 * @Since   08/04/2020
 * @Changed by Nathan on 12/04/2020
 * @Changed by Lyubomir on 14/04/2020
 */
public class FaqFragment extends Fragment {

    private TextView textViewData;

    /**
     * When faq screen is requested, this method is called and it gives the buttons onclick listener
     * that get the corresponding answers from the database
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Load correct XML file
        View rootView = inflater.inflate(R.layout.fragment_nav_faq, container, false);
        textViewData = rootView.findViewById(R.id.text_view_faq);
        // Get buttons and apply onClickListeners
        Button q1 = rootView.findViewById(R.id.question1);
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocument("Answear1");
            }
        });

        Button q2 = rootView.findViewById(R.id.question2);
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocument("Answear2");
            }
        });

        Button q3 = rootView.findViewById(R.id.question3);
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocument("Answear3");
            }
        });

        Button q4 = rootView.findViewById(R.id.question4);
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocument("Answear4");
            }
        });

        Button q5 = rootView.findViewById(R.id.question5);
        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocument("Answear5");
            }
        });

        // Load view
        return rootView;
    }

    /**
     * Retrieves information related  to the Q&A section from a FireBase database and stores the
     * retrieves information as a string, then draw/display the information onto the page fragment.
     *
     * @param documentName the database field ID
     */
    public void getDocument(String documentName) {
        final String TAG = "FaqFragment";
        FirebaseFirestore.getInstance()
                .collection("FAQ")
                .document(documentName)
                .get()
                .addOnCompleteListener( new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Get resultant answer and draw it to the screen
                                String result = document.getString("answear");
                                textViewData.setText(result);
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }
}
