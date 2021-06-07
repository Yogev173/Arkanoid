package game.levels;

import game.levels.Background.DirectHitBackground;
import geometry.characteristics.Velocity;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.enviroment.Block;
import geometry.sprite.enviroment.Paddle;
import sprite.Sprite;

import java.awt.*;
import java.util.List;

public class Level1 extends DefaultLevel{


    /**
     * initialize the information about the level.
     */

    public Level1() {
        super("Direct Hit", DefaultLevel.PADDLE_SPEED, Paddle.PADDLE_DEFAULT_WIDTH);
    }

    /**
     * @return list of Velocity for the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(3);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of Blocks.
     */
    @Override
    public void initializeBlocks() {
        int X = GameLevel.WIDTH / 2;
        int Y = GameLevel.HEIGHT / 4;
        int h = Block.DEFAULT_HEIGHT;
        this.blocks.add(new Block(new Rectangle(new Point(X - h / 2 , Y), h, h)
                , Color.RED));
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public void initializeBackground() {
        this.background =  new DirectHitBackground();
    }
}
