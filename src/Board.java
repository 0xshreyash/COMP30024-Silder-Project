/**
 * Created by ShreyashPatodia on 18/03/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* NOTE: All directions are absolute i.e. they are from the point of view of the viewer.
 * So vertical cannot move "down" and horizontal cannot move to the "left", this has been
 * hardcoded into the classes.
 */

/** The driver class for the game, takes input from standard input, stores the input as an
 *  array of cells, adds the cells belonging to Horizontal and Vertical players to array lists
 *  of cells in the players themselves, and then goes through the Array list in order to count
 *  the number of possible legal moves each player can make.
 *
 */
public class Board {

    private Cell[][] board;
    private Vertical vertical;
    private Horizontal horizontal;
    private Integer N;

    /**
     * function that takes input from the user.
     */
     public void getInput() {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        this.N = 0;
        try {
            this.N = Integer.parseInt(buffer.readLine());
        }
        catch(IOException e) {

            System.err.println(e);
        }


         this.board = new Cell[this.N][];
         this.vertical = new Vertical(this.N);
         this.horizontal = new Horizontal(this.N);

        /* Creating all the cells first so that we don't have any issues when setting
         * the neighbours for each cell
         */
        createCells();

        char[] rowValues;

        for(int row = this.N - 1; row >= 0; row--) {

            String line = "";
            try {
                line = buffer.readLine();
            }
            catch(IOException e) {

                System.err.println(e);
            }

            line = line.replaceAll("\\s","");
            rowValues = line.toCharArray();

            int column = 0;
            for(char value : rowValues) {

                board[row][column].setValue(value);
                addNeighbours(row, column);

                if(value == 'H') {
                    horizontal.addCell(board[row][column]);
                }
                if(value == 'V') {
                    vertical.addCell(board[row][column]);
                }

                column++;
            }
        }

        this.printLegalMoves();


    }

    public void printLegalMoves() {
        
        System.out.println(this.horizontal.getLegalMoves());
        System.out.println(this.vertical.getLegalMoves());

    }

    /**
     * Creates the cells for the board, we need to do the pre-processing in
     * order to be able to add the neighbours to the cells when we are taking
     * input.
     */
    public void createCells() {

        for(int row = this.N - 1; row >= 0; row --) {

            this.board[row] = new Cell[this.N];

            for(int column = 0; column < this.N; column++) {

                this.board[row][column] = new Cell(new Vector2(row, column));
            }
        }

    }

    /**
     * Adds neighbours to the cells "neighbour" hash table, with "up", "down",
     * "right" or "left" keys depending on the neighbours existence and relative
     * position from the players cell.
     */
    public void addNeighbours(int row, int column) {

        if(row != this.N - 1) {

            this.board[row][column].setNeighbour("up", this.board[row + 1][column]);
        }
        if(row != 0) {

            this.board[row][column].setNeighbour("down", this.board[row - 1][column]);
        }
        if(column != N - 1) {

            this.board[row][column].setNeighbour("right", this.board[row][column + 1]);
        }
        if(column != 0) {

            this.board[row][column].setNeighbour("left", this.board[row][column - 1]);
        }
    }

    /**
     * Function used in development to print the contents of the board, so as to
     * aid program testing and debugging.
     */
    public void printBoard() {

        for (int row = this.N - 1; row >= 0; row--) {
            for (int column = 0; column < this.N; column++) {

                System.out.print(this.board[row][column].getValue() + " ");

            }

            System.out.println();
        }
    }


}
