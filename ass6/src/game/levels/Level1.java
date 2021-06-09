package game.levels;

import game.levels.Background.DirectHitBackground;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.enviroment.Block;
import geometry.sprite.enviroment.Paddle;

import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Level 1.
 */
public class Level1 extends DefaultLevel {

    /**
     * constructor.
     */
    public Level1() {
        super("Direct Hit", DefaultLevel.PADDLE_SPEED, Paddle.PADDLE_DEFAULT_WIDTH);
    }

    /**
     * initialize the Balls.
     */
    @Override
    public void initializeBalls() {
        super.addBalls(1);
    }


    /**
     * initialize the Blocks.
     */
    @Override
    public void initializeBlocks() {
        int x = GameLevel.WIDTH / 2;
        int y = GameLevel.HEIGHT / 4;
        int h = Block.DEFAULT_HEIGHT;
        this.blocks().add(new Block(new Rectangle(new Point(x - h / 2 , y), h, h)
                , Color.RED));
    }

    /**
     * initialize the Background.
     */
    @Override
    public void initializeBackground() {
        this.setBackground(new DirectHitBackground());
    }
}
