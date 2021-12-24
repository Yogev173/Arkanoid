package game.levels;

import game.levels.Background.LaserBackground;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Level 2.
 */
public class Level2 extends DefaultLevel {

    /**
     * Constructor.
     */
    public Level2() {
        super("Wide Easy", DefaultLevel.PADDLE_SPEED, 500);
    }

    /**
     * initialize the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(15);
    }

    /**
     * initialize the Blocks.
     */
    @Override
    public void initializeBlocks() {
        this.addBlockRows(1, false);
    }

    /**
     * initialize the Background.
     */
    @Override
    public void initializeBackground() {
        this.setBackground(new LaserBackground());
    }
}
