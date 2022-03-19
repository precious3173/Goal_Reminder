package com.example.mygoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private Button addGoal;
    private FragmentPageAdapter fragmentPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());
       viewPager = findViewById(R.id.viewPager);
         setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager){
     FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());

     fragmentPageAdapter.addFragment(new MyGoalFragment(), "MyGoalFagment");
     fragmentPageAdapter.addFragment(new AddGoalsFragment(), "AddGoalsFragment");
     fragmentPageAdapter.addFragment(new GoalDescriptionFragment(), "GoalDescription");
     viewPager.setAdapter(fragmentPageAdapter);
    }

    public void setViewPager(int fragmentNumber) {

        viewPager.setCurrentItem(fragmentNumber);
    }
 }