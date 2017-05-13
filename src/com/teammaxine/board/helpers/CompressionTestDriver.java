package com.teammaxine.board.helpers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.CompressedBoard;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class CompressionTestDriver {

    public static void main(String []args) {
        Board board = Generator.generateRandomBoard(5);
        System.out.println(board);
        BoardCompressor compressor = new BoardCompressor();
        CompressedBoard newBoard = compressor.compress(board);
        System.out.println(newBoard);
    }
}
