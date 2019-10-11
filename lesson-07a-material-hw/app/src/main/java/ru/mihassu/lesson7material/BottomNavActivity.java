package ru.mihassu.lesson7material;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        final Fragment1 fragment1 = Fragment1.newInstance();
        final Fragment2 fragment2 = Fragment2.newInstance();
        final Fragment3 fragment3 = Fragment3.newInstance();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_bottom_nav, fragment1);
        fragmentTransaction.commit();

        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        switch (id) {
                            case R.id.navigation_one:
                                fragmentManager.beginTransaction().
                                        replace(R.id.container_bottom_nav, fragment1).commit();
                                return true;

                            case R.id.navigation_two:
                                fragmentManager.beginTransaction().
                                        replace(R.id.container_bottom_nav, fragment2).commit();
                                return true;

                            case R.id.navigation_three:
                                fragmentManager.beginTransaction().
                                        replace(R.id.container_bottom_nav, fragment3).commit();
                                return true;
                        }

                        return false;
                    }
                }
        );
    }

}
