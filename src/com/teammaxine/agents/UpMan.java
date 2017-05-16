/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.agents;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.scorers.Scorer;

/**
 * Our second AI, tries to be greedy and move in
 * the direction of our goal. Surprisingly hard
 * to beat this AI.
 */
public class UpMan extends Agent {
    Scorer scorer = new Scorer();
    public Move move() {
        double maxScore = Double.NEGATIVE_INFINITY;
        double maxLateralScore = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        Move bestLateralMove = null;
        for(Move move : this.getMyBoard().getLegalMoves(this.getPlayer())) {
            if((this.getPlayer() == Board.CELL_VERTICAL && move.d == Move.Direction.UP) ||
                    (this.getPlayer() == Board.CELL_HORIZONTAL && move.d == Move.Direction.RIGHT)) {
                Board newBoard = new Board(this.getMyBoard());
                newBoard.makeMove(move, this.getPlayer());
                double newScore = scorer.scoreBoard(newBoard, this.getPlayer());
                if(newScore > maxScore) {
                    maxScore = newScore;
                    bestMove = move;
                }
            }
            else {
                Board newBoard = new Board(this.getMyBoard());
//                System.out.println(move);
                newBoard.makeMove(move, this.getPlayer());
                double newScore = scorer.scoreBoard(newBoard, this.getPlayer());
                if(newScore > maxLateralScore) {
                    maxLateralScore = newScore;
                    bestLateralMove = move;
                }
            }
        }

        if(bestMove == null) {
            bestMove = bestLateralMove;
        }
        this.update(bestMove, this.getPlayer());
        return bestMove;
    }
}
