package geometry.sprite.enviroment;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import geometry.characteristics.Velocity;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.Ball;
import sprite.Sprite;
import game.levels.GameLevel;


/**
 * @author yogev abarbanel
 * Id: 326116910
 * one Paddle.
 */
public class Paddle implements Sprite, Collidable {

    public static final int PADDLE_DEFAULT_WIDTH = 2 * Block.DEFAULT_WIDTH;
    public static final int PADDLE_DEFAULT_HEIGHT = 10;

    public static final double DEFAULT_VELOCITY = 5;

    public static final int PADDLE_DEFAULT_LEFT_BORDER = GameLevel.BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int PADDLE_DEFAULT_RIGHT_BORDER = GameLevel.WIDTH - GameLevel.BORDER_VERTICAL_BLOCK_WIDTH;

    //angle by section
    public static final double ANGLE_FOR_SECTION_1 = 300;
    public static final double ANGLE_FOR_SECTION_2 = 330;
    public static final double ANGLE_FOR_SECTION_4 = -ANGLE_FOR_SECTION_2;
    public static final double ANGLE_FOR_SECTION_5 = -ANGLE_FOR_SECTION_1;

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

    /**
     * moveLeft.
     * Check if the Paddle can move left if it can it do nothing,
     * if it can't it get it back to the board.
     */
    public void moveLeft() {
        Point upperLeft = this.paddleBlock.getCollisionRectangle().getUpperLeft();
        if (upperLeft.getX() - this.velocity < this.paddleLeftBorder) {
            upperLeft.setX(this.paddleLeftBorder);
        } else {
            upperLeft.setX(upperLeft.getX() - this.velocity);
        }
    }

    /**
     * movRight.
     * Check if the Paddle can move right if it can it do nothing,
     * if it can't it get it back to the board.
     */
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
     * drawOn.
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
     * @param hitter the hitting ball.
     * @return the new Velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //in case it isn't collapse the top of the Paddle
        if (!this.paddleBlock.getCollisionRectangle().getUpperSide().isInLine(collisionPoint)) {
            return this.paddleBlock.hit(hitter, collisionPoint, currentVelocity);
        }

        int fifthPaddle = (int) this.paddleBlock.getCollisionRectangle().getWidth() / 5;
        double pointX = collisionPoint.getX();
        double paddleStart = this.paddleBlock.getCollisionRectangle().getUpperLeft().getX();
        double epsilon = Point.COMPARISON_THRESHOLD;

        //set new Velocity by section of the Paddle
        if (paddleStart - epsilon <= pointX && pointX <= paddleStart + fifthPaddle + epsilon) {
            return Velocity.fromAngleAndSpeed(ANGLE_FOR_SECTION_1, currentVelocity.getSpeed());
        } else if (paddleStart + fifthPaddle - epsilon <= pointX
                && pointX <= paddleStart + 2 * fifthPaddle + epsilon) {
            return Velocity.fromAngleAndSpeed(ANGLE_FOR_SECTION_2, currentVelocity.getSpeed());
        } else if (paddleStart + 2 * fifthPaddle - epsilon <= pointX
                && pointX <= paddleStart + 3 * fifthPaddle + epsilon) {
            return this.paddleBlock.hit(hitter, collisionPoint, currentVelocity);
        } else if (paddleStart + 3 * fifthPaddle - epsilon <= pointX
                && pointX <= paddleStart + 4 * fifthPaddle + epsilon) {
            return Velocity.fromAngleAndSpeed(ANGLE_FOR_SECTION_4, currentVelocity.getSpeed());
        } else if (paddleStart + 4 * fifthPaddle - epsilon <= pointX
                && pointX <= paddleStart + 5 * fifthPaddle + epsilon) {
            return Velocity.fromAngleAndSpeed(ANGLE_FOR_SECTION_5, currentVelocity.getSpeed());
        }

        // if it got to here the collisionPoint isn't with the Paddle.
        throw new RuntimeException("collisionPoint isn't with Paddle");
    }

    /**
     * addToGame.
     * Add this paddleBlock to the game.
     * @param g the game Object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}