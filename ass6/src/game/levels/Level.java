package game.levels;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * level that can be build.
 */
public interface Level {

    /**
     * initialize the Balls.
     */
    void initializeBalls();

    /**
     * initialize the Blocks.
     */
    void initializeBlocks();

    /**
     * initialize the Background.
     */
    void initializeBackground();
}
