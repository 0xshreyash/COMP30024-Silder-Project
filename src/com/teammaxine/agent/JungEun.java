package com.teammaxine.agent;




import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.Cell;
import com.teammaxine.board.helpers.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by noxm on 17/04/17.
 * Base line AI
 * (dictates everything,
 * fails to fire a missile)
 */
public class JungEun extends Agent implements SliderPlayer {

    private Board myBoard;
    private int dimension;
    private char player;
    private HashMap<Vector2, Cell> myCells;

    @Override
    public Move move() {
        return null;
    }
}

