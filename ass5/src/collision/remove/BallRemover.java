package collision.remove;

import game.Game;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import listener.HitListener;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * @param game the game to remove Blocks from.
     * @param numOfBalls Counter for remaining Balls
     */
    public BallRemover(Game game, Counter numOfBalls) {
        this.game = game;
        this.remainingBalls = numOfBalls;
    }

    /**
     * getting the message that Block was hit.
     * @param beingHit the object being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }

    /**
     * @return the Counter of the remaining Balls.
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }
}
