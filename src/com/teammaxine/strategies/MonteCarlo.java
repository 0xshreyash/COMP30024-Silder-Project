/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.scorers.Scorer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Monte Carlo search, with slight variations
 */
public class MonteCarlo implements Strategy {
    private char player;
    private Scorer scorer;
    private Random random;
    private static final int TRIES = 5000000; // tries to make per move
    private static final int MAX_DEPTH = 20;

    private static Move prevMove;

    public MonteCarlo(char player, Scorer scorer) {
        prevMove = null;
        this.scorer = scorer;
        this.player = player;
        this.random = new Random(System.currentTimeMillis());
    }

    private Move oppositeMove(Move m) {
        Move.Direction opDir;
        int i = m.i;
        int j = m.j;
        if(m.d == Move.Direction.UP) {
            opDir = Move.Direction.DOWN;
            j++;
        } else if(m.d == Move.Direction.DOWN) {
            opDir = Move.Direction.UP;
            j--;
        } else if(m.d == Move.Direction.LEFT) {
            opDir = Move.Direction.RIGHT;
            i--;
        } else {
            opDir = Move.Direction.LEFT;
            i++;
        }
        return new Move(i, j, opDir);
    }

    @Override
    public Move findMove(Board currentBoard, int depth) {
        ArrayList<? extends Move> moves = currentBoard.getOptimisticMoves(player);

        double maxScore = Double.NEGATIVE_INFINITY;
        Move toMake = null;

        for(Move m : moves) {
            double sum = 0;
            double localMax = Double.NEGATIVE_INFINITY;
            double localMin = Double.POSITIVE_INFINITY;

            Board copyBoard = new Board(currentBoard);
            for(int i = 0; i < TRIES; i++) {
                double score = randomTraverse(copyBoard, m, 20, player, player);
                sum += score;

                if(score >= localMax)
                    localMax = score;
                if(score <= localMin)
                    localMin = score;
            }

            // update with average score
            double avg = sum / TRIES;

            if((avg >= maxScore) && (prevMove == null || !oppositeMove(prevMove).toString().equals(m.toString()))) {
                maxScore = avg;
                toMake = m;
            }
        }

//        if(toMake == null){
//            System.out.println("Something is not right ");
//            System.out.println(moves);
//            System.out.println(prevMove + " " + toMake + " " + maxScore);
//            System.exit(0);
//        }

        prevMove = toMake;

        return toMake;
    }

    /**
     * Recursively randomly traverse down the tree
     * @param board current board
     * @param m the move
     * @param depth depth searched
     * @param turn whoever's turn this is
     * @param player the player
     * @return score of the traversal.
     */
    public double randomTraverse(Board board, Move m, int depth, char turn, char player) {
        if(m != null)
            board.makeMove(m, turn);

        if((board.getHorizontal().getMyCells().size() == 0 || board.getVertical().getMyCells().size() == 0) ||
                depth >= MAX_DEPTH ||
                (board.getHorizontal().getLegalMoves().size() == 0 && board.getVertical().getLegalMoves().size() == 0)) {
            char winner = board.getWinner();
            if(winner == '-') {
                // draw
                return 0;//Math.tanh(scorer.scoreBoard(board, player)) + 2*Math.tanh(proximity.scoreBoard(board, player));
            } else if(winner == player) {
                // win
                return 1;
            }
            // lose
            return -1;
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
