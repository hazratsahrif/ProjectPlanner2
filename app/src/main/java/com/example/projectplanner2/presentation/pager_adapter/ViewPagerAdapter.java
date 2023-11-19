package com.example.projectplanner2.presentation.pager_adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projectplanner2.presentation.tabs.HomeFragment;
import com.example.projectplanner2.presentation.tabs.QuoteFragment;
import com.example.projectplanner2.presentation.tabs.ReportFragment;
import com.example.projectplanner2.presentation.tabs.UpdateFragment;

import java.util.ArrayList;
import java.util.List;
//
//public class TabsPagerAdapter extends FragmentPagerAdapter {
//    private final List<Fragment> fragmentList = new ArrayList<>();
//    private final List<String> fragmentTitleList = new ArrayList<>();
//    public TabsPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//    public void addFragment(Fragment fragment, String title) {
//        fragmentList.add(fragment);
//        fragmentTitleList.add(title);
//    }
//
//
//    @Override
//    public Fragment getItem(int i) {
//        return fragmentList.get(i);
//    }
//
//    @Override
//    public int getCount() {
//        return fragmentList.size();
//    }
//}
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new HomeFragment();
        }
        else if (position == 1)
        {
            fragment = new QuoteFragment();
        }
        else if (position == 2)
        {
            fragment = new ReportFragment();
        }
        else if (position == 3)
        {
            fragment = new UpdateFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Home";
        }
        else if (position == 1)
        {
            title = "Quote";
        }
        else if (position == 2)
        {
            title = "Report";
        }
        else if (position == 3)
        {
            title = "Update";
        }
        return title;
    }
}