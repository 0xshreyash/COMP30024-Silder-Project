package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.agent.Agent;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Scorer;
import com.teammaxine.board.helpers.Vector2;
import com.teammaxine.board.elements.Cell;

import java.util.ArrayList;
import java.util.HashMap;

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
        Move bestMove = null;

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
        HashMap<Vector2, Cell> cells = board.getCellsOfType(currPlayer);
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(currPlayer);
        Move bestMove = null;
        double bestVal = Double.NEGATIVE_INFINITY;
        for(Move move : legalMoves) {
            Board newBoard = new Board(board);
            simulateMove(move, newBoard, myPlayer);
            // Check if depth - 1 should be here or not
            double val = minValue(newBoard, alpha, beta, depth - 1);
            bestVal = Math.max(bestVal, val); 
            if(bestVal == val)
                bestMove = move;
        }
        return bestMove;
    }

    private double maxValue(Board board, double alpha, double beta, int depth) {
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.myPlayer);
        if(depth == 0 || legalMoves.isEmpty())
            return this.scorer.scoreBoard(board, myPlayer);
        double bestVal = Double.NEGATIVE_INFINITY;
        for(Move move : legalMoves) {
           Board newBoard = new Board(board);
           simulateMove(move, newBoard, myPlayer);
           bestVal = Math.max(bestVal, minValue(newBoard, alpha, beta, depth - 1));
           if (bestVal >= beta)
               return bestVal;
        }
        return bestVal;
    }

    private double minValue(Board board, double alpha, double beta, int depth) {
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(this.otherPlayer);
        if(depth == 0 || legalMoves.isEmpty())
            return this.scorer.scoreBoard(board, myPlayer);
        double bestVal = Double.POSITIVE_INFINITY;
        for(Move move : legalMoves) {
            Board newBoard = new Board(board);
            simulateMove(move, newBoard, otherPlayer);
            bestVal = Math.min(bestVal, maxValue(newBoard, alpha, beta, depth - 1));
            if(bestVal <= alpha)
                return bestVal;
        }
        return bestVal;
    }

    /**
     * Change the board according to how the move would change the board in order to
     * allow us to recurse down the tree.
     * @param move The move to be made on the new Board
     * @param newBoard Copy of the current board to be modified
     * @param player The type of player to be moved
     */
    private void simulateMove(Move move, Board newBoard, char player) {
        System.out.println(move.i + " " + move.j);
        if (move.d == Move.Direction.LEFT) {
            newBoard.changeCellValue(move.j, move.i - 1, player);
        } else if (move.d == Move.Direction.RIGHT && move.i != newBoard.getSize() - 1) {
            newBoard.changeCellValue(move.j, move.i + 1, player);
        } else if (move.d == Move.Direction.UP && move.j != newBoard.getSize() - 1) {
            newBoard.changeCellValue(move.j + 1 , move.i, player);
        } else if (move.d == Move.Direction.DOWN) {
            newBoard.changeCellValue(move.j - 1, move.i, player);
        }
    }

}
