package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.Background.scenery.Building;
import game.levels.Background.scenery.Cloud;
import game.levels.Background.scenery.FireWorks;
import game.levels.GameLevel;
import geometry.shape.Line;
import geometry.shape.Point;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Laser Background for Level.
 */
public class LaserBackground implements Sprite {
    public static final int LASERS_DIFFERENCE = 100;
    public static final int NUM_OF_LASERS = 5;

    private int[] indexes;
    private List<Line> lasers;
    private int numOfFrame;
    private List<Sprite> sprites;
    private List<Sprite> fireWorks;

    /**
     * Constructor.
     */
    public LaserBackground() {
        this.numOfFrame = 0;
        this.sprites = new ArrayList<>();
        this.indexes = new int[NUM_OF_LASERS];
        this.lasers = new ArrayList<>();
        this.fireWorks = new ArrayList<>();

        this.sprites.add(new Building(new Point(370, 300), Color.WHITE, Color.BLACK));
        this.sprites.add(new Cloud(new Point(650, 350)));
        this.sprites.add(new Cloud(new Point(150, 300)));
        this.sprites.add(new Cloud(new Point(200, 325)));
        this.sprites.add(new Cloud(new Point(625, 70)));
        this.sprites.add(new Cloud(new Point(660, 65)));
        this.sprites.add(new Cloud(new Point(200, 30)));
        this.sprites.add(new Cloud(new Point(250, 50)));

        this.fireWorks.add(new FireWorks(new Point(300, 400), Color.YELLOW));
        this.fireWorks.add(new FireWorks(new Point(100, 300), Color.MAGENTA));
        this.fireWorks.add(new FireWorks(new Point(600, 100), Color.RED));
        this.fireWorks.add(new FireWorks(new Point(500, 500), Color.GREEN));

        int startX = GameLevel.WIDTH - GameLevel.BORDER_VERTICAL_BLOCK_WIDTH;
        int difference = 1 + (startX - GameLevel.BORDER_VERTICAL_BLOCK_WIDTH) / LASERS_DIFFERENCE;
        for (int i = 0; i < LASERS_DIFFERENCE; i++) {
            this.lasers.add(new Line(new Point(400, 50)
                    , new Point(startX - i * difference, GameLevel.HEIGHT / 4), Color.RED));
        }

        Random random = new Random();
        for (int i = 0; i < NUM_OF_LASERS; i++) {
            this.indexes[i] = random.nextInt(LASERS_DIFFERENCE);
        }
    }
    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(385, 150, 30, 150);

        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(395, 50, 10, 100);

        d.setColor(Color.pink);
        d.fillCircle(400, 50, 10);
        d.setColor(Color.RED);
        d.fillCircle(400, 50, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(400, 50, 3);

        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }

        for (Sprite sprite : this.fireWorks) {
            sprite.drawOn(d);
        }

        for (int i = 0; i < this.indexes.length; i++) {
            this.lasers.get(this.indexes[i]).drawLine(d);
        }
    }

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        if (this.numOfFrame > 3) {
            for (int i = 0; i < this.indexes.length; i++) {
                this.indexes[i] = (this.indexes[i] + 1) % LASERS_DIFFERENCE;
            }

            for (Sprite sprite : this.fireWorks) {
                sprite.timePassed();
            }

            this.numOfFrame = 0;
        }

        this.numOfFrame++;
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
