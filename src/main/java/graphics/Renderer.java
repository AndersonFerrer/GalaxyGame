/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

/**
 *
 * @author andersonferrer
 */

import controllers.GameController;
import entities.*;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import utils.Constants;
import java.awt.event.KeyListener;
import java.util.Random;

public class Renderer extends JPanel {
    private final GameController controller;
    private final JFrame frame;

    public Renderer(GameController controller) {
        this.controller = controller;
        this.frame = new JFrame("Galaxy Game");
        setupFrame();
    }

    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
    }

    public void start() {
        frame.requestFocusInWindow();
    }

    public void addKeyListener(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Random rand = new Random();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Dibujar la nave
        Ship ship = controller.getShip();
        g.setColor(Color.BLUE);
        g.fillRect(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());

        // Dibujar los proyectiles
        for (Projectile projectile : controller.getProjectiles()) {
            g.setColor(Color.YELLOW);
            g.fillRect(projectile.getX(), projectile.getY(), 5, 10);
        }

        // Dibujar los enemigos
        for (Enemy enemy : controller.getEnemies()) {
            g.setColor(Color.RED);
            g.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        }

        // Dibujar puntuación
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Puntuación: " + controller.getScore(), 10, 20);
        g.drawString("Máxima Puntuación: " + controller.getHighScore(), 10, 40);
    }
}


