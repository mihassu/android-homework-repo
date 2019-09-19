package ru.mihassu.lesson02material.snackbar;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import ru.mihassu.lesson02material.R;

public class MySnackbar {

    public static Snackbar createSnackbar(View view, String message, Activity activity) {

        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.GRAY);

        snackbar.setAction(activity.getResources().getString(R.string.close), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        return snackbar;
    }
}
