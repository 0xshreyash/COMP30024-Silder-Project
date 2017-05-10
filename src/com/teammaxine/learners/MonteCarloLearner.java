package com.teammaxine.learners;

import aiproj.slider.SliderPlayer;
import com.teammaxine.agent.Xena;
import com.teammaxine.board.helpers.MonteCarloScorer;

/**
 * Created by noxm on 10/05/17.
 */
public class MonteCarloLearner extends Learner {
    public static final int playCount = 3;
    public static final int size = 7;
    private static final int BONUS_SCORE = 1;

    public static void main(String[] args) {
        MonteCarloLearner learner = new MonteCarloLearner();
        Xena winner = new Xena();
        Xena opponent;

        int count = 0;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 10; k++) {
                    for(int l = 0; l < 10; l++) {
                        for(int m = 0; m < 10; m++) {
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
    }
}
