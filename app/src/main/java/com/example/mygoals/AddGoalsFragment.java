package com.example.mygoals;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class AddGoalsFragment extends Fragment {

   DatePicker myGoalDate;
   TimePicker myGoalTime;
   Button setGoal, cancel;
   EditText goalTitle, goalDescription;
   DatabaseReference databaseReference;
   private DatabaseReference reference;
   FirebaseAuth firebaseAuth;
   FirebaseUser user;
    String uid;
    Bundle bundle;
    AlarmReceiver alarmReceiver;
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
        myGoalTime = view.findViewById(R.id.GoalTime);

        myGoalTime.setIs24HourView(true);

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

      notificationChannel();

      setGoal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              progressDialog = new ProgressDialog(getContext());

              progressDialog.setTitle("My Goal");

              progressDialog.setMessage("Loading.....");

              progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

              progressDialog.show();




              setDatabase(myGoalDate, myGoalTime, goalTitle, goalDescription);
          }
      });


        return view;
    }

    private void notificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "My Goal Reminder";
            String description = goalTitle.toString();
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel("My Goal Reminder", name, importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

    private void setDatabase(DatePicker myGoalDate, TimePicker myGoalTime, EditText goalTitle, EditText goalDescription) {


        int day = myGoalDate.getDayOfMonth();
        int month = myGoalDate.getMonth();
        int year = myGoalDate.getYear();
        int hour = myGoalTime.getHour();
        int minute = myGoalTime.getMinute();

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

                    Intent intent = new Intent(getContext(), AlarmReceiver.class);
                    intent.putExtra("contentTitle", goalT);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.YEAR, year);

                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                    Toast.makeText(getContext(), "Goal updated", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("goalTitle", "" + goalTitle);
                    MyGoalFragment myGoalFragment = new MyGoalFragment();
                    myGoalFragment.setArguments(bundle);
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