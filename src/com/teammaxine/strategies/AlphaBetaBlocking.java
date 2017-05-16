/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.CompressedBoard;
import com.teammaxine.board.helpers.BoardCompressor;
import com.teammaxine.board.helpers.Compresser;
import com.teammaxine.board.scorers.BlockingScorer;

import java.util.*;

/**
 * Alpha beta search variant that tries to block the opponent,
 * this strategy uses the blocking scorer and searches the entire
 * search tree to find the best move. Strategy focuses on
 * aggressively blocking the other player rather than trying
 * to go off the board itself.
 */
public class AlphaBetaBlocking implements Strategy{
    private char myPlayer;
    private char otherPlayer;
    private BlockingScorer scorer;
    private int maxDepth;

    public AlphaBetaBlocking(char player, BlockingScorer scorer, int maxDepth) {
        this.myPlayer = player;
        this.otherPlayer = this.myPlayer == Board.CELL_HORIZONTAL?
                Board.CELL_VERTICAL: Board.CELL_HORIZONTAL;
        this.scorer = scorer;
        this.maxDepth = maxDepth;
    }

    public Move findMove(Board board, int depth) {
        Move bestMove = alphaBetaSearch(depth, board,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

    /**
     * Performs alpha beta search recursively
     * @param depth the depth to go to
     * @return the best move found according to search.
     */
    private Move alphaBetaSearch(int depth, Board board, double alpha, double beta) {
        Move bestMove = null;
        double bestVal = Integer.MIN_VALUE;

        ArrayList<? extends Move> legalMoves = board.getLegalMoves(myPlayer);
        for(Move move : legalMoves) {
            board.makeMove(move, myPlayer);

            //System.out.println("--------------------");
            //System.out.println("With move :" + move);
            //System.out.println("New board\n" + newBoard);
            // Check if depth - 1 should be here or not
            double val = minValue(board, alpha, beta, depth - 1);
            //System.out.println("Score for this move would be:" + val);
            //bestVal = Math.max(bestVal, val);
            if(val > bestVal) {
                //System.out.println("Making this the best move");
                bestVal = val;
                //System.out.println("With move :" + move);
                //System.out.println(val);
                bestMove = move;
            }
            //cache.add(compressor.compress(b))
            board.undoMove(move, myPlayer);
            alpha = Math.max(alpha, bestVal);
            if(alpha >= beta) {
                return bestMove;
            }
            //System.out.println("--------------------");
        }
        //System.out.println("Nodes visited " + nodes);
        return bestMove;
    }

    /**
     * The function performing the moves for the maximizing player
     * @param board the current board
     * @param alpha the value of alpha
     * @param beta the value of beta
     * @param depth the depth left to travel
     * @return the value of the best move made.
     */
    private double maxValue(Board board, double alpha, double beta, int depth) {
        if(board.horizontalWon()) {
            if(this.myPlayer == 'H')
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(board.verticalWon()) {
            if(this.myPlayer == 'V')
                return  Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer, (depth + 1)/2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(myPlayer);
        double bestVal = Integer.MIN_VALUE;
        // We are out of moves.
        if(legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return minValue(board, alpha, beta, depth);

        for(Move move : legalMoves) {
            board.makeMove(move, myPlayer);
            bestVal = Math.max(bestVal, minValue(board, alpha, beta, depth - 1));
            alpha = Math.max(bestVal, alpha);
            board.undoMove(move, myPlayer);
            if (alpha >= beta)
                break;
        }
        return bestVal;
    }

    /**
     * The function performs the move for the minimizing player
     * @param board the current board
     * @param alpha the value of alpha
     * @param beta the value of beta
     * @param depth the depth still to go
     * @return the value of the best move according to the minimizing player
     */
    private double minValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Min called");
        //System.out.println(board);

        if(board.horizontalWon()) {
            if(this.myPlayer == Board.CELL_HORIZONTAL)
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(board.verticalWon()) {
            if(this.myPlayer == Board.CELL_VERTICAL)
                return  Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, otherPlayer, (depth + 1)/2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(otherPlayer);
        // The other player is out of moves.
        if(legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return maxValue(board, alpha, beta, depth);

        double bestVal = Integer.MAX_VALUE;
        //
        if(legalMoves.size() == 0)
            return Integer.MAX_VALUE;
        //Board newBoard = new Board(board);

        for(Move move : legalMoves) {
            board.makeMove(move, otherPlayer);
            bestVal = Math.min(bestVal, maxValue(board, alpha, beta, depth - 1));
            beta = Math.min(bestVal, beta);
            board.undoMove(move, otherPlayer);
            if(alpha >= beta)
                break;
        }
        return bestVal;
    }
}
