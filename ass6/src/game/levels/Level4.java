package game.levels;

import game.levels.Background.SpaceBackground;
import geometry.sprite.enviroment.Paddle;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Level 4.
 */
public class Level4 extends DefaultLevel {
    /**
     * constructor.
     */
    public Level4() {
        super("Final Four", DefaultLevel.PADDLE_SPEED, Paddle.PADDLE_DEFAULT_WIDTH);
    }

    /**
     * initialize the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(3);
    }

    /**
     * initialize the Blocks.
     */
    @Override
    public void initializeBlocks() {
        this.addBlockRows(7, false);
    }

    /**
     * initialize the Background.
     */
    @Override
    public void initializeBackground() {
        this.setBackground(new SpaceBackground());
    }
}
