package game.levels;

import geometry.characteristics.Velocity;
import geometry.sprite.enviroment.Block;
import sprite.Sprite;

import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * level that can be build.
 */
public interface Level {

    /**
     * initialize the information about the level.
     */
    void createInfo();

    /**
     * @return list of Velocity for the Balls.
     */
    List<Velocity> initializeBalls();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return a list of Blocks.
     */
    List<Block> initializeBlocks();

    /**
     * @return a sprite with the background of the level
     */
    Sprite initializeBackground();
}
