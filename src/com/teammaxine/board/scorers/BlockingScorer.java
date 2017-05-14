package com.teammaxine.board.scorers;

import com.sun.media.sound.SimpleSoundbank;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.helpers.Vector2;

import java.util.HashMap;
import java.util.SimpleTimeZone;

/**
 * Created by shreyashpatodia on 13/05/17.
 */
public class BlockingScorer extends Scorer{

    private char player;
    private static int distance_score = 100;
    private static int b_blocked_score = 150;
    private static int other_blocked_score = 60;
    private static int action_finish_value = 200;

    private static int block_bonus = 8;
    private static int smaller_block_bonus = 5;
    private static Board initialBoard = null;

    public BlockingScorer(char player, Board initialBoard) {
        this.player = player;
        this.initialBoard = initialBoard;
    }
    public double scoreBoard(Board b, char currentPlayer, int movesLeft)
    {
        int boardSize = b.getSize();
        double score = 0;
        int verticalDist = 0;
        int horizontalDist = 0;
        int numHCells = b.getHorizontal().getSize();
        int numVCells = b.getVertical().getSize();
        int originalHCells = initialBoard.getHorizontal().getSize();
        int originalVCells = initialBoard.getVertical().getSize();
        // Find distance, the less the distance the better the score. Consider blocking
        // as well as straight distance from the goal.
        for (Cell c : b.getVertical().getMyCells().values()) {
            // Add the distance from the
            verticalDist += distance_score * (boardSize - c.getPos().getY());

            int col = c.getPos().getX();
            int cellRow = c.getPos().getY();

            for (int row = c.getPos().getY() + 1; row < boardSize; row++) {

                if (b.getCellValue(row, col) == Board.CELL_BLOCKED) {
                    verticalDist += b_blocked_score;
                    break;
                }
                if ((b.getCellValue(row, col) == Board.CELL_HORIZONTAL)) {
                    // Better to have one of the higher column ones block than the other ones
                    verticalDist += other_blocked_score + block_bonus * col;
                    break;
                }
            }
        }


        for (Cell c : b.getHorizontal().getMyCells().values()) {

            horizontalDist += distance_score * (boardSize - c.getPos().getX());
            int row = c.getPos().getY();
            int cellColumn = c.getPos().getX();
            for (int col = c.getPos().getX() + 1; col < boardSize; col++) {
                if (b.getCellValue(row, col) == 'B') {
                    horizontalDist += b_blocked_score;
                    break;
                }
                if((b.getCellValue(row, col) ==  Board.CELL_VERTICAL)) {
                    horizontalDist += other_blocked_score - smaller_block_bonus * col;
                    break;
                }
            }
        }

        if(player == 'H') {
            score = verticalDist - horizontalDist;
            score += (originalHCells - numHCells)*action_finish_value - (originalVCells - numVCells)*action_finish_value;
        }
        else {
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
