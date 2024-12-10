/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author andersonferrer
 */

public class Projectile {
    private int x, y, speed;

    public Projectile(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveUp() {
        y -= speed;
    }

    public boolean isOutOfBounds() {
        return y < 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

