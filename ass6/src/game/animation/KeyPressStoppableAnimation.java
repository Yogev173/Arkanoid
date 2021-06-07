package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private boolean isAlreadyPressed;

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;

        this.isAlreadyPressed = true;
    }

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    @Override
    public boolean shouldStop() {
        if (isAlreadyPressed) {
            this.isAlreadyPressed = false;
        } else if (this.keyboardSensor.isPressed(key)) {
            return true;
        }

        return false;
    }
}
