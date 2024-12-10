/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

public class ScoreManager {
    private int score;
    private int highScore;

    public ScoreManager() {
        score = 0;
        highScore = 0;
    }

    public void addScore(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    public int getScore() {
        return score;
    }
    public int getHighScore() {
        return highScore;
    }
    
}
