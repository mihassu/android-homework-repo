package ru.mihassu.lesson6material;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutLinear;
    private LinearLayoutManager layoutGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFab();
        initRecyclerView();
    }

    private void initFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_settings:
                return true;

            case R.id.set_linear:
                recyclerView.setLayoutManager(layoutLinear);
                return true;

            case R.id.set_grid:
                recyclerView.setLayoutManager(layoutGrid);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.mRecyclerView);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        layoutLinear = new LinearLayoutManager(this);
        layoutLinear.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutLinear);

        layoutGrid = new GridLayoutManager(this,2);

        //создать адаптер
        ItemCardAdapter adapter = new ItemCardAdapter(createDataList());
        recyclerView.setAdapter(adapter);

        //Разделитель
        recyclerView.addItemDecoration(new ItemDivider(this));
    }

    private List<ItemCardModel> createDataList() {

        List<ItemCardModel> data = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.fruits_titles);
        String[] descriptions = getResources().getStringArray(R.array.fruits_descriptions);
        @SuppressLint("Recycle") TypedArray thumbnails = getResources().obtainTypedArray(R.array.fruits_imgs);

        for (int i = 0; i < titles.length; i++) {
            data.add(new ItemCardModel(titles[i], descriptions[i], thumbnails.getResourceId(i, -1)));
        }
        return data;
    }
}

