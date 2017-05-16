package com.teammaxine.agents;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

import java.util.Scanner;

/**
 * Lets us play the game as agents, in order to get an intuition on how to
 * play, and try to find trick moves that we can use in order to
 */
public class UserAgent extends Agent implements SliderPlayer{


    public Move move() {

        /*
         * Take users options.
         */
        System.out.println("You need to play move for:" + this.getPlayer());
        Scanner in = new Scanner(System.in);
        System.out.println("Enter column: ");
        int i = in.nextInt();
        System.out.println("Enter row:");
        int j = in.nextInt();
        System.out.println("Enter Direction:");
        String dir = in.next();
        Move.Direction d;
        /*
         * Turn string into type Move.Direction
         */
        if(dir.toLowerCase().equals("up"))
            d = Move.Direction.UP;
        else if(dir.toLowerCase().equals("down"))
            d = Move.Direction.DOWN;
        else if(dir.toLowerCase().equals("left"))
            d = Move.Direction.LEFT;
        else
            d = Move.Direction.RIGHT;

        return new Move(i, j, d);
    }
}
