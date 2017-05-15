package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;

public class EndGameScorer extends Scorer{

    private static char player;
    private static Board initialBoard = null;
    private static int distance_score = 100;
    private static int action_finish_score = 2000;
    private static int moves = 0;
    private static int lateral_move_penalty = 30000;

    public EndGameScorer(char player, Board initialBoard, int depth) {
        this.player = player;
        this.initialBoard = new Board(initialBoard);
        moves = (depth + 1)/2;
    }
    public double scoreBoard(Board b, char currentPlayer)
    {
        int boardSize = b.getSize();
        int currentH = b.getHorizontal().getMyCells().size();
        int currentV = b.getVertical().getMyCells().size();
        double horizontal_score = 0;
        double vertical_score = 0;

        for (Cell cell : b.getHorizontal().getMyCells().values()) {

            horizontal_score += cell.getPos().getX() * distance_score;
        }
        horizontal_score += (boardSize - 1 - currentH) * action_finish_score;

        for (Cell cell : b.getVertical().getMyCells().values()) {

            vertical_score+= cell.getPos().getY() * distance_score;
        }
        vertical_score+= (boardSize - 1 - currentV) * action_finish_score;

        if (this.player == Board.CELL_HORIZONTAL) {
            int originalHDistance = this.sumHorizontalDistance(initialBoard);
            int currentHDistance = this.sumHorizontalDistance(b);
            int hDistanceCovered = originalHDistance - currentHDistance;
            int hLateralMoves = moves - hDistanceCovered;
            return horizontal_score -lateral_move_penalty * hLateralMoves;
        }
        int originalVDistance = this.sumVerticalDistance(initialBoard);
        int currentVDistance = this.sumVerticalDistance(b);
        int vDistanceCovered = originalVDistance - currentVDistance;
        int vLateralMoves = moves - vDistanceCovered;
        return vertical_score - lateral_move_penalty * vLateralMoves;
    }

}
