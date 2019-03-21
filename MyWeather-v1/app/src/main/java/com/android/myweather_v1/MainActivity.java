package com.android.myweather_v1;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.android.myweather_v1.OptionsActivity.OPTIONS_CONDITIONS;
import static com.android.myweather_v1.OptionsActivity.SHOWPRESSURE_OPTION;
import static com.android.myweather_v1.OptionsActivity.SHOWWIND_OPTION;


public class MainActivity extends AppCompatActivity {

    private String cityName;
    private int currentPosition = 0;
//    private boolean isExistPlace;
    private TextView tViewSelectedCity;

    public static final int REQUEST_ACCES_TYPE = 1;
    public static final String CURRENT_CITY_POS = "CurrentCity";
    public static final String CITY_NAME = "cityName";
    public static final String CONDITIONS = "Conditions";
    public static final String CURRENT_CITY = "CurrentCity";

    public SharedPreferences condOptions;
    private boolean showWind = false;
    private boolean showPressure = false;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectCityButton = findViewById(R.id.buttonSelectCity);
        tViewSelectedCity = findViewById(R.id.tViewSelectedCity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        //Создать объект класса Saver для сохранения состояния
//        Saver.getInstance();

        //Получить файл опций ключу OPTIONS_CONDITIONS. Если файла нет, то он будет создан
        condOptions = getSharedPreferences(OPTIONS_CONDITIONS, Context.MODE_PRIVATE);

        if(condOptions.contains(CURRENT_CITY)){
            cityName = condOptions.getString(CURRENT_CITY, null);
        }


        //Кпопка выбора города вызывает activity со списком городов
        selectCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CitiesActivity.class);
                startActivityForResult(intent, REQUEST_ACCES_TYPE);

            }
        });

        //Показать погоду
        showWeatherInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, cityName);

//        //Сохранить состояния CheckBox в Saver
//        Saver.getInstance().setWindIsChecked(((CheckBox)findViewById(R.id.showWindChB)).isChecked());
//        Saver.getInstance().setPressureIsChecked(((CheckBox)findViewById(R.id.showPressureChB)).isChecked());

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

        //Получить значения установленные в опциях
        if(condOptions.contains(SHOWWIND_OPTION) && condOptions.contains(SHOWPRESSURE_OPTION)){

            showWind = condOptions.getBoolean(SHOWWIND_OPTION, false);
            showPressure = condOptions.getBoolean(SHOWPRESSURE_OPTION, false);
        }

//        //Устанавливаем состояния CheckBox полученные из Saver
//        showWindCheckBox.setChecked(Saver.getInstance().isWindIsChecked());
//        showPressureCheckBox.setChecked(Saver.getInstance().isPressureIsChecked());


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

            case R.id.action_options:
                Intent intent1 = new Intent();
                intent1.setClass(this, OptionsActivity.class);
                startActivity(intent1);
                return true;

            default:
                return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = condOptions.edit();
        if(tViewSelectedCity != null){
            editor.putString(CURRENT_CITY, tViewSelectedCity.getText().toString());
            editor.apply();
        }
    }


    public boolean[] getConditionsFromCheckBoxes() {
        boolean[] c = {showWind, showPressure};
        return c;
    }

    public void showWeatherInfo() {

        if(cityName == null) {
            return;
        }

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
