package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import geometry.sprite.enviroment.Block;
import sprite.Sprite;

import java.awt.*;

public class DirectHitBackground implements Sprite {

    public DirectHitBackground() {

    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.ORANGE);
        d.fillCircle((GameLevel.WIDTH - Block.DEFAULT_HEIGHT) / 2, GameLevel.HEIGHT / 4 + Block.DEFAULT_HEIGHT / 2
                ,60);
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
     * @param g the game Object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}