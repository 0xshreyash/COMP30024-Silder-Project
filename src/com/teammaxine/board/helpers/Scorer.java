package com.teammaxine.board.helpers;

import aiproj.slider.Move;
import com.teammaxine.board.actions.AgentAction;
import com.teammaxine.board.elements.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by noxm on 17/04/17.
 */
public class Scorer {
    // score += cell property * this
    private static final boolean showDebug = false;
    private static final double DISTANCE_SCORE = 10;
    private static final double COUNT_SCORE = 8;
    private static final double BLOCK_SCORE = 9;
    private static final double MOVE_SIDE_SCORE = 1;
    private static final double MOVE_FORWARD_SCORE = 2;

    public static double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if(playerIsHorizontal) {
            return scoreBoardHorizontal(board);
        } else {
            return scoreBoardVertical(board);
        }
    }

    private static double scoreBoardVertical(Board board) {
        double score = 0;

        int maxDist = board.getSize() - 1;
        int count = board.getVertical().getMyCells().size();
        score += DISTANCE_SCORE * (maxDist * count - sumVerticalDistance(board));
        score += MOVE_SIDE_SCORE * sumVerticalSideMoves(board);
        score += MOVE_FORWARD_SCORE * sumVerticalForwardMoves(board);
        score += COUNT_SCORE * (board.getSize() - sumVerticalPieces(board));
        score += BLOCK_SCORE * (board.getSize() - sumHorizontalForwardMoves(board));

        return score;
    }

    private static double scoreBoardHorizontal(Board board) {
        double score = 0;

        int maxDist = board.getSize() - 1;
        int count = board.getVertical().getMyCells().size();
        score += DISTANCE_SCORE * (maxDist * count - sumHorizontalDistance(board));
        score += MOVE_SIDE_SCORE * sumHorizontalSideMoves(board);
        score += MOVE_FORWARD_SCORE * sumHorizontalForwardMoves(board);
        score += COUNT_SCORE * (board.getSize() - sumHorizontalPieces(board));
        score += BLOCK_SCORE * (board.getSize() - sumVerticalForwardMoves(board));

        return score;
    }

    private static int sumVerticalForwardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.UP)? 1 : 0;
        }

        if(showDebug)
            System.out.println("Vertical Forward " + sum);

        return sum;
    }

    private static int sumVerticalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.LEFT || a.d == Move.Direction.RIGHT)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Vertical Side " + sum);

        return sum;
    }

    private static int sumHorizontalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.UP || a.d == Move.Direction.DOWN)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Horizontal Side " + sum);

        return sum;
    }

    private static int sumHorizontalForwardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.RIGHT)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Horizontal Forward " + sum);

        return sum;
    }

    private static int sumHorizontalDistance(Board b) {
        int sum = 0;

        HashMap<Vector2, Cell> cellHash = b.getHorizontal().getMyCells();
        ArrayList<Cell> cells = new ArrayList<>(cellHash.values());
        for(Cell c : cells) {
            sum += b.getSize() - c.getPos().getX();
        }
        if(showDebug)
            System.out.println("Horizontal Distance " + sum);

        return sum;
    }

    private static int sumVerticalDistance(Board b) {
        int sum = 0;

        HashMap<Vector2, Cell> cellHash = b.getVertical().getMyCells();
        ArrayList<Cell> cells = new ArrayList<>(cellHash.values());
        for(Cell c : cells) {
            sum += b.getSize() - c.getPos().getY();
        }
        if(showDebug)
            System.out.println("Vertical Distance " + sum);

        return sum;
    }

    private static int sumVerticalPieces(Board board) {
        return board.getVertical().getMyCells().size();
    }

    private static int sumHorizontalPieces(Board board) {
        return board.getHorizontal().getMyCells().size();
    }

}
