package game.levels;

import game.levels.Background.DirectHitBackground;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.enviroment.Block;
import geometry.sprite.enviroment.Paddle;

import java.awt.Color;


public class Level2 extends DefaultLevel{

    /**
     * constructor.
     */
    public Level2() {
        super("Wide Easy", DefaultLevel.PADDLE_SPEED, 500);
    }

    /**
     * @return list of Velocity for the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(30);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of Blocks.
     */
    @Override
    public void initializeBlocks() {
        this.addBlockRows(1, false);
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public void initializeBackground() {
        this.background =  new DirectHitBackground();
    }
}
