package ru.mihassu.lesson7material;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> tabTitles = new ArrayList<>();
    private Activity activity;

    public MyPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public MyPageAdapter(@NonNull FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
//        this.tabTitles.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return tabTitles.get(position);
        return activity.getResources().getStringArray(R.array.tabs_titles)[position];
    }
}
