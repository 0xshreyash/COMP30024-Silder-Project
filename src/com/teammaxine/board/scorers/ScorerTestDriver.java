/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Generator;

/**
 * Test driver for scorers.
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
