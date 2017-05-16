/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.agents;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.board.scorers.Scorer;
import com.teammaxine.strategies.MonteCarlo;
import com.teammaxine.strategies.Strategy;

/**
 * AI that uses Monte-Carlo tree search, the nature of the
 * search allowed us to come up wit the idea of using a
 * greedy strategy at the start of the game.
 */
public class Xena extends Agent {
    private Scorer scorer = null;
    private Strategy myStrategy = null;

    public void setScorer(Scorer s) {
        this.scorer = s;
    }

    public Scorer getScorer() {
        return scorer;
    }

    @Override
    public Move move() {
        if(this.scorer == null)
            scorer = new MonteCarloScorer();

        if(myStrategy == null)
            myStrategy = new MonteCarlo(getPlayer(), scorer);

        Move toMake = myStrategy.findMove(this.getMyBoard(), 500);
        this.update(toMake, this.getPlayer());

        return toMake;
    }
}
