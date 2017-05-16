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
import com.teammaxine.board.helpers.Vector2;

import java.util.ArrayList;

/**
 * Horizontal agent of the board
 */
public class Horizontal extends BoardAgent {
    public static Move.Direction[] directions = {
            Move.Direction.UP,
            Move.Direction.RIGHT,
            Move.Direction.DOWN,

    };

    public Horizontal(int size, Board board) {
        super(size, board);
    }

    public Horizontal(Horizontal other) {
        super(other);
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

    private boolean isBlocking(Move m, int proximity) {
        int i = m.j;
        int j = m.i;
        int curr = 1;
        while(j > 0 && curr <= proximity) {
            j--;
            if(this.getBoard().getBoard()[i][j].getValue() == Board.CELL_VERTICAL)
                return true;
            else if(this.getBoard().getBoard()[i][j].getValue() == Board.CELL_BLOCKED)
                return false;
            curr++;
        }

        return false;
    }

    /**
     * Gets greedily defined optimistic moves for the horizontal player
     * @return arraylist of optimisitc moves.
     */
    @Override
    public ArrayList<AgentAction> getOptimisticMoves() {
        ArrayList<AgentAction> moves = getLegalMoves();
        ArrayList<AgentAction> finalMoves = new ArrayList<>();

        for (AgentAction m : moves) {
            if (!isBlocking(m, 2)) {
                if (m.d == Move.Direction.RIGHT) {
                    finalMoves.add(m);
                }
            }
        }

        if (finalMoves.size() == 0)
            for (AgentAction m : moves) {
                if (!isBlocking(m, 1)) {
                    if (m.d == Move.Direction.RIGHT) {
                        finalMoves.add(m);
                    }
                }
            }

        // try with less contraints
        if (finalMoves.size() == 0)
            for (AgentAction m : moves) {
                if (m.d == Move.Direction.RIGHT) {
                    finalMoves.add(m);
                }
            }

        if (finalMoves.size() > 0)
            return finalMoves;
        return moves;
    }

    @Override
    public Move.Direction getForward() {
        return Move.Direction.RIGHT;
    }

    @Override
    public String toString() {
        return "Horizontal BoardAgent";
    }
}
