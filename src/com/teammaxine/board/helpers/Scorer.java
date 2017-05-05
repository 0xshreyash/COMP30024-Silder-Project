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
    private static final double DISTANCE_SCORE = -1;
    private static final double MOVE_SIDE_SCORE = 1;
    private static final double MOVE_FORWARD_SCORE = 2;

    // score += opponent cell property * this
    private static final double OPPONENT_DISTANCE_SCORE = 1;
    private static final double OPPONENT_MOVE_SIDE_SCORE = -1;
    private static final double OPPONENT_MOVE_FORWARD_SCORE = -2;

    public static double scoreBoard(Board board, char playerPiece) {
        double score = 0;
        boolean playerIsHorizontal = playerPiece == 'H';
        BoardAgent player, opponent;

        if(playerIsHorizontal) {
            // score board for h
            player = board.getHorizontal();
            opponent = board.getVertical();

        } else {
            // score board for v
            player = board.getVertical();
            opponent = board.getHorizontal();
        }

        if(playerIsHorizontal) {
            // distance score
            score += DISTANCE_SCORE * sumHorizontalDistance(board);
            score += OPPONENT_DISTANCE_SCORE * sumVerticalDistance(board);

            // move score
            score += MOVE_SIDE_SCORE * sumHorizontalSideMoves(board);
            score += MOVE_FORWARD_SCORE * sumHorizontalForwardMoves(board);

            score += OPPONENT_MOVE_SIDE_SCORE * sumVerticalSideMoves(board);
            score += OPPONENT_MOVE_FORWARD_SCORE * sumVerticalFowardMoves(board);

        } else {
            // distance score
            score += DISTANCE_SCORE * sumVerticalDistance(board);

            // move score
            score += MOVE_SIDE_SCORE * sumVerticalSideMoves(board);
            score += MOVE_FORWARD_SCORE * sumVerticalFowardMoves(board);

            score += OPPONENT_MOVE_SIDE_SCORE * sumHorizontalSideMoves(board);
            score += OPPONENT_MOVE_FORWARD_SCORE * sumHorizontalForwardMoves(board);
        }

        return score;
    }

    private static int sumVerticalFowardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            if(a.getType() == AgentAction.ActionType.MOVE) {
                sum += (a.d == Move.Direction.UP)? 1 : 0;
            }
        }

        System.out.println("Vertical Forward " + sum);

        return sum;
    }

    private static int sumVerticalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            if(a.getType() == AgentAction.ActionType.MOVE) {
                sum += (a.d == Move.Direction.LEFT || a.d == Move.Direction.RIGHT)? 1 : 0;
            }
        }
        System.out.println("Vertical Side " + sum);

        return sum;
    }

    private static int sumHorizontalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            if(a.getType() == AgentAction.ActionType.MOVE) {
                sum += (a.d == Move.Direction.UP || a.d == Move.Direction.DOWN)? 1 : 0;
            }
        }
        System.out.println("Horizontal Side " + sum);

        return sum;
    }

    private static int sumHorizontalForwardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            if(a.getType() == AgentAction.ActionType.MOVE) {
                sum += (a.d == Move.Direction.RIGHT)? 1 : 0;
            }
        }
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
        System.out.println("Vertical Distance " + sum);

        return sum;
    }
}
