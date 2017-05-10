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
    private static final int TRIES = 1;
    private static final int MAX_DEPTH = 100;

    public MonteCarlo(char player) {
        this.player = player;
        this.random = new Random();
    }

    @Override
    public Move findMove(Board currentBoard, int depth) {
        ArrayList<AgentAction> moves;

        if(player == 'H')
            moves = currentBoard.getHorizontal().getLegalMoves();
        else
            moves = currentBoard.getVertical().getLegalMoves();

        double maxScore = Double.NEGATIVE_INFINITY;
        Move toMake = null;

        for(Move m : moves) {
            double expected = Double.NEGATIVE_INFINITY;
            for(int i = 0; i < TRIES; i++) {
                double score = randomTraverse(currentBoard, m, 0, player, player);
                if(score > expected)
                    expected = score;
            }

            if(expected > maxScore)
                toMake = m;
        }

        return toMake;
    }

    private double randomTraverse(Board board, Move m, int depth, char turn, char player) {
        if(m != null)
            board.makeMove(m, turn);

        if(depth >= MAX_DEPTH) {
            return Scorer.scoreBoard(board, player);
        }

        char nextTurn = (turn == 'H')? 'V' : 'H';

        ArrayList<AgentAction> moves;

        if(nextTurn == 'H')
            moves = board.getHorizontal().getLegalMoves();
        else
            moves = board.getVertical().getLegalMoves();

        if(moves.size() > 0) {
            Move randomMove = moves.get(random.nextInt(moves.size()));
            return randomTraverse(board, randomMove, depth + 1, nextTurn, player);
        } else {
            return randomTraverse(board, null, depth + 1, nextTurn, player);
        }
    }
}
