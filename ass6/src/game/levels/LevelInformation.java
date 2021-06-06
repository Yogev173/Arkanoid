package game.levels;

import geometry.characteristics.Velocity;
import geometry.sprite.enviroment.Block;
import sprite.Sprite;

import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Infformation for a Level.
 */
public interface LevelInformation {

    /**
     * @return num of Ball in this Level.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * the headline.
     * @return the level name
     */
    String levelName();

    /**
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return a list of Blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return number of Blocks to remove.
     */
    int numberOfBlocksToRemove();
}
