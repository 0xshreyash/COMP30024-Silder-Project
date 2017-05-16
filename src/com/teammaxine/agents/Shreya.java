package com.teammaxine.agents;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.scorers.BlockingScorer;
import com.teammaxine.board.scorers.EndGameScorer;
import com.teammaxine.strategies.AlphaBetaBlocking;
import com.teammaxine.strategies.AlphaBetaEnd;
import com.teammaxine.strategies.AlphaBetaGreedy;
import com.teammaxine.strategies.Strategy;


/**
 * Created by noxm on 17/04/17.
 * our main ai <3
 */
public class Shreya extends Agent {
    private int turns = 0;

    @Override
    public Move move() {
        int depth;
        int boardSize = getMyBoard().getSize();
        int blockingTurns = (boardSize) * (boardSize - 1);

        // adjust depth with pre-defined values
        if (boardSize == 5 && turns % 10 == 0)
            depth = 11;
        else if(boardSize == 5)
            depth = 10;
        else if (boardSize == 6 && turns % 4 == 0)
            depth = 10;
        else if(boardSize == 6)
            depth = 9;
        else if (boardSize == 7 && turns < 14)
            depth = 9;
        else if (boardSize == 7)
            depth = 8;
        else
            depth = 8;

        Move toMake;
        char player = this.getPlayer();
        Board board = this.getMyBoard();
        Strategy myStrategy;

        // swap strategy according to the situation
        if(turns >= blockingTurns) {
            EndGameScorer endGameScorer = new EndGameScorer(player, board);
            myStrategy = new AlphaBetaEnd(player, endGameScorer, depth);

        } else if ((turns > (boardSize - 1)) || boardSize == 5) {
            BlockingScorer bscorer = new BlockingScorer(player, board);
            myStrategy = new AlphaBetaBlocking(player, bscorer, depth);

        } else {
            BlockingScorer bscorer = new BlockingScorer(player, board);
            myStrategy = new AlphaBetaGreedy(player, bscorer);
            depth -= 3;
        }

        toMake = myStrategy.findMove(board, depth);
        this.update(toMake, player);

        turns++;
        return toMake;
    }


}
