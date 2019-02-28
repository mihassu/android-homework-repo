package com.wheather;

import java.util.ArrayList;

public class WeatherInformerSimple implements WeatherInfo {

    private ArrayList<City> cities;
    private WeatherStrings wStrings;


    private class City{

        private String cityName;
        private String tempValue;
        private String windValue;
        private String pressureValue;

        public City(String cityName, String tempValue, String windValue, String pressureValue) {
            this.cityName = cityName;
            this.tempValue = tempValue;
            this.windValue = windValue;
            this.pressureValue = pressureValue;
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


    public WeatherInformerSimple(WeatherStrings wStr) {
        this.wStrings = wStr;

        cities = new ArrayList<>();
        cities.add(new City("Omsk", "5град", "3 м/с", "756 мм.рт.ст."));
        cities.add(new City("Tver", "8град", "5 м/с", "762 мм.рт.ст."));
    }

    @Override
    public String getWeatherInfoByCity(String city, boolean[] weatherConditions){
        StringBuilder sb = new StringBuilder(city + "\n");
        for(City c: cities) {
            if(c.getCityName().equals(city)) {
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
}
