package com.example.mygoals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyGoalAdapter extends RecyclerView.Adapter<MyGoalAdapter.Goals>  {
ArrayList<MyGoalArrayList>myGoalArrayLists = new ArrayList<>();
Context context;
GoalDescriptionFragment goalDescriptionFragment;


    public MyGoalAdapter (Context context,ArrayList<MyGoalArrayList>myGoalArrayLists) {
        this.myGoalArrayLists = myGoalArrayLists;
        this.context = context;
    }
    @NonNull
    @Override
    public MyGoalAdapter.Goals onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new MyGoalAdapter.Goals(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGoalAdapter.Goals holder, int position) {

        MyGoalArrayList myGoalArrayList = myGoalArrayLists.get(position);

        String goalDate = myGoalArrayList.getGoalDate();
        String goalTitle = myGoalArrayList.getGoalTitle();
        String goalDescription = myGoalArrayList.getGoalDescription();
        String id = myGoalArrayList.getUid();

        holder.goalDate.setText(goalDate);
        holder.goalTitle.setText(goalTitle);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               goalDescriptionFragment = new GoalDescriptionFragment();
               Bundle bundle = new Bundle();
               bundle.putString("id", id);
               goalDescriptionFragment.setArguments(bundle);
               ((MainActivity)context).setViewPager(2);
           }
       });

    }

    @Override
    public int getItemCount() {
        return myGoalArrayLists.size();
    }



    public class Goals extends RecyclerView.ViewHolder {

        TextView goalTitle, goalDate;

        public Goals(@NonNull View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goalTitle);
            goalDate = itemView.findViewById(R.id.goalDate);

        }


    }
}