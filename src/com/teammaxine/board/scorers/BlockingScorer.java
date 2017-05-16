/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;


/**
 * One of our final scorers, focuses on blocking the other
 * player.
 */
public class BlockingScorer extends Scorer{

    // our player
    private char player;

    // weights for each features
    private static int distance_score = 100;
    private static int b_blocked_score = 150;
    private static int other_blocked_score = 90;
    private static int action_finish_value = 60;

    // starting board's values
    private static int originalHCells;
    private static int originalVCells;

    private static int block_bonus = 12;

    public BlockingScorer(char player, Board initialBoard) {
        this.player = player;
        originalHCells = initialBoard.getHorizontal().getSize();
        originalVCells = initialBoard.getVertical().getSize();
    }

    public double scoreBoard(Board b, char currentPlayer, int movesLeft) {
        int boardSize = b.getSize();
        double score = 0;
        int verticalDist = 0;
        int horizontalDist = 0;
        int numHCells = b.getHorizontal().getSize();
        int numVCells = b.getVertical().getSize();

        // Find distance, the less the distance the better the score. Consider blocking
        // as well as straight distance from the goal.
        for (Cell c : b.getVertical().getMyCells().values()) {
            // Add the distance
            verticalDist += distance_score * (boardSize - c.getPos().getY());

            int col = c.getPos().getX();
            for (int row = c.getPos().getY() + 1; row < boardSize; row++) {
                if (b.getCellValue(row, col) == Board.CELL_BLOCKED) {
                    verticalDist += b_blocked_score;
                    break;
                }

                if ((b.getCellValue(row, col) == Board.CELL_HORIZONTAL)) {
                    // Worse to be blocked at higher columns than at lower ones, because
                    // we have less places to go to, and also that we are closer to the
                    // end and bad moves closer to the end will cause a loss.
                    verticalDist += other_blocked_score + block_bonus * col;
                    break;
                }
            }
        }

        // do the same for horizontal
        for (Cell c : b.getHorizontal().getMyCells().values()) {
            horizontalDist += distance_score * (boardSize - c.getPos().getX());

            int row = c.getPos().getY();
            for (int col = c.getPos().getX() + 1; col < boardSize; col++) {
                if (b.getCellValue(row, col) == Board.CELL_BLOCKED) {
                    horizontalDist += b_blocked_score;
                    break;
                }

                if((b.getCellValue(row, col) ==  Board.CELL_VERTICAL)) {
                    horizontalDist += other_blocked_score + block_bonus * row;
                    break;
                }
            }
        }

        if(player == 'H') {
            score = verticalDist - horizontalDist;
            // Check how many cells were taken off the board and update score accordingly.
            score += (originalHCells - numHCells)*action_finish_value - (originalVCells - numVCells)*action_finish_value;
        } else {
            score = horizontalDist - verticalDist;
            score +=(originalVCells - numVCells)*action_finish_value - (originalHCells - numHCells)*action_finish_value;
        }

        // The other player will probably move closer to the goal if it's their turn.
        if (player != currentPlayer) {
            score -= distance_score;
        }

        return score;
    }
}
