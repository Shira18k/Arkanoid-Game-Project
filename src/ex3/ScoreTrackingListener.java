package ex3;

import ex1.Ball;
import ex2.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;

    }
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}