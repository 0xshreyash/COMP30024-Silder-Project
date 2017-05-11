package com.teammaxine.board.helpers;

import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;
import com.teammaxine.board.elements.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Taking a two-fold approach to scoring, not just scoring the final board but also
 * scoring the
 */
public class BetaScorer extends Scorer {
    // score += cell property * this

    // initialBoard is the board we start evaluating from.
    private Board initialBoard;
    double distance_change_score = 5;
    // Scores to evaluate blockedness
    double b_blocked_score = -10;
    double other_blocked_score = -5;
    double my_blocked_score = -2.5;
    double distance_score = 10;
    double count_score = 5;
    double block_score = 10;
    double move_side_score = 0;
    double move_forward_score = 3;

    public BetaScorer(Board initialBoard) {
        this.initialBoard = initialBoard;
    }

    public double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if (playerIsHorizontal) {
            return scoreBoardHorizontal(board) - scoreBoardVertical(board);
        } else {
            return scoreBoardVertical(board) - scoreBoardHorizontal(board);
        }
    }

    double scoreBoardVertical(Board board) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getVertical().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumVerticalDistance(this.initialBoard);
        int distanceNewBoard = sumVerticalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;

        return score;
    }

    double scoreBoardHorizontal(Board board) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getHorizontal().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumHorizontalDistance(this.initialBoard);
        int distanceNewBoard = sumHorizontalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;

        return score;
    }

    int horizontalMagnitudeOfBlockedness(Board board) {
        int magnitude = 0;


        return magnitude;
    }

    int verticalMagnitudeOfBlockedness(Board board) {
        int magnitude = 0;
        return magnitude;
    }

}
