package com.android.mymaterial;

import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;

//implements NavigationView.OnNavigationItemSelectedListener

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private TextView mFruitsTextView; //счетчик у пункта меню

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFAB();
        initNavigationDrawer();

        Button buttonTest = findViewById(R.id.button_test);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(navigationView, "onClickTest", Snackbar.LENGTH_LONG).show();
                Log.d(TAG, "onClickTest: ");
            }
        });

//        ActionBarDrawerToggle 0:52
//        drawer = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_fruits)
//                .setDrawerLayout(drawer)
//                .build();
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        initCounters();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }



    @Override
    public void onBackPressed() {
        //чтобы при открытом drawer при нажатии кнопки Назад закрывался drawer, а не приложение
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Toast.makeText(this, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();

        } else {
            super.onBackPressed();
        }
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initNavigationDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        //Гамбургер
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); /*синхронизация NavigationDrawer и гамбургера
        (гамбургер превращается в стрелку)*/

        navigationView = findViewById(R.id.nav_view);//Выдвижное меню (NavigationDrawer)
//        navigationView.setNavigationItemSelectedListener(this); //Слушатель на пункты меню

        //слушатель на выдвигание/задвигание, открытие/закрытие NavigationDrawer
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                Log.d(TAG, "onDrawerSlide: ");
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.d(TAG, "onDrawerOpened: ");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Log.d(TAG, "onDrawerClosed: ");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.d(TAG, "onDrawerStateChanged: ");
            }
        });
    }

    private void initCounters() {
        mFruitsTextView = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_fruits));
        mFruitsTextView.setText("55");
        mFruitsTextView.setGravity(Gravity.CENTER_VERTICAL);
        mFruitsTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        mFruitsTextView.setTypeface(null, Typeface.BOLD);
    }

    //обработка пунктов меню NavigationDrawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Snackbar.make(navigationView, "onNavigationItemSelected", Snackbar.LENGTH_LONG).show();
        Log.d(TAG, "onNavigationItemSelected: ");
        switch (id) {
            case R.id.nav_fruits:
//                navController.navigate(R.id.nav_fruits);
                Toast.makeText(this, "Фрукты", Toast.LENGTH_SHORT).show();
                Snackbar.make(navigationView, "Фрукты", Snackbar.LENGTH_LONG).show();
                System.out.println("nav_fruits");
                break;

            case R.id.nav_vegetables:
                Toast.makeText(this, "Овощи", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        Toast.makeText(this, "closeDrawer", Toast.LENGTH_SHORT).show();
        return true;
    }
}
