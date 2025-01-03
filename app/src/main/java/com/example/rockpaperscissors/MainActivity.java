package com.example.rockpaperscissors;

import static com.example.rockpaperscissors.Utils.showInfoDialog;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.rockpaperscissors.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private GameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });


        model = new GameModel();

        binding.contentMain.Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setPlayerChoice(Choice.ROCK);
                cpuChoose(); // rock is 1
            }
        });

        binding.contentMain.Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setPlayerChoice(Choice.PAPER);
                cpuChoose(); // rock is 1
            }
        });

        binding.contentMain.Scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setPlayerChoice(Choice.SCISSORS);
                cpuChoose(); // rock is 1
            }
        });


    }

    private void cpuChoose() {
        Random random = new Random();
        int number = random.nextInt(3);
        switch (number) {
            case 0:
                binding.contentMain.image.setImageResource(R.drawable.paper);
                model.setCpuChoice(Choice.PAPER);
                break;
            case 1:
                binding.contentMain.image.setImageResource(R.drawable.rock);
                model.setCpuChoice(Choice.ROCK);
                break;
            case 2:
                binding.contentMain.image.setImageResource(R.drawable.scissors);
                model.setCpuChoice(Choice.SCISSORS);
                break;
        }


        checkWinner(false);
    }

    private void checkWinner(boolean fromRestore) {
        switch (model.getCpuChoice()){
            case ROCK:
                if (model.getPlayerChoice() == Choice.PAPER) {
                    binding.contentMain.winnerText.setText("You win 🎉");
                    if(!fromRestore) model.setGamesWon(model.getGamesWon() + 1);
                    binding.contentMain.gamesWon.setText("Games won: " + model.getGamesWon());
                }
                else if (model.getPlayerChoice() == Choice.ROCK)
                    binding.contentMain.winnerText.setText("Its a tie 🤝");
                else
                    binding.contentMain.winnerText.setText("You lose 😢");
                break;

            case PAPER:
                if (model.getPlayerChoice() == Choice.ROCK)
                    binding.contentMain.winnerText.setText("You lose 😢");
                else if (model.getPlayerChoice() == Choice.PAPER)
                    binding.contentMain.winnerText.setText("Its a tie 🤝");
                else {
                    binding.contentMain.winnerText.setText("You win 🎉");
                    if(!fromRestore) model.setGamesWon(model.getGamesWon() + 1);
                    binding.contentMain.gamesWon.setText("Games won: " + model.getGamesWon());
                }
                break;

            case SCISSORS:
                if (model.getPlayerChoice() == Choice.ROCK) {
                    binding.contentMain.winnerText.setText("You win 🎉");
                    if(!fromRestore) model.setGamesWon(model.getGamesWon() + 1);
                    binding.contentMain.gamesWon.setText("Games won: " + model.getGamesWon());
                }
                else if (model.getPlayerChoice() == Choice.SCISSORS)
                    binding.contentMain.winnerText.setText("Its a tie 🤝");
                else
                    binding.contentMain.winnerText.setText("You lose 😢");
                break;
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_about){
            showInfoDialog(MainActivity.this, "About Rock Paper Scissors",
                    "Rock Paper Scissors!!\n" +
                            "\nBy Aharon Seidman\nSholom Fiorini\nAnd Moshe Slepoy");

        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        outState.putString("GAME", gson.toJson(model));

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Gson gson = new Gson();
        model = gson.fromJson(savedInstanceState.getString("GAME"), GameModel.class);
        updateUI();

    }

    private void updateUI() {
        switch (model.getCpuChoice()){
            case ROCK:
                binding.contentMain.image.setImageResource(R.drawable.rock);
                break;
            case PAPER:
                binding.contentMain.image.setImageResource(R.drawable.paper);
                break;
            case SCISSORS:
                binding.contentMain.image.setImageResource(R.drawable.scissors);
                break;
        }

       checkWinner(true);

    }

}