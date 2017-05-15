package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.BlockingScorer;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.board.scorers.MontyPythonScorer;
import com.teammaxine.board.scorers.Scorer;
import com.teammaxine.strategies.AlphaBetaBlocking;
import com.teammaxine.strategies.AlphaBetaGreedy;
import com.teammaxine.strategies.MonteCarlo;
import com.teammaxine.strategies.Strategy;


/**
 * Created by noxm on 17/04/17.
 * our main ai <3
 */
public class Shreya extends Agent {
    private boolean switched[] = {false, false};
    private int turns = 0;
    private Strategy myStrategy;

    public Shreya() {

    }

    @Override
    public Move move() {
        int depth = -1;
        int boardSize = getMyBoard().getSize();
        int alphaBetaTurns = (boardSize) * (boardSize - 1);

        if (this.getMyBoard().getSize() == 5)
            depth = 11;
        if (this.getMyBoard().getSize() == 6)
            depth = 10;
        if (this.getMyBoard().getSize() == 7)
            depth = 9;

        Move toMake;

        if (turns == 0) {
            BlockingScorer bscorer = new BlockingScorer(this.getPlayer(), this.getMyBoard());
            myStrategy = new AlphaBetaGreedy(this.getPlayer(), bscorer);
        }

        if ((turns > getMyBoard().getSize() && !switched[0]) || this.getMyBoard().getSize() == 5) {
//            System.out.println("Switching to Alpha Beta Blocking");
            switched[0] = true;

            BlockingScorer bscorer = new BlockingScorer(this.getPlayer(), this.getMyBoard());
            myStrategy = new AlphaBetaBlocking(this.getPlayer(), bscorer, depth);
        }

        toMake = myStrategy.findMove(this.getMyBoard(), depth);
        this.update(toMake, this.getPlayer());

        turns++;
        return toMake;
    }
}
