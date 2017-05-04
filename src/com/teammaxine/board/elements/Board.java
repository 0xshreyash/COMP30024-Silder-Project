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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Board class - the environment of the board
 * NOTE: All directions are absolute i.e. they are from the point of view
 * of the viewer. So vertical cannot move "down" and horizontal cannot move
 * to the "left".
 */
public class Board {

    /**
     * Constants values that defines the board
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
     *                     1st string = bottom row
     */
    public Board(ArrayList<String> boardMapping) {

        this.size = boardMapping.size();
        board = new Cell[this.size][];

        /*
        Creating all the cells first so that we don't have any issues when
        setting the neighbours for each cell
        */
        createCells();

        for (int row = 0; row < this.size; row++) {
            String line = boardMapping.get(row);
            char[] rowValues = line.toCharArray();

            int column = 0;
            for (char value : rowValues) {
                board[row][column].setValue(value);
                //System.out.println(board[row][column].getPos() + " " + value);
                addNeighbours(row, column);
                column++;
            }
        }
    }

    /**
     * Print legal moves for Horizontal and Vertical according to Part A
     * specifications
     *
     * PROBABLY NOT NEEDED FOR PART-2 OF THE ASSIGNMENT.
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
    private void createCells() {
        for (int row = this.size - 1; row >= 0; row--) {
            this.board[row] = new Cell[this.size];

            for (int column = 0; column < this.size; column++) {
                this.board[row][column] = new Cell(new Vector2(row, column));
                //System.out.println(board[row][column].getPos());
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
    private void addNeighbours(int row, int column) {
        if (row != this.size - 1)
            this.board[row][column].setNeighbour(Move.Direction.UP,
                                        this.board[row  + 1][column]);

        if (row != 0)
            this.board[row][column].setNeighbour(Move.Direction.DOWN,
                                        this.board[row - 1][column]);

        if (column != this.size - 1)
            this.board[row][column].setNeighbour(Move.Direction.RIGHT,
                                        this.board[row][column + 1]);

        if (column != 0)
            this.board[row][column].setNeighbour(Move.Direction.LEFT,
                                        this.board[row][column - 1]);
    }

    /**
     * Function used in development to print the contents of the board, so as to
     * aid program testing and debugging.
     */
    @Override
    public String toString() {
        String boardString = "";

        // print from top row
        for (int row = this.size - 1; row >= 0; row--) {
            for (int column = 0; column < this.size; column++) {
                boardString += this.board[row][column] + " ";
            }
            boardString += "\n";
        }

        return boardString;
    }

    /**
     * Returns all the cells of a certain type to the calling function
     * for easy usage.
     * @param type the type of cell to find
     * @return a HashMap containing all the cells of the type
     */
    public HashMap<Vector2, Cell> getCellsOfType(char type) {
        HashMap<Vector2, Cell> cells = new HashMap<>();
        for(int row = 0; row < this.size; row++) {
            for(int column = 0; column < this.size; column++) {
                if(board[row][column].getValue() == type)
                    cells.put(new Vector2(row, column), board[row][column]);
            }
        }

        return cells;
    }

    public void changeCellValue(int x, int y, char newValue) {
        board[x][y].setValue(newValue);
    }
    public Vertical getVertical() {
        return vertical;
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Integer getSize() {
        return size;
    }
}
