package com.android.lesson05androidweather;

import android.app.FragmentTransaction;
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
import android.widget.TextView;



public class CitiesFragment extends ListFragment {

    public static final String CURRENT_CITY_POS = "CurrentCity";
    public static final String CITY_NAME = "cityName";
    public static final String CONDITIONS = "Conditions";
    private int currentPosition = 0;
    private boolean isExistPlace;
    private String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.cities_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Показать список городов
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Cities, android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);

//        //Определить есть ли место
//        View detailsFrame = getActivity().findViewById(R.id.weather_container_land);
//        isExistPlace = (detailsFrame != null) && (detailsFrame.getVisibility() == View.VISIBLE);
//
//        //Если это не повторное создание
//        if(savedInstanceState != null) {
//            currentPosition = savedInstanceState.getInt(CURRENT_CITY_POS, 0);
//        }
//
//        //Вызов метода для вывода погоды
//        if(isExistPlace) {
////            //видимо тип выбора элемента из списка (один клик или два))
////            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//            showWeatherInfo();
//        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        TextView city = (TextView) v;
        cityName = city.getText().toString(); //достать текст с кнопки
        currentPosition = position;

        Intent intent = new Intent();
        intent.setClass(getActivity(), MainActivity.class);
        intent.putExtra(CITY_NAME, cityName);
        intent.putExtra(CURRENT_CITY_POS, currentPosition);
        startActivity(intent);
        getActivity().finish();

//        TextView tViewSelectedCity = getActivity().findViewById(R.id.tViewSelectedCity);
//        tViewSelectedCity.setText(cityName);

        //видимо тип выбора элемента из списка (один клик или два))
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Выделить текущий элемнт списка (Заработало после добавления CHOICE_MODE_SINGLE)
        getListView().setItemChecked(currentPosition, true);

    }


//    public void showWeatherInfo() {
////        //Выделить текущий элемнт списка (Заработало после добавления CHOICE_MODE_SINGLE)
////        getListView().setItemChecked(currentPosition, true);
//
//        if (isExistPlace) {
//            //Проверка что фрагмент есть в activity
//            WeatherInfoFragment details = (WeatherInfoFragment) getFragmentManager().findFragmentById(R.id.weather_container_land);
//
//            //Вывести информацию
//            //if(details == null || details.  )
//            details = WeatherInfoFragment.create(cityName, currentPosition, getConditionsFromCheckBoxes());
//
//            //Добавление фрагмента с информацией
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.replace(R.id.weather_container_land, details);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
//        }
//        else {
//
//            Intent intent = new Intent();
//            intent.setClass(getActivity(), WeatherInfoActivity.class);
//            intent.putExtra(CITY_NAME, cityName);
//            intent.putExtra(CURRENT_CITY_POS, currentPosition);
//            intent.putExtra(CONDITIONS, getConditionsFromCheckBoxes());
//            startActivity(intent);
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_CITY_POS, currentPosition); //ключи надо все объявить через переменные
    }

//    public boolean[] getConditionsFromCheckBoxes() {
//
//        CheckBox showWind = getActivity().findViewById(R.id.showWindChB);
//        CheckBox showPressure = getActivity().findViewById(R.id.showPressureChB);
//        boolean[] c = {showWind.isChecked(), showPressure.isChecked()};
//
//        return c;
//    }

}
