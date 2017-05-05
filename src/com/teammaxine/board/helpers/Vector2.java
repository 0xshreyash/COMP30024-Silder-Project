/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.helpers;

/**
 * Object that represents a coordinate
 */
public class Vector2 {
    private int x;
    private int y;

    /**
     * Creates a vector2 with given x and y coordinates
     *
     * @param xPos initial x coordinate of vector
     * @param yPos initial y coordinate of vector
     */
    public Vector2(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
    }

    /**
     * Copy constructor for Vector2
     * @param other the vector who's contents we need to copy
     */
    public Vector2(Vector2 other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * Checks for equality of vectors
     *
     * @param other the vector to compare with
     * @return true if they are equal, false if they are not
     */
    public boolean equals(Vector2 other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * Gives the x position of the vector
     *
     * @return x coordinate of the vector
     */
    public int getX() {
        return this.x;
    }

    /**
     * Changes x coordinate of vector
     *
     * @param xPos the new x-coordinate of the vector
     */
    public void setX(int xPos) {
        this.x = xPos;
    }

    /**
     * Gives the y position of the vector
     *
     * @return y coordinate of the vecotr
     */
    public int getY() {
        return this.y;
    }

    /**
     * Changes y coordinate of the vector
     *
     * @param yPos the new y-coordinate of the vector
     */
    public void setY(int yPos) {
        this.y = yPos;
    }

    /**
     * Gives the vector itself
     *
     * @return the vector
     */
    public Vector2 getPos() {
        return this;
    }

    /**
     * Changes the x and y position of the vector
     *
     * @param other the new Vector2 coordinate of the vector
     */
    public void setPos(Vector2 other) {
        setX(other.getX());
        setY(other.getY());
    }

    @Override
    public String toString() {
        return "Vector2: (" + this.getX() + ", " + this.getY() + ")";
    }
}
