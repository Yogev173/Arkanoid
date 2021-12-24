package game.animation;

import biuoop.DrawSurface;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Pause Screen Animation object.
 */
public class PauseScreen implements Animation {
    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 30);
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    public boolean shouldStop() {
        return false;
    }
}

