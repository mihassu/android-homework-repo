package com.android.lesson05androidweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;


public class CitiesFragment extends ListFragment {

    private int currentPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.cities_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Показать список городов:
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Cities, android.R.layout.simple_list_item_activated_1);

        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); //видимо тип выбора элемента из списка (один клик ил два))



    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        currentPosition = position;
        showWeatherInfo();

    }

    public void showWeatherInfo() {
        //Выделить текущий элемнт списка (Заработало после добавления CHOICE_MODE_SINGLE)
        getListView().setItemChecked(currentPosition, true);

        //WeatherInfoFragment details = (WeatherInfoFragment)getFragmentManager().findFragmentById(R.id.weather_container);


        CheckBox showWind = getActivity().findViewById(R.id.showWindChB);
        CheckBox showPressure = getActivity().findViewById(R.id.showPressureChB);

        Intent intent = new Intent();
        intent.setClass(getActivity(), WeatherInfoActivity.class);
        intent.putExtra("index", currentPosition);
        intent.putExtra("showWind", showWind.isChecked());
        intent.putExtra("showPressure", showPressure.isChecked());
        startActivity(intent);
    }


}
