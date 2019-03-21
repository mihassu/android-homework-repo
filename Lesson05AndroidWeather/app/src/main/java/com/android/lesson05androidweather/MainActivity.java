package com.android.lesson05androidweather;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
//    private boolean isExistPlace;
    private TextView tViewSelectedCity;
    private CheckBox showWindCheckBox;
    private CheckBox showPressureCheckBox;
    public static final int REQUEST_ACCES_TYPE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectCityButton = findViewById(R.id.buttonSelectCity);
        tViewSelectedCity = findViewById(R.id.tViewSelectedCity);
//        Button showWeatherButton = findViewById(R.id.buttonShowWeather);
        showWindCheckBox = findViewById(R.id.showWindChB);
        showPressureCheckBox = findViewById(R.id.showPressureChB);

        //Создать объект класса Saver для сохранения состояния
        Saver.getInstance();


        //Кпопка выбора города вызывает activity со списком городов
        selectCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CitiesActivity.class);
                startActivityForResult(intent, REQUEST_ACCES_TYPE);

            }
        });

//        //Кнопка показа погоды
//        showWeatherButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WeatherInfoFragment det = WeatherInfoFragment.create((tViewSelectedCity.getText()).toString(), currentPosition, getConditionsFromCheckBoxes());
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                if(getFragmentManager().findFragmentById(R.id.weather_container) == null) {
//                    ft.add(R.id.weather_container, det);
//
//                } else {
//                    ft.replace(R.id.weather_container, det);
//                }
//
//                ft.commit();
//            }
//        });

        showWindCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherInfo();
            }
        });

        showPressureCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherInfo();
            }
        });

        //Показать погоду
        showWeatherInfo();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, cityName);

        //Сохранить состояния CheckBox в Saver
        Saver.getInstance().setWindIsChecked(((CheckBox)findViewById(R.id.showWindChB)).isChecked());
        Saver.getInstance().setPressureIsChecked(((CheckBox)findViewById(R.id.showPressureChB)).isChecked());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cityName = savedInstanceState.getString(CITY_NAME);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Читаем установленное значение размера шрифта
        float fontSize = Float.parseFloat(prefs.getString(getString(R.string.pref_size), "20"));

        //Применяем настройки
        tViewSelectedCity.setTextSize(fontSize);

        //Устанавливаем состояния CheckBox полученные из Saver
        showWindCheckBox.setChecked(Saver.getInstance().isWindIsChecked());
        showPressureCheckBox.setChecked(Saver.getInstance().isPressureIsChecked());

        if(cityName != null){
            tViewSelectedCity.setText(cityName);
        }
        showWeatherInfo();

    }

    //Получаем данные из activity для которой вызывали startActivityForResult (кнопка Выбор города)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_ACCES_TYPE){
            if(resultCode == RESULT_OK){
                try {
                    cityName = data.getStringExtra(CITY_NAME);
                    currentPosition = data.getIntExtra(CURRENT_CITY_POS, 0);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                if(cityName != null){
                    tViewSelectedCity.setText(cityName);
                }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

        boolean[] c = {showWindCheckBox.isChecked(), showPressureCheckBox.isChecked()};

        return c;
    }

    public void showWeatherInfo() {
        WeatherInfoFragment det = WeatherInfoFragment.create((tViewSelectedCity.getText()).toString(), currentPosition, getConditionsFromCheckBoxes());
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if(getFragmentManager().findFragmentById(R.id.weather_container) == null) {
            ft.add(R.id.weather_container, det);

        } else {
            ft.replace(R.id.weather_container, det);
        }

        ft.commit();
    }
}
