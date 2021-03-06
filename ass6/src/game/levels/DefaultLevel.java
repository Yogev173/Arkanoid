package game.levels;

import geometry.characteristics.Velocity;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * default Level.
 */
public abstract class DefaultLevel implements LevelInformation, Level {

    public static final int MAX_BLOCKS_IN_ROW = (GameLevel.WIDTH - 2 * GameLevel.BORDER_VERTICAL_BLOCK_WIDTH)
            / Block.DEFAULT_WIDTH;

    public static final int PADDLE_SPEED = 5;

    //information
    private List<Velocity> ballVelocities;
    private Sprite background;
    private List<Block> blocks;

    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;

    /**
     * Constructor.
     * @param levelName the name of the level.
     * @param paddleSpeed the speed of the paddle.
     * @param paddleWidth the width of the paddle.
     */
    public DefaultLevel(String levelName, int paddleSpeed, int paddleWidth) {
        this.levelName = levelName;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;

        this.ballVelocities = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.initializeLevel();
    }

    /**
     * initialize the Level.
     */
    private void initializeLevel() {
        this.initializeBalls();
        this.initializeBlocks();
        this.initializeBackground();
    }

    /**
     * initializeBall.
     * initialize one Ball.
     * @param numOfBalls num of ball to add.
     */
    protected void addBalls(int numOfBalls) {
        double speed = GameLevel.BALL_DEFAULT_SPEED;
        double anglesDifference = 180.0 / (numOfBalls + 1);

        for (int currentBall = 1; currentBall <= numOfBalls; currentBall++) {
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(90 + currentBall * anglesDifference, speed));
        }
    }

    /**
     * addBlockRows.
     * @param numOfRows number of row to create
     * @param isGradual if the rows should be gradual
     */
    protected void addBlockRows(int numOfRows, boolean isGradual) {
        this.addBlockRows(numOfRows, isGradual, MAX_BLOCKS_IN_ROW);
    }

    /**
     * addBlockRows.
     * @param numOfRows number of row to create
     * @param isGradual if the rows should be gradual
     * @param maxNumOfBlocksInRow limit to the number of Blocks in each row
     */
    protected void addBlockRows(int numOfRows, boolean isGradual, int maxNumOfBlocksInRow) {
        int startOfTheRowX = GameLevel.WIDTH - GameLevel.BORDER_VERTICAL_BLOCK_WIDTH - Block.DEFAULT_WIDTH;
        int startOfTheRowY = GameLevel.HEIGHT / 4;
        int numOfBlocks = Math.min(MAX_BLOCKS_IN_ROW, maxNumOfBlocksInRow);

        for (int rowNumber = 0; rowNumber < numOfRows; rowNumber++) {
            this.addBlockRow(startOfTheRowX, startOfTheRowY, numOfBlocks, Ball.randomColorFromArray(rowNumber));

            // to prevent overlap with next row of Block
            startOfTheRowY += Block.DEFAULT_HEIGHT;

            if (isGradual) {
                numOfBlocks--;
            }
        }
    }

    /**
     * initializeBlockRow.
     * @param startOfTheRowX the x coordinate of the first block in the row (from the right).
     * @param startOfTheRowY the y coordinate of the first block in the row (from up).
     * @param numOfBlocks how much Block to create in this row.
     * @param color the Color for the Block's row.
     */
    protected void addBlockRow(int startOfTheRowX, int startOfTheRowY, int numOfBlocks, Color color) {
        int upperLeftX = startOfTheRowX;
        int upperLeftY = startOfTheRowY;
        for (int blockNumber = 0; blockNumber < numOfBlocks; blockNumber++) {
            Block block = new Block(new Rectangle(new Point(upperLeftX, upperLeftY)
                    , Block.DEFAULT_WIDTH, Block.DEFAULT_HEIGHT), color);

            this.blocks.add(block);
            // to prevent overlap with next Block
            upperLeftX -= Block.DEFAULT_WIDTH;
        }
    }


    /**
     * @return num of Ball in this Level.
     */
    @Override
    public int numberOfBalls() {
        return this.ballVelocities.size();
    }

    /**
     * @return The initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * @return paddle speed
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * the headline.
     * @return the level name
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return a list of Blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return number of Blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    /**
     * set background.
     * @param newBackground the new Background
     */
    public void setBackground(Sprite newBackground) {
        this.background = newBackground;
    }
}
