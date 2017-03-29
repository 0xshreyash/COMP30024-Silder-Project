/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */
package com.teammaxine.game.helpers;

/**
 * Object that represents a coordinate
 */
public class Vector2 {
    private int x;
    private int y;

    public Vector2(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
    }

    public boolean equals(Vector2 other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    public int getX() {
        return this.x;
    }

    public void setX(int xPos) {
        this.x = xPos;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int yPos) {
        this.y = yPos;
    }

    public Vector2 getPos() {
        return this;
    }

    public void setPos(Vector2 other) {
        setX(other.getX());
        setY(other.getY());
    }

    @Override
    public String toString() {
        return "Vector2: (" + this.getX() + ", " + this.getY() + ")";
    }
}
