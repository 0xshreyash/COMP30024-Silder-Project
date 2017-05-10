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
    @Override
    public Move move() {
        Strategy myStrategy = new MonteCarlo(getPlayer(), new MonteCarloScorer());

        Move toMake = myStrategy.findMove(this.getMyBoard(), 0);
        this.update(toMake, this.getPlayer());

        return toMake;
    }
}
