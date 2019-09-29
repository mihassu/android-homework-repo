package com.android.mymaterial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.android.mymaterial.SecondActivity.NAME;

public class ThirdActivity extends AppCompatActivity {

    private final String TAG = "ThirdActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //кнопка Назад. Ее id: android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Получить значение из SecondActivity
        getIntent().getExtras().getString(NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            //кнопка Назад
            case android.R.id.home:
//                Log.d(TAG, "Back pressed");
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
