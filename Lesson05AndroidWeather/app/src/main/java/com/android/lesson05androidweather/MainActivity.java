package com.android.lesson05androidweather;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import static com.android.lesson05androidweather.CitiesFragment.CITY_NAME;
import static com.android.lesson05androidweather.CitiesFragment.CURRENT_CITY_POS;


public class MainActivity extends AppCompatActivity {

    private String cityName;
    private int currentPosition = 0;
    private boolean isExistPlace;
    TextView tViewSelectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectCityButton = findViewById(R.id.buttonSelectCity);
        tViewSelectedCity = findViewById(R.id.tViewSelectedCity);
        Button showWeatherButton = findViewById(R.id.buttonShowWeather);

        try {
            cityName = getIntent().getExtras().getString(CITY_NAME, null);
            currentPosition = getIntent().getExtras().getInt(CURRENT_CITY_POS, 0);
        } catch (NullPointerException e) {
            tViewSelectedCity.setText(R.string.selectcityStr);
        }

        if(cityName != null){
            tViewSelectedCity.setText(cityName);
        } else {
            tViewSelectedCity.setText(R.string.selectcityStr);
        }


        //Кпопка выбора города вызывает activity со списком городов
        selectCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CitiesActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        //Кнопка показа погоды
        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherInfoFragment det = WeatherInfoFragment.create((tViewSelectedCity.getText()).toString(), currentPosition, getConditionsFromCheckBoxes());
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if(getFragmentManager().findFragmentById(R.id.weather_container) == null) {
                    ft.add(R.id.weather_container, det);

                } else {
                    ft.replace(R.id.weather_container, det);
                }

                ft.commit();
            }
        });


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Читаем установленное значение размера шрифта
        float fontSize = Float.parseFloat(prefs.getString(getString(R.string.pref_size), "20"));

        //Применяем настройки
        tViewSelectedCity.setTextSize(fontSize);

    }


    //Задаем макет для верхнего меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Назначаем действия для верхнего меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return true;
        }
    }

    public boolean[] getConditionsFromCheckBoxes() {

        CheckBox showWind = findViewById(R.id.showWindChB);
        CheckBox showPressure = findViewById(R.id.showPressureChB);
        boolean[] c = {showWind.isChecked(), showPressure.isChecked()};

        return c;
    }
}
