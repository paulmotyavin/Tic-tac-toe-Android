package com.example.themes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.themes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SharedPreferences stats;

    int winsO;
    int winsX;
    int Draws;

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    ImageButton switchButton, statsButton;
    ActivityMainBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0,0,0,0,0,0,0,0,0}; // 9
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

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

        stats = getSharedPreferences("STATS", MODE_PRIVATE);
        if (stats.contains("WINSX") && stats.contains("WINSO") && stats.contains("DRAWS")){
            winsX = stats.getInt("WINSX", 0);
            winsO = stats.getInt("WINSO", 0);
            Draws = stats.getInt("DRAWS", 0);
        }else{
            updateStats();
        }

        setContentView(binding.getRoot());

        switchButton = findViewById(R.id.switch_button);
        statsButton = findViewById(R.id.stat_button);

        if (settings.getBoolean("MODE_NIGHT_ON", false)){
            switchButton.setImageResource(R.drawable.ic_day);
        }else {
            switchButton.setImageResource(R.drawable.ic_night);
        }

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = settings.edit();
                if (settings.getBoolean("MODE_NIGHT_ON", false)){
                    editor.putBoolean("MODE_NIGHT_ON", false);
                }else {
                    editor.putBoolean("MODE_NIGHT_ON", true);
                }
                editor.apply();

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOneName.setText(getPlayerOneName);
        binding.playerTwoName.setText(getPlayerTwoName);

        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)) {
                    performAction((ImageView) view, 5);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)) {
                    performAction((ImageView) view, 6);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        binding.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8))
                {
                    performAction((ImageView) view, 8);
                    if("Бот".equals(getPlayerOneName) || "Бот".equals(getPlayerTwoName))
                    {
                        if (!checkResults()) {
                            if (totalSelectedBoxes < 9) {
                                makeBotMove();
                            } else {
                                if ("Бот".equals(binding.playerOneName.getText())) makeBotMove();
                            }
                        }
                    }
                }
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatisticsActivity statisticsActivity = new StatisticsActivity(MainActivity.this, "Победа X: " + winsX, "Победа O: " + winsO, "Ничья: " + Draws);
                statisticsActivity.setCancelable(false);
                statisticsActivity.show();
            }
        });

        if (!checkResults() && "Бот".equals(binding.playerOneName.getText()))
            makeBotMove();

    }

    private void getCurrentTheme() {
        if (settings.getBoolean("MODE_NIGHT_ON", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                winsX++;
                ResultActivity resultActivity = new ResultActivity(MainActivity.this, binding.playerOneName.getText().toString()
                + " победитель! ", MainActivity.this);
                resultActivity.setCancelable(false);
                resultActivity.show();
                updateStats();
            } else if (totalSelectedBoxes == 9) {
                Draws++;
                ResultActivity resultActivity = new ResultActivity(MainActivity.this, "Ничья!", MainActivity.this);
                resultActivity.setCancelable(false);
                resultActivity.show();
                updateStats();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                winsO++;
                ResultActivity resultActivity = new ResultActivity(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " победитель! ", MainActivity.this);
                resultActivity.setCancelable(false);
                resultActivity.show();
                updateStats();
            } else if (totalSelectedBoxes == 9) {
                Draws++;
                ResultActivity resultActivity = new ResultActivity(MainActivity.this, "Ничья!", MainActivity.this);
                resultActivity.setCancelable(false);
                resultActivity.show();
                updateStats();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn  = currentPlayerTurn;
        if (playerTurn == 1){
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults(){
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
            boxPositions[combination[2]] == playerTurn){
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch(){
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        int[] imageIds = {R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5, R.id.image6, R.id.image7, R.id.image8, R.id.image9};

        for (int id : imageIds) {
            ImageView imageView = findViewById(id);
            imageView.setImageDrawable(null);
        }

        String name1 = binding.playerTwoName.getText().toString();

        binding.playerTwoName.setText((binding.playerOneName.getText().toString()));
        binding.playerOneName.setText(name1);

        if (!checkResults() && "Бот".equals(binding.playerOneName.getText()))
            makeBotMove();

    }

    public void updateStats(){
        editor = stats.edit();
        editor.putInt("WINSX", winsX);
        editor.putInt("WINSO", winsO);
        editor.putInt("DRAWS", Draws);
        editor.apply();
    }

    private void makeBotMove() {
        Random random = new Random();
        int[] imageIds = {R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5, R.id.image6, R.id.image7, R.id.image8, R.id.image9};
        int botMove;
        do {
            botMove = random.nextInt(9);
        } while (!isBoxSelectable(botMove));

        ImageView botImageView = findViewById(imageIds[botMove]);
        performAction(botImageView, botMove);
    }

}