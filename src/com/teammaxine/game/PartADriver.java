package com.teammaxine.game;

import com.teammaxine.game.elements.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The driver class for the game, takes input from standard input, stores the input as an
 * array of cells, adds the cells belonging to com.teammaxine.game.elements.Horizontal and com.teammaxine.game.elements.Vertical players to array lists
 * of cells in the players themselves, and then goes through the Array list in order to count
 * the number of possible legal moves each player can make.
 */
public class PartADriver {

    public static void main(String[] args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

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
