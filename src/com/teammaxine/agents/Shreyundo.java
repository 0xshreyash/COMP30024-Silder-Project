/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.agents;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.AlphaScorer;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.strategies.AlphaBetaUndo;
import com.teammaxine.strategies.MonteCarlo;
import com.teammaxine.strategies.Strategy;

import java.util.Random;

/**
 * The first AI where we started to undo-moves rather than deep copy a board,
 * this AI allowed us to make our project much faster and thus, go deeper in
 * the search tree. This was due to the board being extremely big, and containing
 * a huge amount of information and using the same board allowed us to not have
 * the overhead of deep copy at multiple levels. For example,
 * Copy Board -> Copy Horizontal -> Copy each Vector and Copy each cell.
 */
public class Shreyundo extends Agent {
    private static AlphaScorer scorer = null;
    private static Strategy alphaBeta = null;
    private static Strategy monteCarlo = null;

    private static int turn = 0;
    private static int totalTurn = 0;
    private static Random random;

    @Override
    public Move move() {
        int depth = -1;
        if(this.getMyBoard().getSize() == 5)
            depth = 20;
        if(this.getMyBoard().getSize() == 6)
            depth = 9;
        if(this.getMyBoard().getSize() == 7)
            depth = 13;
        if(turn == 0) {
            random = new Random(System.currentTimeMillis());
            scorer = new AlphaScorer(this.getMyBoard(), depth, this.getPlayer());
            alphaBeta = new AlphaBetaUndo(this.getPlayer(), scorer);
            monteCarlo = new MonteCarlo(this.getPlayer(), new MonteCarloScorer());
            totalTurn = this.getMyBoard().getSize() * (this.getMyBoard().getSize() - 1);
        }

        turn++;

        Move toMake;

        //if(random.nextInt(100) < 50)
        //if(turn < totalTurn / 2)
            toMake = alphaBeta.findMove(this.getMyBoard(), depth);
        /*else
            toMake = monteCarlo.findMove(this.getMyBoard(), depth);
            */

        this.update(toMake, this.getPlayer());

        return toMake;
    }
}
