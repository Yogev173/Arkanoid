package game.animation;

import biuoop.DrawSurface;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Win Screen Animation object.
 */
public class WinningScreen implements Animation{

    private int score;

    /**
     * constructor.
     * @param score the final score.
     */
    public WinningScreen(int score) {
        this.score = score;
    }

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score
                + " -- press space to continue", 30);
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    public boolean shouldStop() {
        return false;
    }
}
