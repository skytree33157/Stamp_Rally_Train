package com.example.Stamp_Rally_Train;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    private static final int NUM_PAGES = 2;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Stamp();
            case 1:
                return new Statistics();
            default:
                return new Stamp();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}