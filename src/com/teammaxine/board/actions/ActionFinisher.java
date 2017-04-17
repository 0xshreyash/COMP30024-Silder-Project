/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.board.actions;

import com.teammaxine.board.elements.BoardAgent;
import com.teammaxine.board.helpers.Vector2;

/**
 * Contains the location of a cell that is on the edge, close to
 * finishing for a certain boardAgent, i.e. an action that might lead to
 * a finish for that piece.
 */
public class ActionFinisher extends AgentAction {
    Vector2 location;
    BoardAgent boardAgent;

    public ActionFinisher(Vector2 location, BoardAgent boardAgent) {
        this.location = location;
        this.boardAgent = boardAgent;
    }
}
