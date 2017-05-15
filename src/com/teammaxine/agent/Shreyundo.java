package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.AlphaScorer;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.strategies.AlphaBetaUndo;
import com.teammaxine.strategies.MonteCarlo;
import com.teammaxine.strategies.Strategy;

import java.util.Random;

/**
 * Created by noxm on 17/04/17.
 * our main ai
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
