package com.android.myweather_v1;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import static com.android.myweather_v1.MainActivity.CITY_NAME;
import static com.android.myweather_v1.MainActivity.CONDITIONS;
import static com.android.myweather_v1.MainActivity.CURRENT_CITY_POS;


public class WeatherInfoFragment extends Fragment implements WeatherStrings {

    //Фабричный метод - метод который создает объект класса
    public static WeatherInfoFragment create(String cityName, int index, boolean[] conditions) {
        WeatherInfoFragment f = new WeatherInfoFragment();

        //Передача полученных значений во фрагмент
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        args.putInt(CURRENT_CITY_POS, index);
        args.putBooleanArray(CONDITIONS, conditions);
        f.setArguments(args);
        return f;
    }

    public int getIndex() {
        return getArguments().getInt(CURRENT_CITY_POS, 0);
    }

    public String getCityName() {
        return getArguments().getString(CITY_NAME);
    }

    public boolean[] getConditions() {
        return getArguments().getBooleanArray(CONDITIONS);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.weather_info_fragment, container, false);
//        TextView tView = new TextView(getActivity()); //если фрагмент без макета
        TextView tView = v.findViewById(R.id.weather_info);

        //Показать информацию
        WeatherInfo weatherInCity = new WeatherInformerSimple(this, getActivity());
//        tView.setText(weatherInCity.getWeatherInfoByCity(getCityName(), getIndex(), getConditions()));

        //Получить котейнер GridLayout куда будет выводится погода
        GridLayout weather = v.findViewById(R.id.weather_fragment);


        //Показать температуру
        tView.setText(weatherInCity.getTemperatureByCity(getCityName(), getIndex()));

//        weather.addView(createWeatherCard(Gravity.CENTER, weatherInCity.getTemperatureByCity(getCityName(), getIndex()), 26));



        if (getConditions()[0]) {
            weather.addView(createWeatherCard(Gravity.START, getResources().getText(R.string.windStr).toString(), 16));
            weather.addView(createWeatherCard(Gravity.END, weatherInCity.getWindByCity(getCityName(), getIndex()), 16));
        }

        if (getConditions()[1]) {
            weather.addView(createWeatherCard(Gravity.START, getResources().getText(R.string.pressureStr).toString(), 16));
            weather.addView(createWeatherCard(Gravity.END, weatherInCity.getPressureByCity(getCityName(), getIndex()), 16));
        }

        Log.e("City Name", getCityName());

        return v;


    }

    private View createWeatherCard(int gravity, String weatherValue, float textSize) {
        TextView tempTextView = new TextView(getActivity());
        GridLayout.LayoutParams layoutparams = new GridLayout.LayoutParams();
        layoutparams.setMargins(0, 10, 0, 10);
        layoutparams.setGravity(gravity);
        tempTextView.setText(weatherValue);
        tempTextView.setTextSize(textSize);
        tempTextView.setLayoutParams(layoutparams);

        return tempTextView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
}
