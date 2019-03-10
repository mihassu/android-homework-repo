package com.android.lesson05androidweather;

public interface WeatherInfo {
    String getWeatherInfoByCity(String cityName, int index, boolean[] weatherConditions);
}
