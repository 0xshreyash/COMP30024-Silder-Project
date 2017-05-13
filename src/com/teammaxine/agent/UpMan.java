package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.scorers.Scorer;

/**
 * Created by shreyashpatodia on 10/05/17.
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
