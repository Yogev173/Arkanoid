import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {

    public static final int PADDLE_DEFAULT_WIDTH = Block.DEFAULT_WIDTH;
    public static final int PADDLE_DEFAULT_HEIGHT = Block.DEFAULT_HEIGHT;

    public static final double DEFAULT_VELOCITY = 5;

    public static final int PADDLE_DEFAULT_LEFT_BORDER = Game.BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int PADDLE_DEFAULT_RIGHT_BORDER = Game.WIDTH - Game.BORDER_VERTICAL_BLOCK_WIDTH;

    private KeyboardSensor keyboard;
    private Block paddleBlock;
    private double velocity;
    private int paddleLeftBorder;
    private int paddleRightBorder;

    /**
     * Constructor.
     * @param keyboard user-controlled keyboard sensor
     * @param paddleBlock the Block of the Paddle
     */
    public Paddle(KeyboardSensor keyboard, Block paddleBlock) {
        this.keyboard = keyboard;
        this.paddleBlock = paddleBlock;
        this.velocity = DEFAULT_VELOCITY;
        this.paddleLeftBorder = PADDLE_DEFAULT_LEFT_BORDER;
        this.paddleRightBorder = PADDLE_DEFAULT_RIGHT_BORDER;
    }

    public void moveLeft() {
        Point upperLeft = this.paddleBlock.getCollisionRectangle().getUpperLeft();
        if (upperLeft.getX() - this.velocity < this.paddleLeftBorder) {
            upperLeft.setX(this.paddleLeftBorder);
        } else {
            upperLeft.setX(upperLeft.getX() - this.velocity);
        }
    }

    public void moveRight() {
        Point upperLeft = this.paddleBlock.getCollisionRectangle().getUpperLeft();
        double width = this.paddleBlock.getCollisionRectangle().getWidth();
        if (upperLeft.getX() + width + this.velocity > this.paddleRightBorder) {
            upperLeft.setX(this.paddleRightBorder - width);
        } else {
            upperLeft.setX(upperLeft.getX() + this.velocity);
        }
    }

    /**
     * timePassed.
     * check if need to move the paddleBlock and if so execute it.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * drawOn
     *
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        paddleBlock.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }

    /**
     * hit.
     * Notify the paddleBlock that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collided Point
     * @param currentVelocity the velocity at collided time
     * @return
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return this.paddleBlock.hit(collisionPoint, currentVelocity);
    }

    /**
     * addToGame.
     * Add this paddleBlock to the game.
     * @param g the game Object.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}