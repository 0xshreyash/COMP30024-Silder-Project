/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.elements;

import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;

import java.util.ArrayList;

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

    public Vertical(int size, Board board) {
        super(size, board);
    }


    public Vertical(Vertical other) {
        super(other);
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

    private boolean isBlocking(Move m, int proximity) {
        int i = m.j;
        int j = m.i;
        int curr = 1;

        while(i > 0 && curr <= proximity) {
            i--;
            if(this.getBoard().getBoard()[j][i].getValue() == 'H')
                return true;
            else if(this.getBoard().getBoard()[j][i].getValue() == 'B')
                return false;
            curr++;
        }

        return false;
    }

    @Override
    public ArrayList<AgentAction> getOptimisticMoves() {
        ArrayList<AgentAction> moves = getLegalMoves();
        ArrayList<AgentAction> finalMoves = new ArrayList<>();


        for (AgentAction m : moves) {
            if (!isBlocking(m, 2)) {
                if (m.d == Move.Direction.UP) {
                    finalMoves.add(m);
                }
            }
        }

        if (finalMoves.size() == 0)
            for (AgentAction m : moves) {
                if (!isBlocking(m, 1)) {
                    if (m.d == Move.Direction.UP) {
                        finalMoves.add(m);
                    }
                }
            }

        // try with less contraints
        if (finalMoves.size() == 0)
            for (AgentAction m : moves) {
                if (m.d == Move.Direction.UP) {
                    finalMoves.add(m);
                }
            }

        if (finalMoves.size() > 0)
            return finalMoves;
        return moves;
    }
}
