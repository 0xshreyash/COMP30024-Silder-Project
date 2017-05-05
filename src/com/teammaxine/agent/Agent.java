/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.helpers.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Agent that plays out the board, base for different implementations
 * of our agent. Does the init and update stuff for us, so that we
 * can focus on the move with future implementations.
 */
public abstract class Agent {

    /** Cells controlled by the Agents */
    private HashMap<Vector2, Cell> myCells;
    /** The directions the Agent can move in */
    private ArrayList<Move.Direction> legalDirections;
    /** The row/column dimension for the board */
    private int dimension;
    /** The type of player we control, either 'H' or 'V' */
    private char player;

    public void setMyCells(HashMap<Vector2, Cell> myCells) {
        this.myCells = myCells;
    }

    public ArrayList<Move.Direction> getLegalDirections() {
        return legalDirections;
    }

    public void setLegalDirections(ArrayList<Move.Direction> legalDirections) {
        this.legalDirections = legalDirections;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public void setMyBoard(Board myBoard) {
        this.myBoard = myBoard;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public static Move.Direction[] getHorizontalDirections() {
        return HORIZONTAL_DIRECTIONS;
    }

    public static void setHorizontalDirections(Move.Direction[] horizontalDirections) {
        HORIZONTAL_DIRECTIONS = horizontalDirections;
    }

    public static Move.Direction[] getVerticalDirections() {
        return VERTICAL_DIRECTIONS;
    }

    public static void setVerticalDirections(Move.Direction[] verticalDirections) {
        VERTICAL_DIRECTIONS = verticalDirections;
    }

    public HashMap<Vector2, Cell> getMyCells() {
        return myCells;
    }

    /** The board representation */
    private Board myBoard;
    /** The possible moves for the player */
    private ArrayList<Move> moves;
    /** Legal Directions for the horizontal player */
    private static Move.Direction[] HORIZONTAL_DIRECTIONS = {
            Move.Direction.UP,
            Move.Direction.DOWN,
            Move.Direction.RIGHT
    };
    /** Legal Directions for the vertical player */
    private static Move.Direction[] VERTICAL_DIRECTIONS = {
            Move.Direction.UP,
            Move.Direction.LEFT,
            Move.Direction.RIGHT
    };

    public Agent() {
        this.myBoard = null;
        this.player = Board.CELL_UNKNOWN;
        this.dimension = -1;
        this.myCells = new HashMap<>();
        this.legalDirections = new ArrayList<>();
        this.moves = new ArrayList<>();
    }

    /**
     * Chooses which set of directions is accessible to the
     * agent and then calls setAllDirections with the appropriate
     * arguments to set the directions.
     */
    public void setLegalDirections() {

        if(player == Board.CELL_HORIZONTAL) {

            setAllDirections(HORIZONTAL_DIRECTIONS);
        }
        else {

            setAllDirections(VERTICAL_DIRECTIONS);
        }
    }

    /**
     * Sets all the the directions for the agent after getting
     * the appropriate directions from setLegalDirections
     *
     * @param directions Array of accessible directions for the
     *                   agent
     */
    public void setAllDirections(Move.Direction[] directions) {

        for(Move.Direction direction : directions) {

            this.legalDirections.add(direction);
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
    public boolean edgeCheck(Cell cell) {

        if(this.player == Board.CELL_HORIZONTAL)
            return cell.getPos().getX() == (dimension - 1);
        else if(this.player == Board.CELL_VERTICAL)
            return cell.getPos().getY() == (dimension - 1);
        return false;
    }

    /**
     * The function that initialises the agent, and makes sure
     * we are implementing SliderPlayer right.
     *
     * @param dimension the row, column length of the board
     * @param board the board as a String
     * @param player the type of player the agent is going to be
     */
    public void init(int dimension, String board, char player) {

        this.player = player;
        this.dimension = dimension;
        /*
           Creates the board and also adds the appropriate cells to the player's
           hashmap of cells.
         */
        this.createBoard(dimension, board);
        this.setLegalDirections();
        this.setMyCells();
        this.moves = getLegalMoves();
        /*System.out.println("Player");
        System.out.println(myBoard);
        System.out.println("Printing legal moves for:" + player);
        for(Move move: moves) {
            System.out.println(move);
        }*/
    }

    /**
     * Creates the board for the player to have a representation of the board
     *
     * @param dimension the row, column length of the board
     * @param board the representation of the board as a string
     */
    public void createBoard(int dimension, String board) {

        ArrayList<String> boardMapping = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(new StringReader(board));
        // Code duplication from PartA driver, won't have PartA driver in the submission.
        for (int row = 0; row < dimension; row++) {
            String line = "";
            try {
                line = buffer.readLine();
            }
            catch(IOException e) {
                System.err.println(e);
            }
            // remove spaces and add to the head of the list
            line = line.replaceAll("\\s", "");
            boardMapping.add(0, line);
        }

        this.myBoard = new Board(boardMapping);
    }

    /**
     * Gets all the cells of the type player for the player to use.
     */
    public void setMyCells() {

        myCells = myBoard.getCellsOfType(this.player);

    }

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

    /**
     * remove a cell that agent controls
     *
     * @param oldCell the cell that is not longer belonging to the agent.
     */
    public void removeCell(Cell oldCell) {
        this.myCells.remove(oldCell);
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

    /**
     * get all the legal moves possible
     *
     * @return array list of agent actions
     */
    public ArrayList<Move> getLegalMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        //System.out.println("Num myCells: " + this.myCells.size());
        for (Cell myCell : this.myCells.values()) {
            if(edgeCheck(myCell)) {

                /** Check for edge moves and then add the possible moves to the Arraylist of
                 *  moves the player can make
                 */

                if(this.player == Board.CELL_HORIZONTAL) {
                    moves.add(new Move(myCell.getPos().getX(), myCell.getPos().getY(), Move.Direction.RIGHT));
                }
                else
                    moves.add(new Move(myCell.getPos().getX(), myCell.getPos().getY(), Move.Direction.UP));

            }

            /** Moves other than the edge move */
            for (Move.Direction legalDirection : this.legalDirections) {
                char value;

                if (myCell.getNeighbours().containsKey(legalDirection)) {
                    value = myCell.getNeighbour(legalDirection).getValue();

                } else {
                    value = Board.CELL_UNKNOWN;
                }

                if (value == Board.CELL_EMPTY) {
                    moves.add(new Move(myCell.getPos().getX(), myCell.getPos().getY(), legalDirection));
                }
            }
        }
        //System.out.println(moves.size());
        return moves;
    }

    // Update covers the case where the other player may move to win the game.
    public void update(Move move) {
        if(move != null) {
            myBoard.changeCellValue(move.i, move.j, Board.CELL_EMPTY);
            if (move.d == Move.Direction.LEFT) {
                myBoard.changeCellValue(move.i - 1, move.j, player);
            } else if (move.d == Move.Direction.RIGHT && move.j != dimension - 1) {
                myBoard.changeCellValue(move.i + 1, move.j, player);
            } else if (move.d == Move.Direction.UP && move.i != dimension - 1) {
                myBoard.changeCellValue(move.i, move.j + 1, player);
            } else if (move.d == Move.Direction.DOWN) {
                myBoard.changeCellValue(move.i, move.j - 1, player);
            }
        }
        this.myCells = this.myBoard.getCellsOfType(player);
        //System.out.println(myBoard);
        return;
    }
}
