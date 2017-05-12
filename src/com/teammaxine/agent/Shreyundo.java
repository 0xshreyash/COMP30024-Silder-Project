package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.helpers.BetaScorer;
import com.teammaxine.board.helpers.Scorer;
import com.teammaxine.strategies.AlphaBetaOrdered;
import com.teammaxine.strategies.Strategy;
/**
 * Created by noxm on 17/04/17.
 * our main ai
 */
public class Shreyundo extends Agent {

    @Override
    public Move move() {
        Scorer scorer = new BetaScorer(this.getMyBoard());
        Strategy myStrategy = new AlphaBetaOrdered(this.getPlayer(), scorer);
        int depth = -1;
        if(this.getMyBoard().getSize() == 5)
            depth = 10;
        if(this.getMyBoard().getSize() == 6)
            depth = 9;
        if(this.getMyBoard().getSize() == 7)
            depth = 8;
        Move toMake = myStrategy.findMove(this.getMyBoard(), depth);
        this.update(toMake, this.getPlayer());
        return toMake;
    }
}
