package com.example.mygoals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyGoalFragment extends Fragment {

    Button addGoal;
    MyGoalAdapter myGoalAdapter;
    ArrayList<MyGoalArrayList> myGoalArrayListArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseReference databaseReference, databaseReference2;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    ImageView notificationBell;
    Bundle bundle;
    String id;
    public MyGoalFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view =inflater.inflate(R.layout.fragment_my_goal, container, false);


         addGoal = view.findViewById(R.id.addGoal);

         addGoal.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ((MainActivity)getActivity()).setViewPager(1);
             }
         });

        databaseReference2= FirebaseDatabase.getInstance().getReference("Notification");
        firebaseAuth = FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();


        bundle = this.getArguments();
        if (bundle != null){

            id = bundle.getString("id");
        }

         notificationBell.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });

         recyclerView = view.findViewById(R.id.recyclerview);

         myGoalAdapter = new MyGoalAdapter(getContext(),myGoalArrayListArrayList);




         goalList();
         notification();

         return view;
    }

    private void notification() {




    }

    private void goalList() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Goals");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             myGoalArrayListArrayList.clear();

             for (DataSnapshot ds : snapshot.getChildren()){
                 MyGoalArrayList array = ds.getValue(MyGoalArrayList.class);
                 myGoalArrayListArrayList.add(array);
             }
             recyclerView.setAdapter(myGoalAdapter);
             recyclerView.setHasFixedSize(true);
             myGoalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}