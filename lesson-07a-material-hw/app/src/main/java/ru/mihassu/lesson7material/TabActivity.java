package ru.mihassu.lesson7material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_layout);


        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(Fragment1.newInstance());
        adapter.addFragment(Fragment2.newInstance());
        adapter.addFragment(Fragment3.newInstance());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}
