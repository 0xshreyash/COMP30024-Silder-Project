/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.actions;

import aiproj.slider.Move;
import com.teammaxine.board.helpers.Vector2;

/**
 * Placeholder for part B
 */
public class AgentAction extends Move {
    public enum ActionType {
        MOVE,
        FINISH
    }
    private ActionType type;

    public AgentAction(ActionType type, Vector2 location, Move.Direction d) {
        super(location.getX(), location.getY(), d);
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }
}
