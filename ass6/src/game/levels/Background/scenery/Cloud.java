package game.levels.Background.scenery;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import geometry.shape.Point;
import sprite.Sprite;

import java.awt.*;

public class Cloud implements Sprite {

    private Point point;

    public Cloud(Point point) {
        this.point = point;
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.point.getX();
        int y = (int) this.point.getY();
        d.setColor(Color.WHITE);
        d.fillCircle(x + 20, y + 70, 10);
        d.fillCircle(x + 40, y + 70, 15);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(x + 30, y + 50, 15);
        d.fillCircle(x + 60, y + 70, 15);
        d.setColor(Color.GRAY);
        d.fillCircle(x + 50, y + 50, 17);
        d.fillCircle(x + 80, y + 50, 20);
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
