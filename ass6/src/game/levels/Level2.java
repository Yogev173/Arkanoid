package game.levels;

import game.levels.Background.LaserBackground;


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
        super.addBalls(15);
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
        this.background =  new LaserBackground();
    }
}
