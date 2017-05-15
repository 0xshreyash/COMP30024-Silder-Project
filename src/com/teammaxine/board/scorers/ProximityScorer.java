package com.teammaxine.board.scorers;

import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.BoardAgent;
import com.teammaxine.board.elements.Cell;

/**
 * Created by noxm on 13/05/17.
 */
public class ProximityScorer extends Scorer {
    public double scoreBoard(Board board, char playerPiece) {
        BoardAgent agent;
        if(playerPiece == 'H')
            agent = board.getHorizontal();
        else
            agent = board.getVertical();

        double score = 0;
        for(Cell myCell : agent.getMyCells().values()) {
            if(playerPiece == 'H')
                score += horizontalProximityScore(board, myCell);
            if(playerPiece == 'V')
                score += verticalProximityScore(board, myCell);
        }

        return score;
    }

    private double verticalProximityScore(Board board, Cell myCell) {
        int j = myCell.getPos().getY();
        int i = myCell.getPos().getX();
        double score = 0;

        // stuff below ie. im blocking them
        while(i >= 0) {
            Cell cell = board.getBoard()[i][j];
            if(cell.getValue() == 'H')
                score += 1;
            else if (cell.getValue() == 'B') {
                // there is B below, no point blocking this col
                score = 0;
                break;
            }
            i--;
        }

        return score;
    }

    private double horizontalProximityScore(Board board, Cell myCell) {
        int j = myCell.getPos().getY();
        int i = myCell.getPos().getX();
        double score = 0;

        // stuff below ie. im blocking them
        while(j >= 0) {
            Cell cell = board.getBoard()[i][j];
            if(cell.getValue() == 'V')
                score += 1;
            else if (cell.getValue() == 'B') {
                // there is B below, no point blocking this col
                score = 0;
                break;
            }
            j--;
        }

        return score;
    }
}
