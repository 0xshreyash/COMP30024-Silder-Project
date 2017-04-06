package com.teammaxine.game.actions;

import com.teammaxine.game.elements.Agent;
import com.teammaxine.game.helpers.Vector2;

/**
 * Created by noxm on 6/04/17.
 */
public class ActionFinisher extends AgentAction {
    Vector2 location;
    Agent agent;

    public ActionFinisher(Vector2 location, Agent agent) {
        this.location = location;
        this.agent = agent;
    }
}
