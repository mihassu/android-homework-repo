package com.android.myweather_v1;

public interface WeatherInfo {
    String getWeatherInfoByCity(String cityName, int index, boolean[] weatherConditions);
    String getTemperatureByCity(String cityName, int index);
    String getWindByCity(String cityName, int index);
    String getPressureByCity(String cityName, int index);

}
