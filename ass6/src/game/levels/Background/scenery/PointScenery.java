package game.levels.Background.scenery;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Point Sprite.
 */
public class PointScenery implements Sprite {

    private static final int RADIOS = 2;
    private Color color;
    private int centerX;
    private int centerY;

    /**
     * Constructor.
     * @param centerX x coordinate
     * @param centerY y coordinate
     * @param color the color of the point.
     */
    public PointScenery(int centerX, int centerY, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle(this.centerX, this.centerY, RADIOS);
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
