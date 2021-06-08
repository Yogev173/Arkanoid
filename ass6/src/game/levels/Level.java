package game.levels;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * level that can be build.
 */
public interface Level {
    /**
     * @return list of Velocity for the Balls.
     */
    void initializeBalls();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return a list of Blocks.
     */
    void initializeBlocks();

    /**
     * @return a sprite with the background of the level
     */
    void initializeBackground();
}
