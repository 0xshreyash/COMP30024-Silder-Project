/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */
package com.teammaxine.game.elements;

import com.teammaxine.game.actions.ActionMove;
import com.teammaxine.game.actions.AgentAction;
import com.teammaxine.game.helpers.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * AI agent in the game
 */
public abstract class Agent {
    HashMap<Vector2, Cell> myCells;
    ArrayList<String> legalDirections;

    /**
     * Agent that plays out the game
     *
     * @param size size of the board the agent is in
     */
    public Agent(int size) {
        // cells that agent controls
        this.myCells = new HashMap(size);
        this.legalDirections = new ArrayList<>();
        this.setAllDirections();
    }

    /**
     * directions it is allowed to move
     *
     * @param direction
     */
    public void addLegalDirection(String direction) {
        this.legalDirections.add(direction);
    }

    /**
     * add a cell that the agent controls
     *
     * @param newCell
     */
    public void addCell(Cell newCell) {

        this.myCells.put(newCell.getPos(), newCell);

    }

    /**
     * remove a cell that agent controls
     *
     * @param oldCell
     */
    public void removeCell(Cell oldCell) {
        this.myCells.remove(oldCell);
    }

    /**
     * check if agent controls the cell
     *
     * @param cell
     * @return true if the cell is being controled by agent
     */
    public boolean hasCell(Cell cell) {
        return this.myCells.values().contains(cell);
    }

    /**
     * get all the legal moves possible
     *
     * @return array list of agent actions
     */
    public ArrayList<AgentAction> getLegalMoves() {
        ArrayList<AgentAction> moves = new ArrayList<>();

        for (Cell myCell : this.myCells.values()) {
            for (String legalDirection : this.legalDirections) {
                char value;
                Vector2 neighourPos = null;

                if (myCell.neighbours.containsKey(legalDirection)) {
                    value = myCell.getNeighbour(legalDirection).getValue();
                    neighourPos = myCell.getNeighbour(legalDirection).getPos();
                } else {
                    value = Board.CELL_UNKNOWN;
                }

                if (value == Board.CELL_EMPTY) {
                    moves.add(new ActionMove(myCell.getPos(), neighourPos));
                }
            }
        }

        return moves;
    }

    /**
     * agents should be initialized with this function
     */
    public abstract void setAllDirections();
}
