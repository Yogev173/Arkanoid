package game.animation;

import biuoop.DrawSurface;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Animation object.
 */
public interface Animation {

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    boolean shouldStop();
}
