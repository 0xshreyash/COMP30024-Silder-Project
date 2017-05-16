/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.*;

/**
 * Scorer that used to used in Monte Carlo for additional weighting
 * We used Machine Learning with the scorer but it still couldn't
 * beat our minimax agent.
 *
 */
public class MonteCarloScorer extends Scorer {
    public MonteCarloScorer() {

    }

    public MonteCarloScorer(double dist, double count, double block, double side, double forward) {
        distance_score = dist;
        count_score = count;
        block_score = block;
        move_side_score = side;
        move_forward_score = forward;
    }

    @Override
    public double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if(playerIsHorizontal) {
            return scoreBoardHorizontal(board);
        } else {
            return scoreBoardVertical(board);
        }
    }
}
