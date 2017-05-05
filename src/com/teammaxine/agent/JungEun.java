package com.teammaxine.agent;




import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.helpers.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by noxm on 17/04/17.
 * Base line AI
 * (dictates everything,
 * fails to fire a missile)
 */
public class JungEun extends Agent implements SliderPlayer {


    @Override
    public Move move() {
        System.out.println(this.getPlayer());
        ArrayList<? extends Move> moves = this.getMyBoard().getLegalMoves(this.getPlayer());
        if(moves.size() == 0)
            return null;
        HashMap<Vector2, Cell> myCells = this.getMyCells();

        this.update(moves.get(0), this.getPlayer());
        /*for(Vector2 pos : myCells.keySet()) {
            System.out.println(pos);
        }*/
        //System.out.println(moves.get(0).i + " " + moves.get(0).j);
        return moves.get(0);
    }

}

