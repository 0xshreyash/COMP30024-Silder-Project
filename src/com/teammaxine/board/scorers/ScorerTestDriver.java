package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Generator;

/**
 * Created by noxm on 5/05/17.
 */
public class ScorerTestDriver {
    public static void main(String[] args) {
        Board b = Generator.generateRandomBoard(5);
        System.out.println(b);
        //System.out.println(b.getHorizontal().getLegalMoves());
//        double h = Scorer.scoreBoard(b, 'H');
//        double v = Scorer.scoreBoard(b, 'V');

//        System.out.println("Horizontal Score: " + h);
//        System.out.println("Vertical Score: " + v);
    }
}
