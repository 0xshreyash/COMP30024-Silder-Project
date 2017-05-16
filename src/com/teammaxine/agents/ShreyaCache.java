/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.agents;

import aiproj.slider.Move;
import com.teammaxine.board.scorers.BlockingScorer;
import com.teammaxine.strategies.AlphaBetaCache;


/**
 * ShreyaCache uses a LRU cache to store states of the board,
 * the key of the cache is a compressed version of the board
 * and the value is represented by an object of the state class.
 * Does not work well due to memory limits set during run-time
 * for the assignment.
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
        /* Working similar to Shreya */
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
