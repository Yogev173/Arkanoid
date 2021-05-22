package collision.spcialBlocks;

import collision.remove.Counter;
import game.Game;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import listener.HitListener;

public class ExtraBallBlockListener implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * @param game the game to remove Blocks from.
     * @param removedBlocks the number of Blocks that need to be removed.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * getting the message that Block was hit.
     * @param beingHit the object being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }

    /**
     * @return the Counter of the remaining Blocks.
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }
}