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
    private boolean showDebug = false;

    double distance_score = 10;
    double count_score = 5;
    double block_score = 10;
    double move_side_score = 0;
    double move_forward_score = 3;

    public Scorer() {

    }

    Scorer(double dist, double count, double block, double side, double forward) {
        distance_score = dist;
        count_score = count;
        block_score = block;
        move_side_score = side;
        move_forward_score = forward;
    }

    public double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if(playerIsHorizontal) {
            return scoreBoardHorizontal(board);
        } else {
            return scoreBoardVertical(board);
        }
    }

     double scoreBoardVertical(Board board) {
        double score = 0;

        int maxDist = board.getSize() - 1;
        int count = board.getVertical().getMyCells().size();
        score += distance_score * (maxDist * count - sumVerticalDistance(board));
        score += move_side_score * sumVerticalSideMoves(board);
        score += move_forward_score * sumVerticalForwardMoves(board);
        score += count_score * (board.getSize() - sumVerticalPieces(board));
        score += block_score * (board.getHorizontal().getMyCells().size() - sumHorizontalForwardMoves(board));

        return score;
    }

    double scoreBoardHorizontal(Board board) {
        double score = 0;

        int maxDist = board.getSize() - 1;
        int count = board.getVertical().getMyCells().size();
        score += distance_score * (maxDist * count - sumHorizontalDistance(board));
        score += move_side_score * sumHorizontalSideMoves(board);
        score += move_forward_score * sumHorizontalForwardMoves(board);
        score += count_score * (board.getSize() - sumHorizontalPieces(board));
        score += block_score * (board.getVertical().getMyCells().size() - sumHorizontalForwardMoves(board));

        return score;
    }

    final int sumVerticalForwardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.UP)? 1 : 0;
        }

        if(showDebug)
            System.out.println("Vertical Forward " + sum);

        return sum;
    }

    final int sumVerticalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getVertical().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.LEFT || a.d == Move.Direction.RIGHT)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Vertical Side " + sum);

        return sum;
    }

    final int sumHorizontalSideMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.UP || a.d == Move.Direction.DOWN)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Horizontal Side " + sum);

        return sum;
    }

    final int sumHorizontalForwardMoves(Board b) {
        int sum = 0;

        ArrayList<AgentAction> moves = b.getHorizontal().getLegalMoves();
        for(AgentAction a : moves) {
            sum += (a.d == Move.Direction.RIGHT)? 1 : 0;
        }
        if(showDebug)
            System.out.println("Horizontal Forward " + sum);

        return sum;
    }

    final int sumHorizontalDistance(Board b) {
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

    final int sumVerticalDistance(Board b) {
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

    final int sumVerticalPieces(Board board) {
        return board.getVertical().getMyCells().size();
    }

    final int sumHorizontalPieces(Board board) {
        return board.getHorizontal().getMyCells().size();
    }
}
