package com.example.hello;

import android.content.res.Resources;

import java.util.Calendar;

public class BuilderGreetingPhrase {
    private int currentHour;
    private int currentMinute;
    //private Resources resources;
    private GreetingStrings greetStrings;

    public BuilderGreetingPhrase(GreetingStrings gStrs) {
        this.currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        this.currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        this.greetStrings = gStrs;
    }

    public String getGreetPhrase() {

        return String.format("%s %s%s %s%s", greetStrings.getNow(), currentHour, greetStrings.getHours(), currentMinute, greetStrings.getMinute());

    }

    /*public BuilderGreetingPhrase(Resources resources) {
        this.currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        this.resources = resources;
    }

    public String getGreetPhrase() {

        return String.format("%s %s %s!!!", resources.getString(R.string.now), currentHour, resources.getString(R.string.hours));

    }*/
}
