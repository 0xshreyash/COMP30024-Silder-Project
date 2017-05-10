package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Scorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by noxm on 10/05/17.
 */
public class MonteCarlo implements Strategy {
    private char player;
    private Random random;
    private static final int TRIES = 300;
    private static final int MAX_DEPTH = 100;

    public MonteCarlo(char player) {
        this.player = player;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public Move findMove(Board currentBoard, int depth) {
        ArrayList<AgentAction> moves;

        if(player == 'H') {
            moves = currentBoard.getHorizontal().getOptimisticMoves();
        } else {
            moves = currentBoard.getVertical().getOptimisticMoves();
        }

        double maxScore = Double.NEGATIVE_INFINITY;
        Move toMake = null;

        for(Move m : moves) {
            double sum = 0;
            double localMax = Double.NEGATIVE_INFINITY;
            double localMin = Double.POSITIVE_INFINITY;

            Board copyBoard = new Board(currentBoard);
            for(int i = 0; i < TRIES; i++) {
                double score = randomTraverse(copyBoard, m, 0, player, player);
                sum += score;

                if(score > localMax)
                    localMax = score;
                if(score < localMax)
                    localMin = score;
            }
            double avg = sum / TRIES;

            if(localMin > maxScore) {
                maxScore = localMin;
                toMake = m;
            }
        }

        return toMake;
    }

    private double randomTraverse(Board board, Move m, int depth, char turn, char player) {
        if(m != null)
            board.makeMove(m, turn);

        if((board.getHorizontal().getMyCells().size() == 0 || board.getVertical().getMyCells().size() == 0) ||
                depth >= MAX_DEPTH ||
                (board.getHorizontal().getLegalMoves().size() == 0 && board.getVertical().getLegalMoves().size() == 0)) {
            return Scorer.scoreBoard(board, player);
        }

        char nextTurn = (turn == 'H')? 'V' : 'H';

        ArrayList<AgentAction> moves;

        if(nextTurn == 'H')
            moves = board.getHorizontal().getOptimisticMoves();
        else
            moves = board.getVertical().getOptimisticMoves();

        if(moves.size() > 0) {
            Move randomMove = moves.get(random.nextInt(moves.size()));
            return randomTraverse(board, randomMove, depth + 1, nextTurn, player);
        } else {
            return randomTraverse(board, null, depth + 1, nextTurn, player);
        }
    }
}
