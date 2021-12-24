package game.levels;

import game.levels.Background.TownBackground;
import geometry.sprite.enviroment.Paddle;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Level 3.
 */
public class Level3 extends DefaultLevel {

    /**
     * constructor.
     */
    public Level3() {
        super("Town", DefaultLevel.PADDLE_SPEED, Paddle.PADDLE_DEFAULT_WIDTH);
    }

    /**
     * initialize the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(2);
    }

    /**
     * initialize the Blocks.
     */
    @Override
    public void initializeBlocks() {
        this.addBlockRows(5, true, 7);
    }

    /**
     * initialize the Background.
     */
    @Override
    public void initializeBackground() {
        this.setBackground(new TownBackground());
    }
}
