package com.teammaxine.board.elements;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class CompressedBoard {

    public static final byte CELL_EMPTY = 0;
    public static final byte CELL_HORIZONTAL = 1;
    public static final byte CELL_VERTICAL = 2;
    public static final byte CELL_BLOCKED = 3;

    private byte[][] compBoard;
    private int size;

    public CompressedBoard(byte compBoard[][], int size) {
        this.compBoard = compBoard;
        this.size = size;
    }

    @Override
    public String toString() {
        String boardString = "";
        // print from top row
        for (int row = this.size - 1; row >= 0; row--) {
            for (int column = 0; column < this.size; column++) {
                boardString += this.compBoard[row][column] + " ";
            }
            boardString += "\n";
        }

        return boardString;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this)
            return true;
        if(!(other instanceof  CompressedBoard))
            return false;
        CompressedBoard o = (CompressedBoard)other;
        if(this.size != o.getSize())
            return false;
        byte[][] otherBoard = o.getCompBoard();
        for (int row = this.size - 1; row >= 0; row--) {
            for (int column = 0; column < this.size; column++) {
                if (otherBoard[row][column] != compBoard[row][column])
                    return false;
            }
        }
        return true;
    }


    public byte[][] getCompBoard() {
        return compBoard;
    }

    public void setCompBoard(byte[][] compBoard) {
        this.compBoard = compBoard;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int row = 0; row < this.size; row++) {
            hash += Arrays.hashCode(compBoard[row]);
        }
        return hash;
    }

}
