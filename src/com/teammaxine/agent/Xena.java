package com.teammaxine.agent;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import com.teammaxine.board.helpers.MonteCarloScorer;
import com.teammaxine.board.helpers.Scorer;
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

    @Override
    public Move move() {
        Scorer scorer;

        if(s == null) {
            scorer = new MonteCarloScorer();
        } else {
            scorer = s;
        }

        Strategy myStrategy = new MonteCarlo(getPlayer(), scorer);

        Move toMake = myStrategy.findMove(this.getMyBoard(), 0);
        this.update(toMake, this.getPlayer());

        return toMake;
    }
}
