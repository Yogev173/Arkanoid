package collision.remove;

import game.GameLevel;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import listener.HitListener;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * @param gameLevel the game to remove Blocks from.
     * @param removedBlocks Counter for remaining Blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * getting the message that Block was hit.
     * @param beingHit the object being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }

    /**
     * @return the Counter of the remaining Blocks.
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }
}
