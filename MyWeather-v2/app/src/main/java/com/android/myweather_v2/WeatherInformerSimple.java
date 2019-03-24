package com.android.myweather_v2;

import android.app.Activity;

import java.util.ArrayList;

public class WeatherInformerSimple implements WeatherInfo {

    private ArrayList<City> cities;
    private WeatherStrings wStrings;
    private Activity activity;


    private class City{

        private int cityIndex;
        private String cityName;
        private String tempValue;
        private String windValue;
        private String pressureValue;

        public City(int cityIndex, String cityName, String tempValue, String windValue, String pressureValue) {
            this.cityIndex = cityIndex;
            this.cityName = cityName;
            this.tempValue = tempValue;
            this.windValue = windValue;
            this.pressureValue = pressureValue;
        }
        public int getCityIndex() {
            return cityIndex;
        }

        public String getCityName() {
            return cityName;
        }

        public String getTempValue() {
            return tempValue;
        }

        public String getWindValue() {
            return windValue;
        }

        public String getPressureValue() {
            return pressureValue;
        }
    }


    public WeatherInformerSimple(WeatherStrings wStr, Activity activity) {
        this.wStrings = wStr;
        this.activity = activity;

        cities = new ArrayList<>();
        cities.add(new City(0,"Москва", "5ºC", "1 м/с", "777 мм.рт.ст."));
        cities.add(new City(1,"Санкт-Петербург", "10ºC", "2 м/с", "766 мм.рт.ст."));
        cities.add(new City(2,"Новосибирск", "15ºC", "3 м/с", "744 мм.рт.ст."));
        cities.add(new City(3,"Самара", "20ºC", "4 м/с", "733 мм.рт.ст."));

    }

    @Override
    public String getWeatherInfoByCity(String cityName, int index, boolean[] weatherConditions){
        StringBuilder sb = new StringBuilder();
        for(City c: cities) {
            if(c.getCityName().equals(cityName)) {
//                sb.append(c.getCityName());
//                sb.append(":\n");
                sb.append(wStrings.getTemperature());
                sb.append(": ");
                sb.append(c.getTempValue());
                sb.append("\n");

                if(weatherConditions[0]){
                    sb.append(wStrings.getWind());
                    sb.append(": ");
                    sb.append(c.getWindValue());
                    sb.append("\n");
                }
                if(weatherConditions[1]){
                    sb.append(wStrings.getPressure());
                    sb.append(": ");
                    sb.append(c.getPressureValue());
                }
                break;
            }
        }
        return sb.toString();
    }


    @Override
    public String getTemperatureByCity(String cityName, int index) {

        String temperature = activity.getResources().getString(R.string.no_data);
        for (City c: cities) {
            if(c.getCityName().equals(cityName)) {
                temperature = c.getTempValue();
            }
        }
        return temperature;
    }

    @Override
    public String getWindByCity(String cityName, int index) {
        String wind = activity.getResources().getString(R.string.no_data);
        for (City c: cities) {
            if(c.getCityName().equals(cityName)) {
                wind = c.getWindValue();
            }
        }
        return wind;
    }

    @Override
    public String getPressureByCity(String cityName, int index) {
        String pressure = activity.getResources().getString(R.string.no_data);
        for (City c: cities) {
            if(c.getCityName().equals(cityName)) {
                pressure = c.getPressureValue();
            }
        }
        return pressure;
    }
}
