/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */

package com.teammaxine.game.elements;

import com.teammaxine.game.helpers.Vector2;

import java.util.ArrayList;

/**
 * Board class - the environment of the game
 * <p>
 * NOTE: All directions are absolute i.e. they are from the point of view of the viewer.
 * So vertical cannot move "down" and horizontal cannot move to the "left", this has been
 * hardcoded into the classes.
 */
public class Board {

    /**
     * Constants values that defines the game
     * This information should be available to every class
     */
    public static final String DIR_UP = "up";
    public static final String DIR_DOWN = "down";
    public static final String DIR_LEFT = "left";
    public static final String DIR_RIGHT = "right";

    public static final char CELL_EMPTY = '+';
    public static final char CELL_UNKNOWN = '-';
    public static final char CELL_HORIZONTAL = 'H';
    public static final char CELL_VERTICAL = 'V';

    private Cell[][] board;
    private Vertical vertical;
    private Horizontal horizontal;
    private Integer size;

    /**
     * function that takes input from the user.
     *
     * @param boardMapping array list of strings that maps the board
     */
    public Board(ArrayList<String> boardMapping) {
        this.size = boardMapping.size();

        vertical = new Vertical(this.size);
        horizontal = new Horizontal(this.size);
        board = new Cell[this.size][];

        /*
        Creating all the cells first so that we don't have any issues when setting
        the neighbours for each cell
        */
        createCells();

        for (int row = 0; row < this.size; row++) {
            String line = boardMapping.get(row);
            char[] rowValues = line.toCharArray();

            int column = 0;
            for (char value : rowValues) {
                board[row][column].setValue(value);
                addNeighbours(row, column);

                if (value == CELL_HORIZONTAL)
                    horizontal.addCell(board[row][column]);

                if (value == CELL_VERTICAL)
                    vertical.addCell(board[row][column]);

                column++;
            }
        }
    }

    /**
     * Print legal moves for Horizontal and Vertical according to Part A
     * specifications
     */
    public void printLegalMoves() {
        System.out.println(this.horizontal.getLegalMoves().size());
        System.out.println(this.vertical.getLegalMoves().size());
    }

    /**
     * Creates the cells for the board, we need to do the pre-processing in
     * order to be able to add the neighbours to the cells when we are taking
     * input.
     */
    public void createCells() {
        for (int row = this.size - 1; row >= 0; row--) {
            this.board[row] = new Cell[this.size];

            for (int column = 0; column < this.size; column++) {
                this.board[row][column] = new Cell(new Vector2(row, column));
            }
        }
    }

    /**
     * Adds neighbours to the cells "neighbour" hash table, with "up", "down",
     * "right" or "left" keys depending on the neighbours existence and relative
     * position from the players cell.
     *
     * @param row    row number of the cell
     * @param column column number of the cell
     */
    public void addNeighbours(int row, int column) {
        if (row != this.size - 1)
            this.board[row][column].setNeighbour(DIR_UP, this.board[row + 1][column]);

        if (row != 0)
            this.board[row][column].setNeighbour(DIR_DOWN, this.board[row - 1][column]);

        if (column != this.size - 1)
            this.board[row][column].setNeighbour(DIR_RIGHT, this.board[row][column + 1]);

        if (column != 0)
            this.board[row][column].setNeighbour(DIR_LEFT, this.board[row][column - 1]);
    }

    /**
     * Function used in development to print the contents of the board, so as to
     * aid program testing and debugging.
     */
    @Override
    public String toString() {
        String boardString = "";

        for (int row = this.size - 1; row >= 0; row--) {
            for (int column = 0; column < this.size; column++) {
                boardString += this.board[row][column] + " ";
            }
            boardString += "\n";
        }

        return boardString;
    }
}
