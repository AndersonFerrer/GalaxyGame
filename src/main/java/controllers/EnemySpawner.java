/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author andersonferrer
 */


import entities.Enemy;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private final List<Enemy> enemies;
    private final Random random;

    public EnemySpawner() {
        enemies = new ArrayList<>();
        random = new Random();
    }

    public void spawnEnemy() {
        int x = random.nextInt(Constants.SCREEN_WIDTH - 40);
        enemies.add(new Enemy(x, 0, 40, 40, 2));
    }

    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.moveDown();
        }
        enemies.removeIf(Enemy::isOutOfBounds);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}

