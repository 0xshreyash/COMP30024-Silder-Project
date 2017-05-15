package com.teammaxine.agent;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.BlockingScorer;
import com.teammaxine.strategies.AlphaBetaBlocking;
import com.teammaxine.strategies.AlphaBetaCache;


/**
 * Created by noxm on 17/04/17.
 * our main ai
 */
public class ShreyaCache extends Agent {
    private int turns = 0;
    private BlockingScorer scorer;
    private AlphaBetaCache myStrategy;

    public ShreyaCache() {

    }
    @Override
    public Move move() {
        turns++;
        /*System.out.println("My board before the move:");
        System.out.println(this.getMyBoard());
        for(Cell cell : this.getMyBoard().getCellsOfType(this.getPlayer()).values()) {
            System.out.println(cell.getPos());
        }
        for(Move move : this.getMyBoard().getLegalMoves(this.getPlayer())) {
            System.out.println(move);
        }*/
        int depth = -1;
        int boardSize = getMyBoard().getSize();
        int alphaBetaTurns = (boardSize)*(boardSize - 1);
        if(this.getMyBoard().getSize() == 5)
            depth = 7;                                                                                                                     ;
        if(this.getMyBoard().getSize() == 6)
            depth = 9;
        if(this.getMyBoard().getSize() == 7)
            depth = 8;
        Move toMake = null;
        if(scorer == null) {
            scorer = new BlockingScorer(this.getPlayer(), this.getMyBoard());
            myStrategy = new AlphaBetaCache(this.getPlayer(), scorer, depth, 100);
        }
        toMake = myStrategy.findMove(this.getMyBoard(), depth);
        this.update(toMake, this.getPlayer());
        turns++;
        return toMake;
    }
}
