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
import com.teammaxine.board.scorers.Scorer;

import java.util.ArrayList;

/**
 * Greedy agent that tries to make the best move from
 * a pre-defined set of optimistically set of good moves.
 * Moves fast, allows for better search later on in the game.
 */
public class AlphaBetaGreedy implements Strategy{
    private char myPlayer;
    private char otherPlayer;
    private Scorer scorer;

    public AlphaBetaGreedy(char player, Scorer scorer) {
        this.myPlayer = player;
        this.otherPlayer = this.myPlayer == Board.CELL_HORIZONTAL?
                Board.CELL_VERTICAL: Board.CELL_HORIZONTAL;
        this.scorer = scorer;
    }

    public Move findMove(Board board, int depth) {
        Move bestMove = alphaBetaSearch(depth, board, myPlayer,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return bestMove;
    }

    /**
     * Performs alpha beta search recursively
     * @param depth the depth the player is searching to.
     * @param currPlayer the player with the current chance
     * @return the best move according to search.
     */
    private Move alphaBetaSearch(int depth, Board board, char currPlayer, double alpha, double beta) {
        ArrayList<? extends Move> legalMoves = board.getOptimisticMoves(currPlayer);

        // Add terminal state here maybe, don't know if it matters.
        double bestVal = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        //System.out.println("++++++++++++++++++++");
        //System.out.println(board);
        //System.out.println("     possibles     ");
        Board newBoard = new Board(board);
        for(Move move : legalMoves) {
            newBoard.makeMove(move, myPlayer);
            double val = minValue(newBoard, alpha, beta, depth - 1);
//            System.out.println("Score for this move would be:" + val);
            //bestVal = Math.max(bestVal, val);
            if(val > bestVal) {
//                System.out.println("Making this the best move");
                bestVal = val;
//                System.out.println("With move :" + move);
//                System.out.println(val);
                bestMove = move;
            }
            newBoard.undoMove(move, myPlayer);
            if(alpha >= beta) {
                return bestMove;
            }
            alpha = Math.max(alpha, bestVal);
//            System.out.println("--------------------");
        }
//        System.out.println("++++++++++++++++++++");
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

        ArrayList<? extends Move> legalMoves = board.getOptimisticMoves(this.myPlayer);
        if(depth == 0 || isTerminalState(board)) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return this.scorer.scoreBoard(board, myPlayer);
        }
        double bestVal = Double.NEGATIVE_INFINITY;
        // The other player is out of moves so score th
        if(legalMoves.size() == 0)
            return scorer.scoreBoard(board, myPlayer);
        //Board newBoard = new Board(board);
        for(Move move : legalMoves) {
            board.makeMove(move, myPlayer);
            //System.out.println(newBoard);
            bestVal = Math.max(bestVal, minValue(board, alpha, beta, depth - 1));
            alpha = Math.max(bestVal, alpha);
            board.undoMove(move, myPlayer);
            if (beta <= bestVal)
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
        ArrayList<? extends Move> legalMoves = board.getOptimisticMoves(this.otherPlayer);

        if(depth == 0 || isTerminalState(board)) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }
        double bestVal = Double.POSITIVE_INFINITY;
        if(legalMoves.size() == 0)
            return scorer.scoreBoard(board, myPlayer);
        //Board newBoard = new Board(board);
        for(Move move : legalMoves) {
            board.makeMove(move, otherPlayer);
            bestVal = Math.min(bestVal, maxValue(board, alpha, beta, depth - 1));
            beta = Math.min(bestVal, beta);
            board.undoMove(move, otherPlayer);
            if(bestVal <= alpha)
                break;
        }
        return bestVal;
    }


    /**
     * Terminal state testing logic. Don't know if this is correct thought.
     * This function is not in board since terminal states are defined by the
     * algorithm and may be different from algorithm to algorithm.
     * @param board The board to be checked as terminal or not
     * @return true if the board is in terminal condition and false otherwise
     */
    private boolean isTerminalState(Board board) {
        if(board.getHorizontal().getMyCells().size() == 0)
            return true;
        else if(board.getVertical().getMyCells().size() == 0)
            return true;
        else if(board.getHorizontal().getLegalMoves().size() == 0 &&
                board.getHorizontal().getLegalMoves().size() == 0)
            return true;
        return false;
    }
}
