package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.*;

/**
 * Simple scorer for testing purposes.
 */
public class AlphaScorer extends Scorer {
    // score += cell property * this

    // initialBoard is the board we start evaluating from.
    private Board initialBoard;
    double distance_change_score = 20;
    double lateral_move_penalty = - 90;
    double action_finish_value = 10;
    // Scores to evaluate blockedness
    double b_blocked_score = -20;
    int moves;

    public AlphaScorer(Board initialBoard, int depth)
    {
        this.initialBoard = initialBoard;
        this.lateral_move_penalty = (initialBoard.getSize() + 1) * -10;
        //this.action_finish_value = (initialBoard.getSize()) * 10;
        this.moves = (depth + 1)/2;
    }

    public double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if (playerIsHorizontal) {
            return scoreBoardHorizontal(board);
        } else {
            return scoreBoardVertical(board);
        }
    }

    public double scoreBoard(Board board, char playerPiece, int movesLeft) {
        boolean playerIsHorizontal = playerPiece == 'H';

        if (playerIsHorizontal) {
            return scoreBoardHorizontal(board, movesLeft);
        } else {
            return scoreBoardVertical(board, movesLeft);
        }
    }

    double scoreBoardVertical(Board board, int movesLeft) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getVertical().getMyCells().size();
        int countBefore = initialBoard.getVertical().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumVerticalDistance(this.initialBoard);
        int distanceNewBoard = sumVerticalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;
        //System.out.println(score);
        // Distance change should be the number of forward moves made.
        score += lateral_move_penalty * (moves - distanceChange - movesLeft);
        //System.out.println(score);

        score += action_finish_value * (countBefore - count);
        //System.out.println(score);
        double oldBlockedness = verticalMagnitudeOfBlockedness(initialBoard);
        double changeBlockness = verticalMagnitudeOfBlockedness(board) - oldBlockedness;
        score += changeBlockness;
        return score;
    }

    double scoreBoardHorizontal(Board board, int movesLeft) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getHorizontal().getMyCells().size();
        int countBefore = initialBoard.getHorizontal().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumHorizontalDistance(this.initialBoard);
        int distanceNewBoard = sumHorizontalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;
        score += lateral_move_penalty * (moves - distanceChange - movesLeft);
        score += action_finish_value * (countBefore - count);
        double oldBlockedness = horizontalMagnitudeOfBlockedness(initialBoard);
        double changeBlockness = horizontalMagnitudeOfBlockedness(board) - oldBlockedness;
        score += changeBlockness;
        return score;
    }


    double horizontalRowSum(Board board) {
        double sum = 0;
        for(Cell c : board.getHorizontal().getMyCells().values())
            sum += c.getPos().getY();
        return sum;
    }

    double verticalColumnSum(Board board) {
        double sum = 0;
        for(Cell c : board.getVertical().getMyCells().values())
            sum += c.getPos().getX();
        return sum;
    }

    int horizontalMagnitudeOfBlockedness(Board b) {
        int magnitude = 0;
        Cell board[][] = b.getBoard();
        for(Cell c : b.getHorizontal().getMyCells().values()) {
            for (int i = c.getPos().getX() + 1; i < b.getSize(); i++) {
                char cellValue = board[c.getPos().getY()][i].getValue();
                if (cellValue == Board.CELL_BLOCKED) {
                    magnitude += b_blocked_score;
                }
            }
        }
        return magnitude;
    }

    int verticalMagnitudeOfBlockedness(Board b) {
        int magnitude = 0;
        Cell board[][] = b.getBoard();
        for(Cell c : b.getVertical().getMyCells().values()) {
            for (int i = c.getPos().getY() + 1; i < b.getSize(); i++) {
                char cellValue = board[i][c.getPos().getX()].getValue();
                if (cellValue == Board.CELL_BLOCKED) {
                    magnitude += b_blocked_score;
                }
            }
        }
        //System.out.println(magnitude);
        return magnitude;
    }

}
