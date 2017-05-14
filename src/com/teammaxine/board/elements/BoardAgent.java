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
import java.util.HashMap;

/**
 * AI agent in the board
 */
public abstract class BoardAgent {
    private HashMap<Vector2, Cell> myCells;
    private ArrayList<Move.Direction> legalDirections;
    int size;

    /**
     * BoardAgent that plays out the board
     *
     * @param size size of the board the agent is in
     */
    public BoardAgent(int size) {
        // cells that agent controls
        this.size = size;
        this.myCells = new HashMap<>(size);
        this.legalDirections = new ArrayList<>();
        this.setAllDirections();
    }

    public BoardAgent(BoardAgent other) {
        this.size = other.getSize();
        this.legalDirections = new ArrayList<>(other.getLegalDirections());
        this.myCells = new HashMap<>(size);
    }

    /**
     * agents should be initialized with this function
     */
    public abstract void setAllDirections();

    /**
     * Check if the cell is in the edge where you can get out of the board
     *
     * @param cell to consider
     * @return true if the cell is on the end edge
     */
    public abstract boolean edgeCheck(Cell cell);

    /**
     * directions it is allowed to move
     *
     * @param direction the direction to be added to the legal directions.
     */
    public void addLegalDirection(Move.Direction direction) {
        this.legalDirections.add(direction);
    }

    /**
     * add a cell that the agent controls
     *
     * @param newCell the new cell belonging to the agent i.e. a cell that
     * contains the agent's piece.
     */
    public void addCell(Cell newCell) {

        this.myCells.put(newCell.getPos(), newCell);

    }

    public HashMap<Vector2, Cell> getMyCells() {
        return myCells;
    }

    /**
     * remove a cell that agent controls
     *
     * @param oldCell the cell that is not longer belonging to the agent.
     */

    public void removeCell(Cell oldCell) {
        this.myCells.remove(oldCell.getPos(), oldCell);
    }


    /**
     * check if agent controls the cell
     *
     * @param cell the cell who's possession is to be checked for the agent
     * @return true if the cell is being controled by agent
     */
    public boolean hasCell(Cell cell) {
        return this.myCells.values().contains(cell);
    }

    public ArrayList<AgentAction> getOptimisticMoves() {
        ArrayList<AgentAction> moves = getLegalMoves();
        ArrayList<AgentAction> finalMoves = new ArrayList<>();

        for(AgentAction m : moves) {
            if((this instanceof Vertical && m.d == Move.Direction.UP) ||
               (this instanceof Horizontal && m.d == Move.Direction.RIGHT))
                finalMoves.add(m);
        }

        if(finalMoves.size() > 0)
            return finalMoves;
        return moves;
    }

    public ArrayList<AgentAction> getOptimisticMoves(int size) {
        ArrayList<AgentAction> moves = getLegalMoves();
        ArrayList<AgentAction> finalMoves = new ArrayList<>();

        for(AgentAction m : moves) {
            if((this instanceof Vertical && m.d == Move.Direction.UP && m.i != size - 1) ||
                    (this instanceof Horizontal && m.d == Move.Direction.RIGHT && m.j != size - 1))
                finalMoves.add(m);
        }
        if(finalMoves.size() > 0)
            return finalMoves;
        return moves;
    }
    /**
     * get all the legal moves possible
     *
     * @return array list of agent actions
     */
    public ArrayList<AgentAction> getLegalMoves() {
        ArrayList<AgentAction> moves = new ArrayList<>();

        for (Cell myCell : this.myCells.values()) {
            if(edgeCheck(myCell)) {
                moves.add(new AgentAction(AgentAction.ActionType.FINISH, myCell.getPos(), getForward()));
            }
            for (Move.Direction legalDirection : this.legalDirections) {
                char value;

                if (myCell.getNeighbours().containsKey(legalDirection)) {
                    value = myCell.getNeighbour(legalDirection).getValue();
                } else {
                    value = Board.CELL_UNKNOWN;
                }

                if (value == Board.CELL_EMPTY) {
                    //System.out.println("Neighbour of " + myCell.getPos() + " has value " + value);
                    moves.add(new AgentAction(AgentAction.ActionType.MOVE, myCell.getPos(), legalDirection));
                }
            }
        }

        return moves;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Move.Direction> getLegalDirections() {
        return legalDirections;
    }

    public abstract Move.Direction getForward();
}
