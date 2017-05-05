/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.elements;

import aiproj.slider.Move;
import com.teammaxine.board.helpers.Vector2;

import java.util.HashMap;

/**
 * Cell class that forms the board
 */
public class Cell {
    private Vector2 pos;
    private char value;

    /**
     * Neighbours of the cell, stored with keys "up", "down",
     * "right" and "left".
     */
    private HashMap<Move.Direction, Cell> neighbours;

    public Cell(Vector2 Position) {
        this.pos = Position;
        this.neighbours = new HashMap<>();
    }

    public Cell(Vector2 Position, char initialValue) {
        this.value = initialValue;
        this.pos = Position;
        this.neighbours = new HashMap<>();
    }

    /**
     * Gives the value of a cell which is the char representation
     * of the piece on the board.
     *
     * @return the value of the piece on the board.
     */
    public char getValue() {
        return this.value;
    }

    /**
     * Sets the value with the char representation of the piece
     * on the board.
     *
     * @param val the new value of the piece on the board.
     */
    public void setValue(char val) {
        this.value = val;
    }

    /**
     * Returns the position of the cell as a Vector2.
     *
     * @return position of the cell.
     */
    public Vector2 getPos() {
        return this.pos;
    }

    /**
     * Sets the neighbour of the cell, assigning it a unique key.
     *
     * @param key the key of this neighbour wrt to the hashmap of neighbours.
     * @param neighbour the neighbour to be added to the cell.
     */
    public void setNeighbour(Move.Direction key, Cell neighbour) {
        this.neighbours.put(key, neighbour);
    }

    /**
     * Returns the neighbour corresponding to the key passed as argument.
     *
     * @param key the key of the neighbour to be found.
     * @return the Cell with the key passes as argument.
     */
    public Cell getNeighbour(Move.Direction key) {
        return this.neighbours.get(key);
    }

    /**
     * Returns the hashmap of neighbours of a cell.
     *
     * @return the hashmap containing the cell's neighbours.
     */
    public HashMap<Move.Direction,Cell> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
