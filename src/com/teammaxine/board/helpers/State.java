/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.helpers;

/**
 * Class that acted as the values associated to a key
 * represented by a Compressed Board. Did not make our final
 * submission, read comments.txt to find out why!
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
