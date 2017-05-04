/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.elements;

import aiproj.slider.Move;

/**
 * Horizontal agent of the board
 */
public class Horizontal extends BoardAgent {
    public static Move.Direction[] directions = {
            Move.Direction.UP,
            Move.Direction.RIGHT,

    };

    public Horizontal(int size) {
        super(size);
    }

    /**
     * Sets the legal directions for the horizontal agent.
     */
    @Override
    public void setAllDirections() {
        for (Move.Direction direction : Horizontal.directions) {
            this.addLegalDirection(direction);
        }
    }

    /**
     * Checks for the edge case when the the horizontal can
     * go off the board from the right.
     *
     * @param cell to consider
     * @return true if the cell is on the edge and can go off
     * the right.
     */
    @Override
    public boolean edgeCheck(Cell cell) {
        return cell.getPos().getX() == size - 1;
    }

    @Override
    public String toString() {
        return "Horizontal BoardAgent";
    }
}
