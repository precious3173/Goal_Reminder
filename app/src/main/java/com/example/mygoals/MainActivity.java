package com.example.mygoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addGoal;
    private FragmentPageAdapter fragmentPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());

    }

}