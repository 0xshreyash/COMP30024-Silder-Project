/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */
package com.teammaxine.game.elements;

/**
 * Horizontal agent of the game
 */
public class Horizontal extends Agent {
    public static String[] directions = {
            Board.DIR_UP,
            Board.DIR_DOWN,
            Board.DIR_RIGHT
    };

    public Horizontal(int size) {
        super(size);
    }

    @Override
    public void setAllDirections() {
        for (String direction : Horizontal.directions) {
            this.addLegalDirection(direction);
        }
    }

    @Override
    public boolean edgeCheck(Cell cell) {
        return cell.getPos().getX() == size - 1;
    }

    @Override
    public String toString() {
        return "Horizontal Agent";
    }
}
