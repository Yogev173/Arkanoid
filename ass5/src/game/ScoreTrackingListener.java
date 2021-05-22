package game;

import collision.remove.Counter;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import listener.HitListener;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * track after the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int SCORE_FOR_HITTING_BLOCK = 5;

    private Counter currentScore;

    /**
     * @param scoreCounter Counter for the score in the gmae.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * getting the message that Block was hit.
     * @param beingHit the object being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(SCORE_FOR_HITTING_BLOCK);
    }


    /**
     * @return the Counter of the remaining Blocks.
     */
    public Counter getCounter() {
        return this.currentScore;
    }
}
