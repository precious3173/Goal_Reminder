package com.example.mygoals.adapter;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment>mFragmentList = new ArrayList<>();
    private final List<String>mFragmentTitle = new ArrayList<>();
    public FragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);

        setMyScroller();
    }


    private void setMyScroller() {
    }

    public void addFragment (Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
 }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}

