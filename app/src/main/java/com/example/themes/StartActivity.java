package com.example.themes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);

        if (settings.contains("MODE_NIGHT_ON")){
            getCurrentTheme();
        }else{
            editor = settings.edit();
            editor.putBoolean("MODE_NIGHT_ON", false);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show();
        }

        setContentView(R.layout.activity_start);

        EditText playerOne = findViewById(R.id.player);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button startGameButton = findViewById(R.id.startGameButtonTwo);
        Button startWithBot = findViewById(R.id.startGameButtonBot);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Пожалуйста введите имена игроков", Toast.LENGTH_SHORT).show();
                }
                else if (getPlayerOneName == "Бот" || getPlayerTwoName == "Бот"){
                    Toast.makeText(StartActivity.this, "Данное имя зарезервировано системой", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent (StartActivity.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });

        startWithBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if (getPlayerOneName.isEmpty() && getPlayerTwoName.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Пожалуйста введите свое имя", Toast.LENGTH_SHORT).show();
                }
                else if (!getPlayerOneName.isEmpty() && !getPlayerTwoName.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Пожалуйста введите только одно имя ", Toast.LENGTH_SHORT).show();
                }
                else if (getPlayerOneName == "Бот" || getPlayerTwoName == "Бот"){
                    Toast.makeText(StartActivity.this, "Данное имя зарезервировано системой", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent (StartActivity.this, MainActivity.class);
                    if (getPlayerOneName.isEmpty()){
                        intent.putExtra("playerOne", "Бот");
                        intent.putExtra("playerTwo", getPlayerTwoName);
                    }
                    else{
                        intent.putExtra("playerOne", getPlayerOneName);
                        intent.putExtra("playerTwo", "Бот");
                    }
                    startActivity(intent);
                }
            }
        });

    }

    private void getCurrentTheme(){
        if (settings.getBoolean("MODE_NIGHT_ON", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}