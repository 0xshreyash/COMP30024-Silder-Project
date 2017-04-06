/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.game.elements;

/**
 * Vertical agent of the game
 */
public class Vertical extends Agent {

    /**
     * Valid directions for the vertical.
     */
    public static String[] directions = {
            Board.DIR_UP,
            Board.DIR_LEFT,
            Board.DIR_RIGHT
    };

    public Vertical(int size) {
        super(size);
    }

    /**
     * Sets the legal directions for the vertical agent.
     */
    @Override
    public void setAllDirections() {
        for (String direction : Vertical.directions) {
            this.addLegalDirection(direction);
        }
    }

    /**
     * Checks for the edge case when the the vertical can
     * go off the board from the top.
     *
     * @param cell to consider
     * @return true if the cell is on the edge and can go off
     * the top.
     */
    @Override
    public boolean edgeCheck(Cell cell) {
        return cell.getPos().getY() == size - 1;
    }

    @Override
    public String toString() {
        return "Vertical Agent";
    }
}
