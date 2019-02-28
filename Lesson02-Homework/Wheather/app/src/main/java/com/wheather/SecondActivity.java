package com.wheather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements WeatherStrings {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView showWeatherInfo = findViewById(R.id.weatherInfoSecond);
        if(getIntent() == null) { //Делать проверку на null!!!!!
            showWeatherInfo.setText("null");
        } else {
            String curCity = getIntent().getExtras().getString("curCity");
            boolean[] conditions = {getIntent().getExtras().getBoolean("showWind"),
                    getIntent().getExtras().getBoolean("showPressure")};

            WeatherInfo weatherInCity = new WeatherInformerSimple(this);

            showWeatherInfo.setText(weatherInCity.getWeatherInfoByCity(curCity, conditions));

        }



    }

    @Override
    public String getTemperature() {
        return getResources().getString(R.string.temperatureStr);
    }

    @Override
    public String getWind() {
        return getResources().getString(R.string.windStr);
    }

    @Override
    public String getPressure() {
        return getResources().getString(R.string.pressureStr);
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
