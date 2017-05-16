package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;

/**
 * Created by shreyashpatodia on 13/05/17.
 */
public class EndGameScorer extends Scorer{

    private char player;
    private static int distance_score = 200;
    private static int b_blocked_score = 250;
    private static int other_blocked_score = 100;
    private static int action_finish_value = 150;
    private static int originalHCells;
    private static int originalVCells;
    private static int sensibleLateralPenalty = -10;
    private static int badLateralPenalty = -30;

    private static int block_bonus = 30;

    public EndGameScorer(char player, Board initialBoard) {
        this.player = player;
        originalHCells = initialBoard.getHorizontal().getSize();
        originalVCells = initialBoard.getVertical().getSize();
    }
    public double scoreBoard(Board b, char currentPlayer, int sensibleLateralMoves, int badLateralMoves)
    {
        int boardSize = b.getSize();
        double score = 0;
        int verticalDist = 0;
        int horizontalDist = 0;
        int numHCells = b.getHorizontal().getSize();
        int numVCells = b.getVertical().getSize();
        // Find distance, the less the distance the better the score. Consider blocking
        // as well as straight distance from the goal.
        for (Cell c : b.getVertical().getMyCells().values()) {
            // Add the distance from the
            int col = c.getPos().getX();
            verticalDist += distance_score * (boardSize - c.getPos().getY());
            for (int row = c.getPos().getY() + 1; row < boardSize; row++) {

                if (b.getCellValue(row, col) == Board.CELL_BLOCKED) {
                    verticalDist += b_blocked_score;
                    break;
                }
                if ((b.getCellValue(row, col) == Board.CELL_HORIZONTAL)) {
                    // Better to have one of the higher column ones block than the other ones
                    verticalDist += other_blocked_score + block_bonus * col;

                }
            }
        }


        for (Cell c : b.getHorizontal().getMyCells().values()) {
            int row = c.getPos().getY();
            horizontalDist += distance_score * (boardSize - c.getPos().getX());
            for (int col = c.getPos().getX() + 1; col < boardSize; col++) {
                if (b.getCellValue(row, col) == 'B') {
                    horizontalDist += b_blocked_score;
                    break;
                }
                if((b.getCellValue(row, col) ==  Board.CELL_VERTICAL)) {
                    horizontalDist += other_blocked_score + block_bonus * row;

                }
            }
        }

        if(player == 'H') {
            score = verticalDist - horizontalDist;
            score += (originalHCells - numHCells)*action_finish_value - (originalVCells - numVCells)*action_finish_value;
            score += sensibleLateralMoves*sensibleLateralPenalty + badLateralMoves*badLateralPenalty;
        }
        else {
            score = horizontalDist - verticalDist;
            score +=(originalVCells - numVCells)*action_finish_value - (originalHCells - numHCells)*action_finish_value;
            score += sensibleLateralMoves*sensibleLateralPenalty + badLateralMoves*badLateralPenalty;
        }

        // The other player will probably move closer to the goal if it's their turn.
        if (player != currentPlayer) {
            score -= distance_score;
        }

        return score;
    }

}
