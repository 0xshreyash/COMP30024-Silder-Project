package com.teammaxine.agent;




import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import com.teammaxine.board.elements.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by noxm on 17/04/17.
 * Base line AI
 * (dictates everything,
 * fails to fire a missile)
 */
public class JungEun implements SliderPlayer {
    @Override
    public void init(int dimension, String board, char player) {

        Board myBoard = createBoard(dimension, board);
        //System.out.println(myBoard);
    }

    public Board createBoard(int dimension, String board) {

        ArrayList<String> boardMapping = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(new StringReader(board));
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

        return new Board(boardMapping);
    }

    @Override
    public void update(Move move) {

    }

    @Override
    public Move move() {
        return null;
    }
}

