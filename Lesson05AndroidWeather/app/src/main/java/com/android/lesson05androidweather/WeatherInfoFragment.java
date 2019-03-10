package com.android.lesson05androidweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import static com.android.lesson05androidweather.CitiesFragment.CURRENT_CITY_POS;
import static com.android.lesson05androidweather.CitiesFragment.CITY_NAME;
import static com.android.lesson05androidweather.CitiesFragment.CONDITIONS;



public class WeatherInfoFragment extends Fragment implements WeatherStrings{

    //Фабричный метод - метод который создает объект класса
    public static WeatherInfoFragment create(String cityName, int index, boolean[] conditions){
        WeatherInfoFragment f = new WeatherInfoFragment();

        //Передача полученных значений во фрагмент
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        args.putInt(CURRENT_CITY_POS, index);
        args.putBooleanArray(CONDITIONS, conditions);
        f.setArguments(args);
        return f;
    }

    public int getIndex(){
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

        View v = inflater.inflate(R.layout.weather_info_fragment, container,false);
//        TextView tView = new TextView(getActivity()); //если фрагмент без макета
        TextView tView = v.findViewById(R.id.weather_info);

        //Показать информацию
        WeatherInfo weatherInCity = new WeatherInformerSimple(this);
        tView.setText(weatherInCity.getWeatherInfoByCity(getCityName(), getIndex(), getConditions()));

        return v;


    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        float fontSize = Float.parseFloat(prefs.getString(getString(R.string.pref_size), "20"));

        try {
            TextView tv = getActivity().findViewById(R.id.weather_info);
            tv.setTextSize(fontSize);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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
