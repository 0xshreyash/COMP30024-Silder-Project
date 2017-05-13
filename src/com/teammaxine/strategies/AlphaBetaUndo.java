package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.AlphaScorer;
import com.teammaxine.board.helpers.Scorer;

import java.util.ArrayList;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class AlphaBetaUndo implements Strategy{
    private char myPlayer;
    private char otherPlayer;
    private AlphaScorer scorer;

    public AlphaBetaUndo(char player, AlphaScorer scorer) {
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

    /**gb
     * Performs alpha beta search recursively
     * @param depth
     * @param currPlayer the player with the current chance
     * @return
     */
    private Move alphaBetaSearch(int depth, Board board, char currPlayer, double alpha, double beta) {
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(currPlayer);
        Move bestMove = null;
        // Add terminal state here maybe, don't know if it matters.
        double bestVal = Double.NEGATIVE_INFINITY;
        //System.out.println("++++++++++++++++++++");
        //System.out.println(board);
        //System.out.println("     possibles     ");
        Board newBoard = new Board(board);
        for(Move move : legalMoves) {
            newBoard.makeMove(move, myPlayer);
            //System.out.println("--------------------");
            //System.out.println("With move :" + move);
            //System.out.println("New board\n" + newBoard);
            // Check if depth - 1 should be here or not
            double val = minValue(newBoard, alpha, beta, depth - 1);
            //System.out.println("Score for this move would be:" + val);
            //bestVal = Math.max(bestVal, val);
            if(val > bestVal) {
                //System.out.println("Making this the best move");
                bestVal = val;
                //System.out.println("With move :" + move);
                //System.out.println(val);
                bestMove = move;
            }
            newBoard.undoMove(move, myPlayer);
            if(bestVal >= beta) {
                return bestMove;
            }
            alpha = Math.max(alpha, bestVal);
            //System.out.println("--------------------");
        }
        //System.out.println("++++++++++++++++++++");
        return bestMove;
    }

    private double maxValue(Board board, double alpha, double beta, int depth) {

        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.myPlayer);
        if(depth == 0 || isTerminalState(board)) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer, (depth + 1)/2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
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

    private double minValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Min called");
        //System.out.println(board);
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.otherPlayer);

        if(depth == 0 || isTerminalState(board)) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer, depth/2);
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
