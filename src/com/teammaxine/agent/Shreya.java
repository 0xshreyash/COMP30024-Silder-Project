package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.BlockingScorer;
import com.teammaxine.strategies.AlphaBetaSorted;
import com.teammaxine.strategies.Strategy;
/**
 * Created by noxm on 17/04/17.
 * our main ai
 */
public class Shreya extends Agent {

    @Override
    public Move move() {
        //Scorer scorer = new BetaScorer(this.getMyBoard());

        /*System.out.println("My board before the move:");
        System.out.println(this.getMyBoard());
        for(Cell cell : this.getMyBoard().getCellsOfType(this.getPlayer()).values()) {
            System.out.println(cell.getPos());
        }
        for(Move move : this.getMyBoard().getLegalMoves(this.getPlayer())) {
            System.out.println(move);
        }*/
        int depth = -1;
        if(this.getMyBoard().getSize() == 5)
            depth = 8;
        if(this.getMyBoard().getSize() == 6)
            depth = 7;
        if(this.getMyBoard().getSize() == 7)
            depth = 6;
        BlockingScorer scorer = new BlockingScorer(this.getPlayer());
        Strategy myStrategy = new AlphaBetaSorted(this.getPlayer(), scorer, depth);
        Move toMake = myStrategy.findMove(this.getMyBoard(), depth);
        this.update(toMake, this.getPlayer());
        return toMake;
    }
}
