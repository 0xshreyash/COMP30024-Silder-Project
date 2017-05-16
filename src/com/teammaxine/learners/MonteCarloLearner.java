/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.learners;

import aiproj.slider.SliderPlayer;
import com.teammaxine.agents.Xena;
import com.teammaxine.board.scorers.MonteCarloScorer;
import com.teammaxine.board.scorers.Scorer;

/**
 * Plays game with various settings of Monte Carlo scorer to find the most
 * optimial setting.
 * Created by noxm on 10/05/17.
 */
public class MonteCarloLearner extends Learner {
    public static final int playCount = 21;
    public static final int size = 7;
    private static final int BONUS_SCORE = 1;

    public static void main(String[] args) {
        selfLearn();
//        battleLearn(new UpMan());
    }

    public static void battleLearn(SliderPlayer opponent) {
        Xena xena = new Xena();
        MonteCarloLearner learner = new MonteCarloLearner();

        int count = 0;
        int bestScore = 0;
        int i = 0, j = 0, k = 0, l = 0, m = 0;
        for(i = 0; i < 10; i++) {
            for(j = 0; j < 10; j++) {
                for(k = 0; k < 10; k++) {
                    for(l = 0; l < 10; l++) {
                        for(m = 0; m < 10; m++) {
                            System.out.println("---------------------------");
                            System.out.println("Game #" + count);
                            Scorer prevSetting = xena.getScorer();

                            xena.setScorer(new MonteCarloScorer(i, j, k, l, m));

                            int[] scores = new int[2];
                            // scores[0] = xena
                            // scores[1] = opponent

                            for(int p = 0; p < playCount; p++) {
                                int windex;

                                System.out.print("Match #" + p + " ");

                                if(p%2 == 0) {
                                    windex = learner.playGame(xena, opponent, size);
                                    scores[windex] += 1;
                                    if(windex == 1)
                                        scores[windex] += BONUS_SCORE;
                                } else {
                                    windex = learner.playGame(opponent, xena, size);
                                    scores[(1+windex)%2] += 1;
                                    if(windex == 1)
                                        scores[(1+windex)%2] += BONUS_SCORE;
                                }

                                System.out.print("Monte " + scores[0] + " : " + scores[1] + " Opponent\n");
                            }

                            if(scores[0] > bestScore) {
                                System.out.println("New Xena Setting " + i + " " + j + " " + k + " " + l + " " + m);
                            } else {
                                xena.setScorer(prevSetting);
                            }

                            count++;
                        }
                    }
                }
            }
        }

        System.out.println("Final winner " + i + " " + j + " " + k + " " + l + " " + m);
    }

    public static void selfLearn() {
        MonteCarloLearner learner = new MonteCarloLearner();
        Xena winner = new Xena();
        Xena opponent;

        int count = 0;
        int i = 0, j = 0, k = 0, l = 0, m = 0;
        for(i = 0; i < 10; i++) {
            for(j = 0; j < 10; j++) {
                for(k = 0; k < 10; k++) {
                    for(l = 0; l < 10; l++) {
                        for(m = 0; m < 10; m++) {
                            System.out.println("---------------------------");
                            System.out.println("Game #" + count);
                            opponent = new Xena();
                            opponent.setScorer(new MonteCarloScorer(i, j, k, l, m));

                            int[] scores = new int[2];
                            // scores[0] = winner
                            // scores[1] = opponent
                            for(int p = 0; p < playCount; p++) {
                                int windex;

                                System.out.print("Match #" + p + " ");

                                if(p%2 == 0) {
                                    windex = learner.playGame(winner, opponent, size);
                                    scores[windex] += 1;
                                    if(windex == 1)
                                        scores[windex] += BONUS_SCORE;
                                } else {
                                    windex = learner.playGame(opponent, winner, size);
                                    scores[(1+windex)%2] += 1;
                                    if(windex == 1)
                                        scores[(1+windex)%2] += BONUS_SCORE;
                                }

                                System.out.print("Monte " + scores[0] + " : " + scores[1] + " Opponent\n");
                            }

                            if(scores[0] < scores[1]) {
                                System.out.println("New winner " + i + " " + j + " " + k + " " + l + " " + m);
                                winner = opponent;
                            }

                            count++;
                        }
                    }
                }
            }
        }

        System.out.println("Final winner " + i + " " + j + " " + k + " " + l + " " + m);
    }
}
