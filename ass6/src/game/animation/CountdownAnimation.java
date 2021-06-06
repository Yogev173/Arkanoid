package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.GameLevel;
import sprite.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation{
    public static final int X_COORDINATE = GameLevel.WIDTH / 2 - 20;
    public static final int Y_COORDINATE = 80;

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;
    private int currentNumber;
    private int numOfFrame;
    private int framePerNumber;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.stop = false;
        this.currentNumber = this.countFrom;
        this.numOfFrame = 0;
        this.framePerNumber = (int) (60 * numOfSeconds / (countFrom + 1));
    }

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);

        if (this.numOfFrame > framePerNumber) {
            this.currentNumber--;
            this.numOfFrame = 0;
        }

        if (this.currentNumber > 0 ) {
            d.drawText(X_COORDINATE,Y_COORDINATE,Integer.toString(this.currentNumber), 50);

        }
        else if (this.currentNumber == 0) {
            d.drawText(X_COORDINATE,Y_COORDINATE,"GO!!", 50);
        } else { // counting dune
            this.stop = true;
        }

        this.numOfFrame++;
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
