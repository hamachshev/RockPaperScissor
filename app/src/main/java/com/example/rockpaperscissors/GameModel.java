package com.example.rockpaperscissors;

public class GameModel {
    private Choice playerChoice;
    private Choice cpuChoice;

    private int gamesWon = 0;

    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
    }

    public Choice getCpuChoice() {
        return cpuChoice;
    }

    public void setCpuChoice(Choice cpuChoice) {
        this.cpuChoice = cpuChoice;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
}
