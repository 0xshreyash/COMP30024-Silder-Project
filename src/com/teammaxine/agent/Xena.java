package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.board.scorers.Scorer;
import com.teammaxine.strategies.MonteCarlo;
import com.teammaxine.strategies.Strategy;

/**
 * Created by noxm on 10/05/17.
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
