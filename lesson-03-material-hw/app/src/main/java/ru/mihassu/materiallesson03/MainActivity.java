package ru.mihassu.materiallesson03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //кнопка Назад. Ее id: android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            //кнопка Назад
            case android.R.id.home:
                Log.d(TAG, "Back pressed");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
