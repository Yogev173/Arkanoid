package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Pause Screen Animation object.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

