package com.teammaxine.board.helpers;

import com.teammaxine.board.elements.Board;

/**
 * Created by noxm on 5/05/17.
 */
public class Generator {
    public static Board generateRandomBoard(int size) {
        String boardString = "";

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                double rand = Math.random() * 23;
                if(rand < 2) {
                    boardString += "H";
                } else if (rand < 4) {
                    boardString += "V";
                } else if(rand < 6) {
                    boardString += "B";
                } else {
                    boardString += "+";
                }
            }
            boardString += "\n";
        }

        Board b = Board.boardFromString(boardString);

        return b;
    }

}
