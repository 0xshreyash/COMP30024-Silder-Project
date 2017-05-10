package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Scorer;
import java.util.ArrayList;

/**
 * Created by shreyashpatodia on 05/05/17.
 */
public class AlphaBeta implements Strategy {

    private char myPlayer;
    private char otherPlayer;
    private Scorer scorer;

    public AlphaBeta(char player, Scorer scorer) {
        this.myPlayer = player;
        this.otherPlayer = this.myPlayer == Board.CELL_HORIZONTAL?
                Board.CELL_HORIZONTAL: Board.CELL_VERTICAL;
        this.scorer = scorer;
    }

    public Move findMove(Board board, int depth) {
        Move bestMove = alphaBetaSearch(depth, board, myPlayer,
                                            Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        //alphaBetaSearch(depth, board, this.player);
        return bestMove;
    }

    /**
     * Performs alpha beta search recursively
     * @param depth
     * @param currPlayer the player with the current chance
     * @return
     */
    private Move alphaBetaSearch(int depth, Board board, char currPlayer, double alpha, double beta) {
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(currPlayer);
        Move bestMove = null;
        double bestVal = Double.NEGATIVE_INFINITY;
        for(Move move : legalMoves) {
            Board newBoard = new Board(board);
            //System.out.println(move);
            newBoard.makeMove(move, myPlayer);

            //System.out.println("New board\n" + newBoard);
            // Check if depth - 1 should be here or not
            double val = minValue(newBoard, alpha, beta, depth - 1);
            bestVal = Math.max(bestVal, val);
            if(bestVal == val) {
                System.out.println("Updating bestVal");
                System.out.println("New score :" + bestVal);
                System.out.println(move);
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double maxValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Max called");
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.myPlayer);
        if(depth == 0 || isTerminalState(board))
            // Return utility when at our worst depth.
            return this.scorer.scoreBoard(board, myPlayer);
        double bestVal = Double.NEGATIVE_INFINITY;
        for(Move move : legalMoves) {
           Board newBoard = new Board(board);
           newBoard.makeMove(move, myPlayer);
           bestVal = Math.max(bestVal, minValue(newBoard, alpha, beta, depth - 1));
           alpha = Math.max(bestVal, alpha);
           if (beta <= alpha)
               break;
        }
        return bestVal;
    }

    private double minValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Min called");
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.otherPlayer);
        if(depth == 0 || isTerminalState(board))
            return this.scorer.scoreBoard(board, myPlayer);
        double bestVal = Double.POSITIVE_INFINITY;
        for(Move move : legalMoves) {
            Board newBoard = new Board(board);
            newBoard.makeMove(move, otherPlayer);
            bestVal = Math.min(bestVal, maxValue(newBoard, alpha, beta, depth - 1));
            beta = Math.min(bestVal, beta);
            if(beta <= alpha)
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
