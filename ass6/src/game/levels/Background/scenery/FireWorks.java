package game.levels.Background.scenery;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import geometry.shape.Point;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Fire Works Sprite.
 */
public class FireWorks implements Sprite {

    public static final int[] FIRE_WORKS_FRAMES = {5, 10, 20, 30, 40, 50};

    private Point center;
    private int fireWorksIndex;
    private int numOfFrame;
    private List<Sprite> points;
    private Color color;

    /**
     * Constructor.
     * @param center the center of the FireWorks.
     * @param color the color of the fire works.
     */
    public FireWorks(Point center, Color color) {
        this.center = center;
        this.fireWorksIndex = 0;
        this.numOfFrame = 0;
        this.points = new ArrayList<>();
        this.color = color;
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : this.points) {
            sprite.drawOn(d);
        }
    }

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        if (numOfFrame > 2) {
            this.points = new ArrayList<>();
            Random random = new Random();
            int frame = this.FIRE_WORKS_FRAMES[this.fireWorksIndex];

            for (int i = 0; i < 3 * frame; i++) {
                Point point = new Point(this.center.getX() + random.nextInt(frame) - 50
                        , this.center.getY() + random.nextInt(frame) - 50);
                if (point.distance(this.center) < frame) {
                    this.points.add(new PointScenery(point, this.color));
                }
            }
            this.fireWorksIndex = (this.fireWorksIndex + 1) % FIRE_WORKS_FRAMES.length;
            this.numOfFrame = 0;
        }

        this.numOfFrame++;
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
