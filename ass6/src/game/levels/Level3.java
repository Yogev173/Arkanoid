package game.levels;

import game.levels.Background.TownBackground;
import geometry.sprite.enviroment.Paddle;

public class Level3 extends DefaultLevel{

    /**
     * constructor.
     */
    public Level3() {
        super("Green 3", DefaultLevel.PADDLE_SPEED, 5 * Paddle.PADDLE_DEFAULT_WIDTH);
    }

    /**
     * @return list of Velocity for the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(20);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of Blocks.
     */
    @Override
    public void initializeBlocks() {
        this.addBlockRows(5, true,7);
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public void initializeBackground() {
        this.background =  new TownBackground();
    }
}
