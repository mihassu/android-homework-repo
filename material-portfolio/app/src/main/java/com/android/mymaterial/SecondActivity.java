package com.android.mymaterial;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {

    private EditText nameField;
    private String name;
    private SharedPreferences savedName;
    //    private Dialog dialog;

    public final static String NAME = "name";
    private final String NAME_VALUE = "nameValue";
    private final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button enterButton = findViewById(R.id.button_enter);
        final LinearLayout linearLayout = findViewById(R.id.linear_layout);
        nameField = findViewById(R.id.name_edit);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameField.getText().toString().equals("")) {
                    Snackbar snackbar = Snackbar.make(linearLayout, "Пустое поле", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    name = nameField.getText().toString();
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    intent.putExtra(NAME,name);
                    startActivity(intent);
                    finish();
                }
            }
        });

//        LayoutInflater layoutInflater = getLayoutInflater();
//        View layuot = layoutInflater.inflate(R.layout.dialog_layout, findViewById(R.layout.activity_second), false);

        //Кнопка Закрыть - показывает диалог
        Button closeButton = findViewById(R.id.button_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.show();
                openQuitDialog(SecondActivity.this);
            }
        });

//        //Диалог с макетом
//        dialog = new Dialog(SecondActivity.this);
//        dialog.setContentView(R.layout.dialog_layout);
//        //Кнопка диалога Да - закрывает активити
//        Button buttonYes = findViewById(R.id.button_yes);
//        buttonYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                name = nameField.getText().toString();
//                finish();
//            }
//        });//
//        //Кнопка диалога Нет - закрывает диалог
//        Button buttonNo = findViewById(R.id.button_no);
//        buttonNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        //Получить файл опций по ключу NAME. Если файла нет, то он будет создан
        savedName = getSharedPreferences(NAME, MODE_PRIVATE);

//        new MaterialAlertDialogBuilder(context)
//                .setTitle("Title")
//                .setMessage("Message")
//                .setPositiveButton("Ok", null)
//                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Сохранить имя
        SharedPreferences.Editor editor = savedName.edit();
        editor.putString(NAME_VALUE, name);
        editor.apply();

        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Вписать в поле nameField сохраненное имя
        if (savedName.contains(NAME_VALUE)) {
            nameField.setText(savedName.getString(NAME_VALUE, "1"));
            nameField.selectAll();
        }
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onBackPressed() {
        openQuitDialog(this);
//        super.onBackPressed();
    }

    //Открыть диалог (подтверждение закрытия)
    public void openQuitDialog(Activity context) {
//        final Activity that = context;
        new AlertDialog.Builder(context).
                setTitle(context.getResources().getString(R.string.close)).
                setMessage(context.getResources().getString(R.string.are_you_sure)).
                setPositiveButton(context.getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SecondActivity.super.onBackPressed();
                            }
                        }).
                setNegativeButton(context.getResources().getString(R.string.no), null).
                create().show();
    }

//    //Кнопки диалога с макетом
//    public void onClickYes() {
//        name = nameField.getText().toString();
//        finish();
//    }
//    public void onClickNo() {
//        dialog.dismiss();
//    }
}
