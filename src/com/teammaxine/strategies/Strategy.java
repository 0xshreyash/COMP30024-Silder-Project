package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;

/**
 * All strategies should inherit this
 * Created by shreyashpatodia on 05/05/17.
 */
public interface Strategy {
    public Move findMove(Board currentBoard, int depth);
}
