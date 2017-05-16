package com.teammaxine.board.helpers;

/**
 * Created by shreyashpatodia on 15/05/17.
 */
public class State {

    private double lowerBound;
    private double upperBound;
    private int depth;
    private double score;

    public State(double lowerBound, double upperBound, int depth, double score) {
        this.depth = depth;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.score = score;
    }
    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
