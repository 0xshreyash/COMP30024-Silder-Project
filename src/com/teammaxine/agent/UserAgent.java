package com.teammaxine.agent;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

import java.util.Scanner;

/**
 * Created by shreyashpatodia on 06/05/17.
 */
public class UserAgent extends Agent implements SliderPlayer{


    public Move move() {
        System.out.println("You need to play move for:" + this.getPlayer());
        Scanner in = new Scanner(System.in);
        System.out.println("Enter column: ");
        int i = in.nextInt();
        System.out.println("Enter row:");
        int j = in.nextInt();
        System.out.println("Enter Direction:");
        String dir = in.next();
        Move.Direction d;
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
