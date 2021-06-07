package game.levels.Background;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import geometry.sprite.enviroment.Block;
import sprite.Sprite;

import java.awt.*;

public class DirectHitBackground implements Sprite {

    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

        int middleX = GameLevel.WIDTH / 2;
        int middleY = GameLevel.HEIGHT / 4 + Block.DEFAULT_HEIGHT / 2;
        int h = Block.DEFAULT_HEIGHT;
        d.setColor(Color.YELLOW);
        d.drawCircle(middleX, middleY,80);
        d.drawCircle(middleX, middleY,60);
        d.drawCircle(middleX, middleY,40);

        d.drawLine(middleX + h + 5, middleY, middleX + h + 85, middleY);
        d.drawLine(middleX - h - 5, middleY, middleX - h -85, middleY);
        d.drawLine(middleX, middleY + h + 5, middleX, middleY + h + 85);
        d.drawLine(middleX, middleY - h - 5, middleX, middleY - h - 85);
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
