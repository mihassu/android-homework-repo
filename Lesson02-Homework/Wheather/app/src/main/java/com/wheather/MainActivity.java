package com.wheather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.*;


public class MainActivity extends AppCompatActivity implements WheatherStrings {

    private static final Logger logger = Logger.getLogger(MainActivity.class.getName());
    private static Handler handler = new ConsoleHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView wheatherInf = findViewById(R.id.wheatherInfo);
        BuilderWheatherInfo builderWhInfo = new BuilderWheatherInfo(this);
        wheatherInf.setText(builderWhInfo.getWheatherInfo());

        String instanceState;
        if(savedInstanceState == null) {
            instanceState = "Первый запуск";
        } else {
            instanceState = "Повторный запуск";
        }
        Toast t = Toast.makeText(getApplicationContext(), "onCreate" + " " + instanceState, Toast.LENGTH_SHORT);
        t.show();


        try {
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
            handler.setLevel(Level.ALL);
            //handler.setFormatter(new XMLFormatter());

            logger.log(Level.ALL, "Запуск onCreate");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "Запуск onStart");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "Повторный запуск - onRestoreInstanceState()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "Запуск onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "Запуск onPause()");

    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "onSaveInstanceState()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        logger.log(Level.ALL, "onDestroy()");

    }







    @Override
    public String getCurrentCity() {
        return getResources().getString(R.string.currentCity);
    }

    @Override
    public String getTemperatureStr() {
        return getResources().getString(R.string.temperatureStr);

    }

    @Override
    public String getTemperatureValue() {
        return getResources().getString(R.string.temperayureValue);

    }

    @Override
    public String getWindStr() {
        return getResources().getString(R.string.windStr);

    }

    @Override
    public String getWindValue() {
        return getResources().getString(R.string.windValue);

    }
}
