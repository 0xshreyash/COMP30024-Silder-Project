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
    private Scorer s = null;

    public void setScorer(Scorer s) {
        this.s = s;
    }

    public Scorer getScorer() {
        return s;
    }

    @Override
    public Move move() {
        Scorer scorer;

        if(s == null) {
            scorer = new MonteCarloScorer();
        } else {
            scorer = s;
        }

        Strategy myStrategy = new MonteCarlo(getPlayer(), scorer);

        Move toMake = myStrategy.findMove(this.getMyBoard(), 500);
        this.update(toMake, this.getPlayer());

        return toMake;
    }
}
