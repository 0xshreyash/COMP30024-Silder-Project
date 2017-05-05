package com.teammaxine.board.helpers;

import com.teammaxine.board.elements.Board;

/**
 * Created by noxm on 5/05/17.
 */
public class ScorerTestDriver {
    public static void main(String[] args) {
        Board b = Generator.generateRandomBoard(5);
        System.out.println(b);
        double h = Scorer.scoreBoard(b, 'H');
        double v = Scorer.scoreBoard(b, 'V');

        System.out.println("Horizontal Score: ");
        System.out.println(h);
        System.out.println("Vertical Score: ");
        System.out.println(v);
    }
}
