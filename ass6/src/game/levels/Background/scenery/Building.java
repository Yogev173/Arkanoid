package game.levels.Background.scenery;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import geometry.shape.Point;
import sprite.Sprite;

import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Building Sprite.
 */
public class Building implements Sprite {

    public static final int WIDTH = 60;
    public static final int LINE_WIDTH = 4;

    private Point start;
    private Color color1;
    private Color color2;

    /**
     * Constructor.
     * @param start the start Point of the Building.
     * @param color1 the Color of the Building.
     * @param color2 the Color of the Walls.
     */
    public Building(Point start, Color color1, Color color2) {
        this.start = start;
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.start.getX();
        int y = (int) this.start.getY();
        int h = GameLevel.HEIGHT - y;
        int lineHeight = h / 20;
        d.setColor(this.color1);
        d.fillRectangle(x, y, WIDTH, h);

        d.setColor(this.color2);
        for (int i = 0; i < 8; i++) {
            d.fillRectangle(x + 2 * i * LINE_WIDTH, y, LINE_WIDTH, h);
        }


        for (int i = 0; i < 6; i++) {
            d.fillRectangle(x, y + 3 * i * lineHeight, WIDTH, lineHeight);
        }
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
