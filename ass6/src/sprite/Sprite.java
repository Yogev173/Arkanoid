package sprite;

import biuoop.DrawSurface;
import game.levels.GameLevel;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Sprite.
 */
public interface Sprite {

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    void timePassed();


    /**
     * addToGame.
     * add a Sprite to the Game environment.
     * @param g the game Object.
     */
    void addToGame(GameLevel g);
}