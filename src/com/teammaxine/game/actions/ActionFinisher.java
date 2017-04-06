/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */
package com.teammaxine.game.actions;

import com.teammaxine.game.elements.Agent;
import com.teammaxine.game.helpers.Vector2;

/**
 * Contains the location of a cell that is on the edge, close to
 * finishing for a certain agent, i.e. an action that might lead to
 * a finish for that piece.
 */
public class ActionFinisher extends AgentAction {
    Vector2 location;
    Agent agent;

    public ActionFinisher(Vector2 location, Agent agent) {
        this.location = location;
        this.agent = agent;
    }
}
