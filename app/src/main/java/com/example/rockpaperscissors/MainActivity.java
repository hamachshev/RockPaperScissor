package com.example.rockpaperscissors;

import static com.example.rockpaperscissors.Utils.showInfoDialog;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.rockpaperscissors.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

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

        binding.contentMain.Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpuChoose(1); // rock is 1
            }
        });

    }

    private void cpuChoose(int userChoice) {
        Random random = new Random();
        int number =random.nextInt(3);
        switch (number) {
            case 0:
                binding.contentMain.image.setImageResource(R.drawable.paper);
                break;
            case 1:
                binding.contentMain.image.setImageResource(R.drawable.rock);
                break;
            case 2:
                binding.contentMain.image.setImageResource(R.drawable.scissors);
                break;
        }

        checkWinner(number, userChoice);
    }

    private void checkWinner(int cpuChoice, int userChoice) {
        switch (cpuChoice){
            case 0:
                if (userChoice == 2)
                    binding.contentMain.winnerText.setText("You win 🎉");
                else if (userChoice == 0)
                    binding.contentMain.winnerText.setText("Its a tie 🤝");
                else
                    binding.contentMain.winnerText.setText("You lose 😢");
                break;

            case 1:
                if (userChoice == 2)
                    binding.contentMain.winnerText.setText("You lose 😢");
                else if (userChoice == 1)
                    binding.contentMain.winnerText.setText("Its a tie 🤝");
                else
                    binding.contentMain.winnerText.setText("You win 🎉");
                break;

            case 2:
                if (userChoice == 1)
                    binding.contentMain.winnerText.setText("You win 🎉");
                else if (userChoice == 2)
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

}