//package com.android.lesson05androidweather;
//
//import android.app.Activity;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//
//
//public class WeatherInfoActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.weather_info_activity);
//
//        WeatherInfoFragment details = new WeatherInfoFragment();
//        details.setArguments(getIntent().getExtras());
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.weather_container, details);
//        //ft.add(android.R.id.content, details); //если activity без макета
//
//        ft.commit();
//
//
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//}
