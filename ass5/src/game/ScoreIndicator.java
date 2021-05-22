package game;

import biuoop.DrawSurface;
import collision.remove.Counter;
import geometry.sprite.enviroment.Paddle;
import sprite.Sprite;
import game.Game;

public class ScoreIndicator implements Sprite {
    public static final double HEIGHT = 30;
    public static final double WIDTH = 50;
    public static final double PADDLE_START_X = Game.WIDTH / 2 - WIDTH / 2;
    public static final double PADDLE_START_Y = 0;

    private Counter scoreCounter;

    /**
     *
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
    public void addToGame(Game g) {

    }
}
