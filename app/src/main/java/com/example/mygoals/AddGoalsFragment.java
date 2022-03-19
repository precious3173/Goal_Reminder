package com.example.mygoals;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class AddGoalsFragment extends Fragment {

   DatePicker myGoalDate;
   Button setGoal, cancel;
   EditText goalTitle, goalDescription;
   DatabaseReference databaseReference;
   private DatabaseReference reference;
   FirebaseAuth firebaseAuth;
   FirebaseUser user;
    String uid;
    String messageTime;
   ProgressDialog progressDialog;

    public AddGoalsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_goals, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference("Goals");
        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        if(user != null){
            uid = user.getUid();
        }
         messageTime = "" + System.currentTimeMillis();


            reference = databaseReference.child(messageTime);






        myGoalDate = view.findViewById(R.id.GoalDate);

      cancel= view.findViewById(R.id.cancel);

      cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ((MainActivity)getActivity()).setViewPager(0);
          }
      });

       goalTitle= view.findViewById(R.id.GoalTitle);

       goalDescription = view.findViewById(R.id.GoalDescription);

      setGoal = view.findViewById(R.id.setGoal);


      setGoal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              progressDialog = new ProgressDialog(getContext());

              progressDialog.setTitle("My Goal");

              progressDialog.setMessage("Loading.....");

              progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

              progressDialog.show();




              setDatabase(myGoalDate, goalTitle, goalDescription);
          }
      });


        return view;
    }

    private void setDatabase(DatePicker myGoalDate, EditText goalTitle, EditText goalDescription) {


        int day = myGoalDate.getDayOfMonth();
        int month = myGoalDate.getMonth();
        int year = myGoalDate.getYear();

        String date = (month + 1) + "/" + day + "/" + year;
        String goalT = goalTitle.getText().toString();
        String goalD = goalDescription.getText().toString();



        Map<String, Object> user = new HashMap<>();
        user.put("goalTitle", "" + goalT);
        user.put("goalDescription", "" + goalD);
        user.put("goalDate", "" + date);
        user.put("id", "" + messageTime);


           reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Goal is updated", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).setViewPager(0);

                }
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(getContext(), e + "error", Toast.LENGTH_SHORT).show();
               }
           });




    }
}