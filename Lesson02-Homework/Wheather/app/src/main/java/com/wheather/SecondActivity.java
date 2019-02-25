package com.wheather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView showWeatherInfo = findViewById(R.id.weatherInfoSecond);
        String curCity = getIntent().getExtras().getString("curCity");
        boolean[] conditions = {getIntent().getExtras().getBoolean("showWind"),
                                 getIntent().getExtras().getBoolean("showPressure")};

        WeatherInfo weatherInCity = new WeatherInformerSimple();

        showWeatherInfo.setText(weatherInCity.getWeatherInfoByCity(curCity, conditions));

    }

    public void backButton(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                finish();
                break;

                default:
                    break;
        }
    }


}
