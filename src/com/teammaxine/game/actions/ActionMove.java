/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hlee39
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.game.actions;

import com.teammaxine.game.helpers.Vector2;

/**
 * Contains a possible move action from one position to
 * another, will help us keep track of possible moves of
 * the agent in future parts.
 */
public class ActionMove extends AgentAction {
    Vector2 from, to;

    public ActionMove(Vector2 from, Vector2 to) {
        super();
        this.from = from;
        this.to = to;
    }
}
