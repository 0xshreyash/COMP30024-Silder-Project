package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Scorer;
import com.teammaxine.board.helpers.Vector2;
import com.teammaxine.board.elements.Cell;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The idea with doing minimax is to find a contingent strategy.
 * The optimal strategy leads to outcomes at least as good as any
 * other strategy when one is playing an infallible opponent.
 * Terrminology:
 * move -> move by both players
 * ply -> half-move, "move" by just one player
 */
public class Minimax implements Strategy{

    private char myPlayer;
    private char otherPlayer;
    private Scorer scorer;

    /**
     * Sets the player and the scoring function for the minimax
     * @param myPlayer the player which is the maximizing player
     * @param scorer   the scoring function for evaluation of minimax
     */
    public Minimax(char myPlayer, Scorer scorer) {
        this.myPlayer = myPlayer;
        this.scorer = scorer;
        this.otherPlayer = myPlayer == Board.CELL_HORIZONTAL?Board.CELL_VERTICAL:Board.CELL_HORIZONTAL;
    }

    /**
     * The function we have to implement to implement strategy
     * @param currentBoard
     * @param depth
     * @return
     */
    public Move findMove(Board currentBoard, int depth) {

        ArrayList<? extends Move> legalMoves = currentBoard.getLegalMoves(myPlayer);
        Move bestMove = null;
        double maxValue = Double.NEGATIVE_INFINITY;
        for(Move move: legalMoves) {
            Board newBoard = new Board(currentBoard);
            simulateMove(move, newBoard, myPlayer);
            double val = minimax(newBoard, depth, otherPlayer);
            maxValue = Math.max(val, maxValue);
            if(maxValue == val)
                bestMove = move;
        }

        return bestMove;
    }
    /**
     *
     * @param board
     * @param depth
     * @param player
     * @return
     */
    private double minimax(Board board, int depth, char player) {

        double bestScore = (player == myPlayer)?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY;
        ArrayList<? extends Move> moves = board.getLegalMoves(player);
        if(depth == 0) // The board aligns with the end game policy.
            return scorer.scoreBoard(board, player);
        for (Move move : moves) {
            Board newBoard = new Board(board);
            simulateMove(move, newBoard, player);
            double val = minimax(newBoard, depth - 1, otherPlayer);
            bestScore = (player == myPlayer)?Math.max(bestScore, val):Math.min(bestScore, val);
        }
        return bestScore;
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
