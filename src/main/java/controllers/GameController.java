/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author andersonferrer
 */

import entities.*;

import graphics.Renderer;
import utils.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class GameController {
    private final Ship ship;
    private final List<Projectile> projectiles;
    private final ScoreManager scoreManager;
    private final Renderer renderer;
    private final EnemySpawner spawner;
    private boolean isGameOver;

    public GameController() {
        ship = new Ship(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 60, 50, 30, Constants.SHIP_SPEED);
        projectiles = new ArrayList<>();
        scoreManager = new ScoreManager();
        spawner = new EnemySpawner();
        renderer = new Renderer(this);
        renderer.addKeyListener(new InputHandler(this));
        configureGameLoop();
        isGameOver = false;
    }

    public void startGame() {
        renderer.start();
    }

    private void configureGameLoop() {
        Timer timer = new Timer(16, e -> {
            if (!isGameOver) {
                update();
                renderer.repaint();
            }
        });
        timer.start();

        Timer enemyTimer = new Timer(1000, e -> {
            if (!isGameOver) {
                spawner.spawnEnemy();
            }
        });
        enemyTimer.start();
    }

    private void update() {
        spawner.updateEnemies();

        for (Projectile projectile : projectiles) {
            projectile.moveUp();
        }
        projectiles.removeIf(Projectile::isOutOfBounds);

        handleCollisions();
    }

    private void handleCollisions() {
        List<Enemy> toRemoveEnemies = new ArrayList<>();
        List<Projectile> toRemoveProjectiles = new ArrayList<>();

        for (Enemy enemy : spawner.getEnemies()) {
            // Colisión con la nave
            if (ship.getX() < enemy.getX() + 40 &&
                ship.getX() + ship.getWidth() > enemy.getX() &&
                ship.getY() < enemy.getY() + 40 &&
                ship.getY() + ship.getHeight() > enemy.getY()) {
                endGame();
                return;
            }
            // Colisión con el suelo
            if (enemy.getY() >= Constants.SCREEN_HEIGHT) {
                endGame();
                return;
            }

            // Colisión con proyectiles
            for (Projectile projectile : projectiles) {
                if (projectile.getX() >= enemy.getX() &&
                    projectile.getX() <= enemy.getX() + 40 &&
                    projectile.getY() <= enemy.getY() + 40 &&
                    projectile.getY() >= enemy.getY()) {
                    toRemoveEnemies.add(enemy);
                    toRemoveProjectiles.add(projectile);
                    scoreManager.addScore(10);
                }
            }
        }

        spawner.getEnemies().removeAll(toRemoveEnemies);
        projectiles.removeAll(toRemoveProjectiles);
    }

    private void endGame() {
        isGameOver = true;
        scoreManager.updateHighScore();
        JOptionPane.showMessageDialog(renderer, "¡Has perdido!\nPuntuación: " + scoreManager.getScore() +
                "\nPuntuación máxima: " + scoreManager.getHighScore(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void resetGame() {
        spawner.getEnemies().clear();
        projectiles.clear();
        scoreManager.resetScore();
        ship.setPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 60);
        isGameOver = false;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Enemy> getEnemies() {
        return spawner.getEnemies();
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getScore() {
        return scoreManager.getScore();
    }

    public int getHighScore() {
        return scoreManager.getHighScore();
    }

    public void shoot() {
        projectiles.add(new Projectile(ship.getX() + 22, ship.getY(), 7));
    }
}


