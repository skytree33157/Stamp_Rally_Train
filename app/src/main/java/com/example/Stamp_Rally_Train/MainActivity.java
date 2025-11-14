package com.example.Stamp_Rally_Train;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        OnBackPressedCallback callback = new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                viewPager.setCurrentItem(0);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                callback.setEnabled(position == 1);
            }
        });
    }
}