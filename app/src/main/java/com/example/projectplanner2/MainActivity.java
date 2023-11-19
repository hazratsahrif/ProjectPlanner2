package com.example.projectplanner2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectplanner2.databinding.ActivityMainBinding;
import com.example.projectplanner2.presentation.pager_adapter.ViewPagerAdapter;



public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Create a PagerAdapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Set the adapter to the ViewPager
        binding.viewPager.setAdapter(pagerAdapter);

        // Connect the TabLayout with the ViewPager
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        setContentView(binding.getRoot());
    }
}