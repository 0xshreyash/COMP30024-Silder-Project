/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */
package com.teammaxine.game.actions;

import com.teammaxine.game.helpers.Vector2;

public class ActionMove extends AgentAction {
    Vector2 from, to;

    public ActionMove(Vector2 from, Vector2 to) {
        super();
        this.from = from;
        this.to = to;
    }
}
