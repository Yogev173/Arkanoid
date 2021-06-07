package game.levels.Background.scenery;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import sprite.Sprite;

import java.awt.*;

public class Planet implements Sprite {

    private Color planetColor;
    private Color craterColor;
    private int radios;
    private int centerX;
    private int centerY;

    /**
     * Constructor.
     * @param planetColor the planet color
     * @param craterColor the carter color
     * @param radios radios of the planet
     * @param centerX x coordinate
     * @param centerY y coordinate
     */
    public Planet(Color planetColor, Color craterColor, int radios, int centerX, int centerY) {
        this.planetColor = planetColor;
        this.craterColor = craterColor;
        this.radios = radios;
        this.centerX = centerX;
        this.centerY = centerY;
    }
    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int r = this.radios;
        d.setColor(this.planetColor);
        d.fillCircle(this.centerX, this.centerY, r);

        d.setColor(this.craterColor);
        d.fillCircle(this.centerX + r / 4, this.centerY + r / 3, r / 4);
        d.fillCircle(this.centerX - r / 3, this.centerY + r / 3, r / 5);
        d.fillCircle(this.centerX - r / 4 , this.centerY - r / 3, r / 6);
        d.fillCircle(this.centerX + r / 3, this.centerY - r / 3, r / 5);
        d.fillCircle(this.centerX - r / 2 , this.centerY - r / 2, r / 6);
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
