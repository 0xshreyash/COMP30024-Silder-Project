package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.helpers.BetaScorer;
import com.teammaxine.board.helpers.Scorer;
import com.teammaxine.strategies.AlphaBeta;
import com.teammaxine.strategies.Strategy;
/**
 * Created by noxm on 17/04/17.
 * our main ai
 */
public class Shreya extends Agent {

    @Override
    public Move move() {
        Scorer scorer = new BetaScorer(this.getMyBoard());

        /*System.out.println("My board before the move:");
        System.out.println(this.getMyBoard());
        for(Cell cell : this.getMyBoard().getCellsOfType(this.getPlayer()).values()) {
            System.out.println(cell.getPos());
        }
        for(Move move : this.getMyBoard().getLegalMoves(this.getPlayer())) {
            System.out.println(move);
        }*/
        Strategy myStrategy = new AlphaBeta(this.getPlayer(), scorer);
        Move toMake = myStrategy.findMove(this.getMyBoard(), 9);
        this.update(toMake, this.getPlayer());
        /*System.out.println("My board after move :");
        System.out.println(this.getMyBoard());*/
        return toMake;
    }
}
