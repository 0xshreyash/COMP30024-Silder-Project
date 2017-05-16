package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.*;

/**
 * Taking a two-fold approach to scoring, not just scoring the final board but also
 * scoring the board
 */
public class BetaScorer extends Scorer {
    // score += cell property * this

    // initialBoard is the board we start evaluating from.
    private Board initialBoard;
    double distance_change_score = 15;

    // Scores to evaluate blockedness
    // Take this score off when blocked by B because,
    // B will never move, so we don't want to be in the
    // same line a
    double b_blocked_score = -6;

    // Take this score when blocked by other player, still
    // good to move forward in these scenarios since the other
    // player may move (especially if the the opponent does
    // not think about blocking, which I am assuming will be
    // the case most of the time/

    double other_blocked_score = -2.5;

    // Our piece will probably move forward anyways so, we
    // can be right behind it. This will happen every time there
    // is a B on the board.
    //double action_finish_score = 3;

    public BetaScorer(Board initialBoard) {
        this.initialBoard = initialBoard;
    }

    public double scoreBoard(Board board, char playerPiece) {
        boolean playerIsHorizontal = playerPiece == 'H';
        double totalScore = 0;
        if (playerIsHorizontal) {
            totalScore = scoreBoardHorizontal(board) - scoreBoardVertical(board);
            totalScore += horizontalBlockingValue(board) - horizontalBlockingValue(initialBoard);
        } else {
            totalScore = scoreBoardVertical(board) - scoreBoardHorizontal(board);
            totalScore += verticalBlockingValue(board) - verticalBlockingValue(initialBoard);
        }

        return totalScore;
    }

    double scoreBoardVertical(Board board) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getVertical().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumVerticalDistance(this.initialBoard);
        int distanceNewBoard = sumVerticalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;

        // Now checking if the new board is more blocked w.r.t to the player
        // or not.
        // More is better, blockedness is negative. Larger number (-3 > -5)
        // then the board is less blocked
        double oldBlockedness = verticalMagnitudeOfBlockedness(initialBoard);
        double changeBlockness = verticalMagnitudeOfBlockedness(board) - oldBlockedness;
        score += changeBlockness;

        return score;
    }

    double scoreBoardHorizontal(Board board) {
        double score = 0;

        int maxDist = board.getSize();
        int count = board.getHorizontal().getMyCells().size();

        // inital board distance should be more than newBoard distance
        int distanceInitialBoard = sumHorizontalDistance(this.initialBoard);
        int distanceNewBoard = sumHorizontalDistance(board);
        // Should be positive for a good set of moves.
        int distanceChange = distanceInitialBoard - distanceNewBoard;
        // Higher is better
        score += distanceChange * distance_change_score;
        double oldBlockedness = horizontalMagnitudeOfBlockedness(initialBoard);
        double changeBlockness = horizontalMagnitudeOfBlockedness(board) - oldBlockedness;
        score += changeBlockness;

        return score;
    }
    // Try adding blockness of our own peices as well.
    int horizontalMagnitudeOfBlockedness(Board b) {
        int magnitude = 0;
        Cell board[][] = b.getBoard();
        for(Cell c : b.getHorizontal().getMyCells().values()) {
            for (int i = c.getPos().getX() + 1; i < b.getSize(); i++) {
                char cellValue = board[c.getPos().getY()][i].getValue();
                /*
                System.out.println("------------");

                System.out.println(c.getPos());
                System.out.println(board[c.getPos().getY()][i].getPos());
                System.out.println(cellValue);
                System.out.println("------------");
                */
                if (cellValue == Board.CELL_BLOCKED) {
                    magnitude += b_blocked_score;
                }
                else if (cellValue == Board.CELL_VERTICAL) {

                    magnitude += other_blocked_score;
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
                else if (cellValue == Board.CELL_HORIZONTAL) {
                    magnitude += other_blocked_score;
                }

            }
        }
        //System.out.println(magnitude);
        return magnitude;
    }

    /**
     * Functions finds how valuable the board is w.r.t to blocking the other player,
     * the
     * @param b
     * @return
     */
    double horizontalBlockingValue(Board b) {
        double score = 0;
        for(Cell c : b.getHorizontal().getMyCells().values()) {
            for(int i = 0; i < c.getPos().getY() - 1; i++) {
                char value = b.getBoard()[i][c.getPos().getX()].getValue();
                if(value == Board.CELL_VERTICAL) {
                    score += block_score;
                }
            }
        }
        return score;
    }

    double verticalBlockingValue(Board b) {
        double score = 0;
        for(Cell c : b.getVertical().getMyCells().values()) {
            for(int i = 0; i < c.getPos().getX() - 1; i++) {
                char value = b.getBoard()[c.getPos().getY()][i].getValue();
                if(value == Board.CELL_HORIZONTAL) {
                    score += block_score;
                }
            }
        }
        return score;
    }




}
