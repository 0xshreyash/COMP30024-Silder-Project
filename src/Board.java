/**
 * Created by ShreyashPatodia on 18/03/17.
 */

import org.jdesktop.swingx.VerticalLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* NOTE: All directions are absolute i.e. they are from the point of view of the viewer.
 * So vertical cannot move "down" and horizontal cannot move to the "left", this has been
 * hardcoded into the classes.
 */
public class Board {

    public static void main(String []args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(buffer.readLine());
        Cell[][] board = new Cell[N][];

        Vertical vertical = new Vertical(N);
        Horizontal horizontal = new Horizontal(N);


        /* Creating all the cells first so that we don't have any issues when setting
         * the neighbours for each cell
         */
        createCells(board, N);

        char[] rowValues = new char[N];

        for(int row = N - 1; row >= 0; row--) {

            String line = buffer.readLine();
            line = line.replaceAll("\\s","");
            rowValues = line.toCharArray();
            int column = 0;
            for(char value : rowValues) {

                board[row][column].setValue(value);
                addNeighbours(board, row, column, N);

                if(value == 'H') {
                    horizontal.addCell(board[row][column]);
                }
                if(value == 'V') {
                    vertical.addCell(board[row][column]);
                }

                column++;
            }
        }
        
        printBoard(board, N);

        System.out.println(vertical.getLegalMoves());
        System.out.println(horizontal.getLegalMoves());
    }

    public static void createCells(Cell[][] board, int N) {

        for(int row = N - 1; row >= 0; row --) {

            board[row] = new Cell[N];

            for(int column = 0; column < N; column++) {

                board[row][column] = new Cell(new Vector2(row, column));
            }
        }

    }

    public static void addNeighbours(Cell[][] board, int row, int column, int N) {

        if(row != N - 1) {

            board[row][column].setNeighbour("up", board[row + 1][column]);
        }
        if(row != 0) {

            board[row][column].setNeighbour("down", board[row - 1][column]);
        }
        if(column != N - 1) {

            board[row][column].setNeighbour("right", board[row][column + 1]);
        }
        if(column != 0) {

            board[row][column].setNeighbour("left", board[row][column - 1]);
        }
    }

    public static void printBoard(Cell [][] board, int N) {

        for (int row = N - 1; row >= 0; row--) {
            for (int column = 0; column < N; column++) {

                System.out.print(board[row][column].getValue() + " ");

            }

            System.out.println();
        }
    }


}
