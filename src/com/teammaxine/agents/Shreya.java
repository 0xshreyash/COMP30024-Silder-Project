/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

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
 * Our main AI, uses three different versions of Alpha beta pruning
 * in order to achieve our goal of playing the best game of Slider
 * possible.
 */
public class Shreya extends Agent {
    private int turns = 0;

    @Override
    public Move move() {
        int depth;
        int boardSize = getMyBoard().getSize();
        int blockingTurns = (boardSize) * (boardSize - 1);

        // adjust depth with pre-defined values, we change depth
        // so that we can go deeper sometimes in the game rather
        // than visiting the same depth over and over again. The
        // overhead still stays within the limits of the game and
        // gives us a better peek at the future.
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

        /* In the end our Scorer is focused on winning the game and also,
         * tries to penalise lateral moves. See comments.txt for explanation.
         */
        if(turns >= blockingTurns) {
            EndGameScorer endGameScorer = new EndGameScorer(player, board);
            myStrategy = new AlphaBetaEnd(player, endGameScorer, depth);

        }
        /* In the middle game we try to block the other player, and focus on
         * blocking rather than taking our pieces off the board.
         */
        else if ((turns > (boardSize - 1)) || boardSize == 5) {
            BlockingScorer bscorer = new BlockingScorer(player, board);
            myStrategy = new AlphaBetaBlocking(player, bscorer, depth);

        }
        /* Be greedy at the start of the game and take only optimistic moves
         */
        else {
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
