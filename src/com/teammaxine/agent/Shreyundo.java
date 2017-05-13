package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.helpers.AlphaScorer;
import com.teammaxine.board.helpers.Scorer;
import com.teammaxine.strategies.AlphaBetaUndo;
import com.teammaxine.strategies.Strategy;
/**
 * Created by noxm on 17/04/17.
 * our main ai
 */
public class Shreyundo extends Agent {

    @Override
    public Move move() {

        int depth = -1;
        if(this.getMyBoard().getSize() == 5)
            depth = 11;
        if(this.getMyBoard().getSize() == 6)
            depth = 9;
        if(this.getMyBoard().getSize() == 7)
            depth = 8;
        AlphaScorer scorer = new AlphaScorer(this.getMyBoard(), depth);
        Strategy myStrategy = new AlphaBetaUndo(this.getPlayer(), scorer);
        Move toMake = myStrategy.findMove(this.getMyBoard(), depth);
        this.update(toMake, this.getPlayer());
        return toMake;
    }
}
