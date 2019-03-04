package com.android.lesson05androidweather;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment implements WeatherStrings{

    //Фабричный метод - метод который создает объект класса
    public static WeatherInfoFragment create(){
        WeatherInfoFragment f = new WeatherInfoFragment();

        /*Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);*/
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        TextView tView = new TextView(getActivity());
        int index = getArguments().getInt("index", 0);
        boolean[] conditions = {getArguments().getBoolean("showWind", false),
                getArguments().getBoolean("showPressure", false)};

        //tView.setText(Integer.toString(index));

        WeatherInfo weatherInCity = new WeatherInformerSimple(this);
        tView.setText(weatherInCity.getWeatherInfoByCity(index, conditions));


        return tView;

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
