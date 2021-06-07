package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.Background.scenery.Planet;
import game.levels.Background.scenery.PointScenery;
import game.levels.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceBackground implements Sprite {

    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpaceBackground() {
        this.sprites = new ArrayList<>();
        this.sprites.add(new Planet(Color.WHITE, Color.gray, 15, 600, 500));
        this.sprites.add(new Planet(Color.red, Color.PINK, 30, 200, 500));
        this.sprites.add(new Planet(Color.BLUE, Color.CYAN, 20, 300, 300));
        this.sprites.add(new Planet(Color.GREEN, Color.GRAY, 15, 500, 400));
        this.sprites.add(new Planet(Color.ORANGE, Color.YELLOW, 25, 400, 200));

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            this.sprites.add(new PointScenery(random.nextInt(GameLevel.WIDTH), random.nextInt(GameLevel.HEIGHT)
                    , Color.white));
        }
    }

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

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
