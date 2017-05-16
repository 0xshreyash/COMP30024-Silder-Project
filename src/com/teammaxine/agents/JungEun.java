/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.agents;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.helpers.Vector2;

import java.util.ArrayList;
import java.util.HashMap;


 /**
  * Our first AI, makes random(-ish) moves.
  * (dictates everything,
  * fails to fire a missile)
  * Modelled after Kim Jong-Un, using the Korean spelling of
  * his infamous name.
  */
public class JungEun extends Agent implements SliderPlayer {


    @Override
    public Move move() {
        /*System.out.println(this.getPlayer());
        System.out.println("Jung's board before move");
        System.out.println(this.getMyBoard());
        for(Cell cell : this.getMyBoard().getHorizontal().getMyCells().values()) {
            System.out.println(cell.getPos());
        }
        for(Move move : this.getMyBoard().getLegalMoves(this.getPlayer())) {
            System.out.println(move);
        }*/

        ArrayList<? extends Move> moves = this.getMyBoard().getLegalMoves(this.getPlayer());
        if(moves.size() == 0)
            return null;
        HashMap<Vector2, Cell> myCells = this.getMyCells();

        this.update(moves.get(0), this.getPlayer());
        /*for(Vector2 pos : myCells.keySet()) {
            System.out.println(pos);
        }*/
        //System.out.println(moves.get(0).i + " " + moves.get(0).j);
        /*System.out.println("Jung's board after move :");
        System.out.println(this.getMyBoard());*/
        return moves.get(0);
    }

}

