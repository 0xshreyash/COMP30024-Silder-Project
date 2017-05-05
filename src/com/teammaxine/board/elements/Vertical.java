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
 * Vertical agent of the board
 */
public class Vertical extends BoardAgent {

    /**
     * Valid directions for the vertical.
     */
    public static Move.Direction[] directions  = {
            Move.Direction.UP,
            Move.Direction.LEFT,
            Move.Direction.RIGHT
    };

    public Vertical(int size) {
        super(size);
    }

    /**
     * Sets the legal directions for the vertical agent.
     */
    @Override
    public void setAllDirections() {
        for (Move.Direction direction : Vertical.directions) {
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
    public Move.Direction getForward() {
        return Move.Direction.UP;
    }

    @Override
    public String toString() {
        return "Vertical BoardAgent";
    }
}
