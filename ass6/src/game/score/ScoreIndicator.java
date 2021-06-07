package game.score;

import biuoop.DrawSurface;
import collision.remove.Counter;
import game.levels.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * showing the Score.
 */
public class ScoreIndicator implements Sprite {
    public static final int X = GameLevel.WIDTH / 2 - 20;
    public static final int Y = 20;
    public static final int FONT_SIZE = 20;

    private Counter scoreCounter;

    /**
     *Constructor.
     * @param scoreCounter the Counter of score in the game.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameLevel.WIDTH, 25);
        d.setColor(Color.BLACK);
        d.drawText(X, Y, "Score: " + this.scoreCounter.getValue(), FONT_SIZE);
    }

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

    /**
     * addToGame.
     * add a Sprite to the Game environment.
     *
     * @param g the game Object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
