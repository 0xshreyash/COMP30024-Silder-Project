/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;

/**
 * All strategies should inherit this
 * Allows us to plug and play strategies on the fly.
 */
public interface Strategy {
    public Move findMove(Board currentBoard, int depth);
}
