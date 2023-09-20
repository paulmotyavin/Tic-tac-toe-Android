package com.example.themes;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends Dialog {
    private final String messageX;
    private final String messageO;
    private final String messageDraw;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public StatisticsActivity(@NonNull Context context, String messageX, String messageO, String messageDraw) {
        super(context);
        this.messageX = messageX;
        this.messageO = messageO;
        this.messageDraw = messageDraw;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = getContext().getSharedPreferences("SETTINGS", MODE_PRIVATE);

        if (settings.contains("MODE_NIGHT_ON")){
            getCurrentTheme();
        }else{
            editor = settings.edit();
            editor.putBoolean("MODE_NIGHT_ON", false);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_statistics);

        TextView MessageX = findViewById(R.id.messageX);
        TextView MessageO = findViewById(R.id.messageO);
        TextView MessageDraw = findViewById(R.id.messageDraw);
        Button closeButton = findViewById(R.id.closeButton);

        MessageX.setText(messageX);
        MessageO.setText(messageO);
        MessageDraw.setText(messageDraw);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
    private void getCurrentTheme() {
        if (settings.getBoolean("MODE_NIGHT_ON", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}