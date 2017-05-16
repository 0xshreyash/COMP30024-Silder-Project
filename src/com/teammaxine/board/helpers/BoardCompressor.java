package com.teammaxine.board.helpers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.elements.CompressedBoard;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class BoardCompressor implements Compresser<Board, CompressedBoard> {

    @Override
    public CompressedBoard compress(Board b) {
        Cell board[][] = b.getBoard();
        int size = b.getSize();
        byte[][] compBoard = new byte[size][];

        for(int i = 0; i < size; i++) {
            compBoard[i] = new byte[size];
            for(int j = 0; j < size; j++) {
                switch(board[i][j].getValue()) {
                    case Board.CELL_EMPTY:
                        //System.out.println(i + " " + j);
                        compBoard[i][j] = CompressedBoard.CELL_EMPTY;
                        //System.out.println(bitset);
                        break;
                    case Board.CELL_BLOCKED:
                        //System.out.println(i + " " + j);
                        compBoard[i][j] = CompressedBoard.CELL_BLOCKED;
                        //System.out.println(bitset);
                        break;
                    case Board.CELL_HORIZONTAL:
                        //System.out.println(i + " " + j);
                        compBoard[i][j] = CompressedBoard.CELL_HORIZONTAL;
                        //System.out.println(bitset);
                        break;
                    case Board.CELL_VERTICAL:
                        //System.out.println(i + " " + j);
                        compBoard[i][j] = CompressedBoard.CELL_VERTICAL;
                        //System.out.println(bitset);
                        break;
                    default: System.err.println("Error! Compression not working");
                }
            }
        }
        return new CompressedBoard(compBoard, size);
    }


}
