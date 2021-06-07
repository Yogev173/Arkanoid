package game.levels;

import biuoop.DrawSurface;
import game.score.ScoreIndicator;
import sprite.Sprite;

public class LevelName implements Sprite {

    private String levelName;

    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(ScoreIndicator.X + GameLevel.WIDTH / 4, ScoreIndicator.Y
                , "Level Name: " + this.levelName, ScoreIndicator.FONT_SIZE);
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
