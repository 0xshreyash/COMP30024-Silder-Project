package com.teammaxine.board.helpers;

/**
 * Created by noxm on 10/05/17.
 */
import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;
import com.teammaxine.board.elements.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by noxm on 17/04/17.
 */
public class MonteCarloScorer extends Scorer {
    // score += cell property * this
    private boolean showDebug = false;

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
