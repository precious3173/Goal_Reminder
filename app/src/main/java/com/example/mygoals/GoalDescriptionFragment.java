package com.example.mygoals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GoalDescriptionFragment extends Fragment {
Bundle bundle;
TextView goalDescriptionText;
String goalDescription, id;
DatabaseReference databaseReference, reference;
FirebaseAuth auth;
FirebaseUser user;
    public GoalDescriptionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goal_description, container, false);

        bundle = this.getArguments();
        if (bundle != null){

            goalDescription = bundle.getString("goalDescription");
        }

        goalDescriptionText = view.findViewById(R.id.text);


        goalDescriptionText.setText(goalDescription);

        return view;


    }


}