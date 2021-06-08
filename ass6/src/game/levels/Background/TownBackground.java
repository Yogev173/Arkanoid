package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.Background.scenery.Building;
import game.levels.Background.scenery.Cloud;
import game.levels.Background.scenery.Planet;
import game.levels.Background.scenery.PointScenery;
import game.levels.GameLevel;
import geometry.shape.Point;
import geometry.sprite.Ball;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Town Background for Level.
 */
public class TownBackground implements Sprite {
    private boolean isDay;
    private int numOfFrame;
    private Planet sun;
    private Planet moon;
    private List<Sprite> stars;
    private List<Sprite> clouds;
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public TownBackground() {
        this.isDay = true;
        this.numOfFrame = 0;
        this.sun = new Planet(Color.YELLOW, Color.YELLOW, 30, 800, 100);
        this.moon = new Planet(Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, 20, 800, 100);
        this.stars = new ArrayList<>();
        this.clouds = new ArrayList<>();
        this.sprites = new ArrayList<>();

        this.clouds.add(new Cloud(new Point(100, 200)));
        this.clouds.add(new Cloud(new Point(400, 300)));
        this.clouds.add(new Cloud(new Point(650, 350)));
        this.clouds.add(new Cloud(new Point(150, 300)));
        this.clouds.add(new Cloud(new Point(200, 325)));
        this.clouds.add(new Cloud(new Point(625, 70)));
        this.clouds.add(new Cloud(new Point(660, 65)));
        this.clouds.add(new Cloud(new Point(200, 30)));
        this.clouds.add(new Cloud(new Point(250, 50)));
        this.clouds.add(new Cloud(new Point(350, 60)));

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            this.stars.add(new PointScenery(random.nextInt(GameLevel.WIDTH), random.nextInt(GameLevel.HEIGHT)
                    , Color.white));
        }

        int startX = GameLevel.BORDER_VERTICAL_BLOCK_WIDTH;
        int startY = 500;
        for (int i =0; i < 13; i++) {
            if (i % 2 == 0) {
                startY -= 50;
            } else {
                startY += 60;
            }

            this.sprites.add(new Building(new Point(startX + i * Building.WIDTH, startY)
                    , Ball.randomColorFromArray(i), Color.gray));
        }
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.isDay) {
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
            this.sun.drawOn(d);

            for (Sprite sprite : this.clouds) {
                sprite.drawOn(d);
            }
        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

            for (Sprite sprite : this.stars) {
                sprite.drawOn(d);
            }

            this.moon.drawOn(d);
        }

        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

        if (isDay) {
            this.sun.setCenterX(this.sun.getCenterX() - 2);
        } else  {
            this.moon.setCenterX(this.moon.getCenterX() - 2);
        }

        if (this.numOfFrame > 415) {
            this.isDay = !this.isDay;
            this.numOfFrame = 0;
            this.sun.setCenterX(830);
            this.moon.setCenterX(830);
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
