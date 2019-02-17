package com.example.hello;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GreetingStrings{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //привязываем макет к activity

        TextView greeting = (TextView) findViewById(R.id.greetingId);
        //greeting.setText(greetPhrase());

        /*BuilderGreetingPhrase greetPhrase = new BuilderGreetingPhrase(getResources());
        greeting.setText(greetPhrase.getGreetPhrase());*/

        BuilderGreetingPhrase greetPhrase = new BuilderGreetingPhrase(this);
        greeting.setText(greetPhrase.getGreetPhrase());
    }

    @Override
    public String getNow() {
        return getResources().getString(R.string.now);
    }

    @Override
    public String getHours() {
        return getResources().getString(R.string.hours);
    }

    @Override
    public String getMinute() {
        return getResources().getString(R.string.minute);
    }


    /*private String greetPhrase() {

        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Resources res = getResources();
        //String nowText = res.getString(R.string.now);
        //return nowText + " " + currentHour;
        return String.format("%s %s %s!!!", res.getString(R.string.now), currentHour, res.getString(R.string.hours));

    }*/


}
