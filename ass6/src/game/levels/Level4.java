package game.levels;

import game.levels.Background.DirectHitBackground;
import game.levels.Background.SpaceBackground;
import geometry.sprite.enviroment.Paddle;

public class Level4 extends DefaultLevel{
    /**
     * constructor.
     */
    public Level4() {
        super("Final Four", DefaultLevel.PADDLE_SPEED, Paddle.PADDLE_DEFAULT_WIDTH);
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
        this.addBlockRows(7, false);
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public void initializeBackground() {
        this.background =  new SpaceBackground();
    }
}
