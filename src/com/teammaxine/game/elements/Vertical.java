/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */
package com.teammaxine.game.elements;

/**
 * Vertical agent of the game
 */
public class Vertical extends Agent {
    public static String[] directions = {
            Board.DIR_UP,
            Board.DIR_LEFT,
            Board.DIR_RIGHT
    };

    public Vertical(int size) {
        super(size);
    }

    @Override
   public void setAllDirections() {
       for (String direction : Vertical.directions) {
           this.addLegalDirection(direction);
       }
   }

}
