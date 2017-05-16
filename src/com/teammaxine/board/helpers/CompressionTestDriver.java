/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.helpers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.CompressedBoard;

/**
 * Test driver for our compression enginer
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
