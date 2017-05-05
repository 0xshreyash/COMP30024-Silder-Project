package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.agent.Agent;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.helpers.Vector2;
import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shreyashpatodia on 05/05/17.
 */
public abstract class AlphaBeta implements Strategy {

    private char player;
    private char otherPlayer;
    private Agent agent;
    public AlphaBeta(char player, Agent agent) {
        this.player = player;
        this.agent = agent;
        this.otherPlayer = this.player == Board.CELL_HORIZONTAL?
                Board.CELL_HORIZONTAL: Board.CELL_VERTICAL;
    }

    public Move findMove(Board board) {
        int depth = 5;
        minimax(depth, board, this.player);
        return null;
    }

    /**
     *
     * @param depth
     * @param currPlayer the player with the current chance
     * @return
     */
    private Move[] minimax(int depth, Board board, char currPlayer) {

        HashMap<Vector2, Cell> cells = board.getCellsOfType(currPlayer);
        ArrayList<Move> legalMoves = board.getLegalMoves()
        return null;
    }
}
