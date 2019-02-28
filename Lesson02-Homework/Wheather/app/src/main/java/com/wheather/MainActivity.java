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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView weatherInf = findViewById(R.id.weatherInfo);
        weatherInf.setText(R.string.enterCity);

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

    public void meowOnClick(View v){
        switch (v.getId()) {
            case R.id.meowButton:
                TextView tv = findViewById(R.id.meowTV);
                tv.setText(R.string.meow);

                break;

            default:
                break;
        }
    }
}
