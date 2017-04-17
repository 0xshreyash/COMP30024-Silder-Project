/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board;

import com.teammaxine.board.elements.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The driver class for the board, takes input from standard input, stores the
 * input as an array of cells, adds the cells belonging to
 * com.teammaxine.board.elements.Horizontal
 * and com.teammaxine.board.elements.Vertical players to array lists
 * of cells in the players themselves, and then goes through the Array
 * list in order to count the number of possible legal moves each player
 * can make.
 */
public class PartADriver {

    public static void main(String[] args) {
        BufferedReader buffer = new BufferedReader(
                                        new InputStreamReader(System.in));

        int size = 0;
        try {
            size = Integer.parseInt(buffer.readLine());
        } catch (IOException e) {
            System.err.println(e);
        }

        // setting of the board
        ArrayList<String> boardMapping = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            String line = "";
            try {
                line = buffer.readLine();
            } catch (IOException e) {
                System.err.println(e);
            }

            // remove spaces and add to the head of the list
            line = line.replaceAll("\\s", "");
            boardMapping.add(0, line);
        }

        // board mapping is now bottom row = 1st element
        Board board = new Board(boardMapping);

        board.printLegalMoves();
    }
}
