package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.Background.scenery.Planet;
import game.levels.Background.scenery.PointScenery;
import game.levels.GameLevel;
import geometry.sprite.Ball;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Space Background for Level.
 */
public class SpaceBackground implements Sprite {

    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpaceBackground() {
        this.sprites = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            this.sprites.add(new PointScenery(random.nextInt(GameLevel.WIDTH), random.nextInt(GameLevel.HEIGHT)
                    , Color.white));
        }

        for (int i = 0; i < 30; i++) {
            this.sprites.add(new PointScenery(random.nextInt(100) + 550, random.nextInt(100)
                    + 100, Ball.randomColor()));
        }

        for (int i = 0; i < 30; i++) {
            this.sprites.add(new PointScenery(random.nextInt(100) + 550, random.nextInt(100)
                    + 400, Ball.randomColor()));
        }

        for (int i = 0; i < 30; i++) {
            this.sprites.add(new PointScenery(random.nextInt(100) + 300, random.nextInt(100)
                    + 500, Ball.randomColor()));
        }

        this.sprites.add(new Planet(Color.WHITE, Color.gray, 15, 600, 500));
        this.sprites.add(new Planet(Color.red, Color.PINK, 30, 200, 500));
        this.sprites.add(new Planet(Color.BLUE, Color.CYAN, 20, 700, 200));
        this.sprites.add(new Planet(Color.GREEN, Color.GRAY, 15, 500, 400));
        this.sprites.add(new Planet(Color.ORANGE, Color.YELLOW, 25, 400, 200));
        this.sprites.add(new Planet(Color.magenta, Color.pink, 25, 400, 350));
        this.sprites.add(new Planet(Color.BLUE, Color.GREEN, Color.orange, 15, 200, 280));
        this.sprites.add(new Planet(Color.CYAN, Color.BLUE, 17, 200, 100));
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

        d.setColor(Color.GRAY);
        d.drawCircle(200, 200, 80);

        d.setColor(Color.GRAY);
        d.drawCircle(200, 200, 100);

        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }

        d.setColor(Color.YELLOW);
        d.fillCircle(200, 200, 30);
        d.setColor(Color.GRAY);
        d.drawCircle(200, 200, 50);
        d.setColor(Color.RED);
        d.fillCircle(250, 200, 10);

        d.setColor(Color.GRAY);
        d.drawCircle(200, 200, 130);
        d.setColor(Color.pink);
        d.fillCircle(70, 200, 20);
        d.setColor(Color.magenta);
        d.drawCircle(70, 200, 27);
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
