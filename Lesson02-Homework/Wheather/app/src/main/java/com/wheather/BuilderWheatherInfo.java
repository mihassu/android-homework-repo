package com.wheather;

public class BuilderWheatherInfo {
    private WheatherStrings wheatherStrings;

    public BuilderWheatherInfo(WheatherStrings wheatherStrings) {
        this.wheatherStrings = wheatherStrings;
    }

    public String getWheatherInfo(){
        return String.format("%s\n%s: %s\n%s: %s", wheatherStrings.getCurrentCity(),
                wheatherStrings.getTemperatureStr(), wheatherStrings.getTemperatureValue(),
                wheatherStrings.getWindStr(), wheatherStrings.getWindValue());
    }
}
