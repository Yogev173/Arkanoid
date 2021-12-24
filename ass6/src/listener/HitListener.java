package listener;

import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * HitListener object.
 */
public interface HitListener {
    /**
     * getting the message that Block was hit.
     * @param beingHit the object being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
