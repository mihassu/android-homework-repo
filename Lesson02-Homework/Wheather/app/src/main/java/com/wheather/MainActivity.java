package com.wheather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    //private int counter = 0;
    private TextView textCountr;
    private String logTag = "mylog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView weatherInf = findViewById(R.id.weatherInfo);
        weatherInf.setText(R.string.enterCity);

        String instanceState;
        if(savedInstanceState == null) {
            instanceState = "Первый запуск";
        } else {
            instanceState = "Повторный запуск";
        }
        Toast t = Toast.makeText(getApplicationContext(), "onCreate" + " " + instanceState, Toast.LENGTH_SHORT);
        t.show();

        //для сохранения instance при пересоздании активити (напр. смена ориентации экрана)
        final MainActivityPresenter presenter = MainActivityPresenter.getInstance();

        textCountr = findViewById(R.id.textCounter); //поле

//        //сохранение состояния через saveInstanceState
//        textCountr.setText(((Integer)counter).toString()); //этот каст обязательно, иначе RuntimeExc

        textCountr.setText(((Integer)presenter.getCounter()).toString());

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                counter++;
//                textCountr.setText(((Integer)counter).toString()); //этот каст обязательно, иначе RuntimeExc

                //Сохранение instance через дополнительный класс:
                presenter.incrementCounter();
                textCountr.setText(((Integer)presenter.getCounter()).toString());
            }
        });

        Log.i(logTag, "Запуск onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onStart");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "onSaveInstanceState()");

        //saveInstanceState.putInt("Counter", counter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Повторный запуск - onRestoreInstanceState()");
//        counter = savedInstanceState.getInt("Counter", counter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onPause()");


    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onStop()");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onRestart()");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.i(logTag, "Запуск onDestroy()");
    }


    public void showWeatherButtonOnClick(View v) {
        switch (v.getId()) {
            case R.id.showWeatherButton:
                EditText curCity = findViewById(R.id.enterCity);
                CheckBox showWind = findViewById(R.id.showWindChB);
                CheckBox showPressure = findViewById(R.id.showPressureChB);

                Intent intent = new Intent(this, SecondActivity.class);

                intent.putExtra("curCity", curCity.getText().toString());
                intent.putExtra("showWind", showWind.isChecked());
                intent.putExtra("showPressure", showPressure.isChecked());

                startActivity(intent);
                break;

            default:
                break;
        }


    }
}
