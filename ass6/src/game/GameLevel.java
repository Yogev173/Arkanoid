package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.GameEnvironment;
import collision.remove.BallRemover;
import collision.remove.BlockRemover;
import collision.remove.Counter;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.CountdownAnimation;
import game.animation.PauseScreen;
import game.score.ScoreIndicator;
import game.score.ScoreTrackingListener;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.Ball;
import geometry.sprite.enviroment.Block;
import geometry.sprite.enviroment.Paddle;
import sprite.Sprite;
import sprite.SpriteCollection;
import java.awt.Color;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Game of pong.
 */
public class GameLevel implements Animation {

    //Board Border
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_VERTICAL_BLOCK_WIDTH = 10;
    public static final int BORDER_VERTICAL_BLOCK_HEIGHT = HEIGHT - 25;
    public static final int BORDER_HORIZONTAL_BLOCK_WIDTH = WIDTH;
    public static final int BORDER_HORIZONTAL_BLOCK_HEIGHT = 10;
    public static final int DEFAULT_MAX_BLOCKS_IN_ROW = Integer.MAX_VALUE;
    public static final Color BORDER_COLOR = Color.GRAY;

    //Paddle
    public static final double PADDLE_START_X = WIDTH / 2 - Paddle.PADDLE_DEFAULT_WIDTH / 2;
    public static final double PADDLE_START_Y = HEIGHT - Paddle.PADDLE_DEFAULT_HEIGHT;

    //Ball setup
    public static final int DEFAULT_NUM_OF_BALLS = 1;
    public static final int BALL_RADIOS = 6;
    public static final double BALL_START_X = WIDTH / 2;
    public static final double BALL_START_Y = PADDLE_START_Y - BALL_RADIOS;
    public static final double BALL_START_VELOCITY_DX = 0;
    public static final double BALL_START_VELOCITY_DY = -4.0;
    public static final int RANDOM_LIMIT = (int) Paddle.PADDLE_DEFAULT_WIDTH / 2;

    //Blocks setup
    public static final int DEFAULT_ROWS_NUMBER = 6;
    public static final int BLOCK_DEFAULT_WIDTH = Block.DEFAULT_WIDTH;
    public static final int BLOCK_DEFAULT_HEIGHT = Block.DEFAULT_HEIGHT;

    public static final int BLOCK_ROWS_X_START = WIDTH - BLOCK_DEFAULT_WIDTH - BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int BLOCK_ROWS_Y_START = HEIGHT * 1 / 4;
    public static final int BLOCK_ROWS_WIDTH_BORDER = WIDTH - 2 * BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int MAX_BLOCKS_IN_ROW = BLOCK_ROWS_WIDTH_BORDER / BLOCK_DEFAULT_WIDTH;

    //special Block
    public static final Color KILLING_BLOCK_COLOR = Color.RED;
    public static final Color EXTRA_BLOCK_COLOR = Color.GREEN;

    //score
    public static final int SCORE_FOR_PASSING_LEVEL = 100;

    //animation
    public static final int FRAME_PER_SECOND = 60;

    //fields:
    //general
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private int numOfRow;
    private int numOfBalls;
    private boolean isGradual;
    private int maxNumOfBlocksInRow;

    //listener
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;

    //Animation related
    private AnimationRunner runner;
    private boolean running;

    /**
     * Constructor.
     */
    public GameLevel() {
        this(DEFAULT_ROWS_NUMBER, DEFAULT_NUM_OF_BALLS, DEFAULT_MAX_BLOCKS_IN_ROW, true);
    }


    /**
     * Constructor.
     * @param numOfRow amount of Blocks rows in the game.
     * @param numOfBalls amount of balls in the game.
     * @param isGradual to draw the Blocks in gradual shape.
     * @param maxNumOfBlocksInRow the max number of Blocks in the same row.
     */
    public GameLevel(int numOfRow, int numOfBalls, int maxNumOfBlocksInRow, boolean isGradual) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", WIDTH, HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.numOfRow = numOfRow;
        this.numOfBalls = numOfBalls;
        this.isGradual = isGradual;

        this.maxNumOfBlocksInRow = maxNumOfBlocksInRow;
        this.blockRemover = new BlockRemover(this, new Counter(0));
        this.ballRemover = new BallRemover(this, new Counter(0));
        this.scoreTrackingListener = new ScoreTrackingListener(new Counter(0));

        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
    }


    /**
     * addCollidable.
     * @param c Collidable Object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite.
     * @param s Sprite Object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize.
     * Initialize a new game: create the Blocks and Ball (and Paddle), and add them to the game.
     */
    public void initialize() {
        this.initializeDeathBlock();
        this.initializeScore();
        this.initializePaddle();
        this.initializeBalls();
        this.initializeBoardBorder();
        this.initializeBlockRows();
    }

    /**
     * initialize the score of the game.
     */
    private void initializeScore() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreTrackingListener.getCounter());
        scoreIndicator.addToGame(this);
    }

    /**
     * initializePaddle.
     * initialize one Paddle.
     */
    private void initializePaddle() {
        Block paddleBlock = new Block(new Rectangle(new Point(PADDLE_START_X, PADDLE_START_Y)
                , Paddle.PADDLE_DEFAULT_WIDTH, Paddle.PADDLE_DEFAULT_HEIGHT), Color.RED);
        Paddle paddle = new Paddle(this.keyboardSensor, paddleBlock);
        paddle.addToGame(this);
    }

    /**
     * initializeBalls.
     * initialize multiple Balls.
     */
    public void initializeBalls() {
        for (int currentBall = 0; currentBall < this.numOfBalls; currentBall++) {
            this.initializeBall();
        }
    }

    /**
     * initializeBall.
     * initialize one Ball.
     */
    public void initializeBall() {
        Random random = new Random();

        Ball ball = new Ball(new Point(BALL_START_X + random.nextInt(RANDOM_LIMIT), BALL_START_Y)
                , BALL_RADIOS, this.environment);
        ball.setVelocity(BALL_START_VELOCITY_DX + random.nextDouble()
                , BALL_START_VELOCITY_DY + random.nextDouble());
        ball.addToGame(this);
        this.ballRemover.getCounter().increase(1);
    }

    /**
     * initializeDeathBorder.
     */
    private void initializeDeathBlock() {
        Block deathBlock = new Block(new Rectangle(new Point(0, HEIGHT)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_HORIZONTAL_BLOCK_HEIGHT), Color.RED);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(this.ballRemover);
    }
    /**
     * initializeBoardBorder.
     */
    private void initializeBoardBorder() {

        (new Block(new Rectangle(new Point(0, 25)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT), BORDER_COLOR)).addToGame(this);

        (new Block(new Rectangle(new Point(WIDTH - BORDER_VERTICAL_BLOCK_WIDTH, 25)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT), BORDER_COLOR)).addToGame(this);

        (new Block(new Rectangle(new Point(0, 25)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_HORIZONTAL_BLOCK_HEIGHT), BORDER_COLOR)).addToGame(this);
    }

    /**
     * initializeBlockRows.
     */
    private void initializeBlockRows() {
        int numOfBlocks = (int) Math.floor(MAX_BLOCKS_IN_ROW);
        numOfBlocks = Math.min(numOfBlocks, this.maxNumOfBlocksInRow);
        int startOfTheRowX = BLOCK_ROWS_X_START;
        int startOfTheRowY = BLOCK_ROWS_Y_START;

        for (int rowNumber = 0; rowNumber < this.numOfRow; rowNumber++) {
            this.initializeBlockRow(startOfTheRowX, startOfTheRowY, numOfBlocks, Ball.randomColorFromArray(rowNumber));

            // to prevent overlap with next row of Block
            startOfTheRowY += BLOCK_DEFAULT_HEIGHT;

            if (this.isGradual) {
                numOfBlocks--;
            }
        }
    }

    /**
     * initializeBlockRow.
     * @param startOfTheRowX the x coordinate of the first block in the row.
     * @param startOfTheRowY the y coordinate of the first block in the row.
     * @param numOfBlocks how much Block to create in this row.
     * @param color the Color for the Block's row.
     */
    private void initializeBlockRow(int startOfTheRowX, int startOfTheRowY, int numOfBlocks, Color color) {
        int upperLeftX = startOfTheRowX;
        int upperLeftY = startOfTheRowY;
        for (int blockNumber = 0; blockNumber < numOfBlocks; blockNumber++) {
            Block block = new Block(new Rectangle(new Point(upperLeftX, upperLeftY)
                    , BLOCK_DEFAULT_WIDTH, BLOCK_DEFAULT_HEIGHT), color);

            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.blockRemover.getCounter().increase(1);

            // to prevent overlap with next Block
            upperLeftX -= BLOCK_DEFAULT_WIDTH;
        }
    }

    /**
     * run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));

        this.running = true;
        this.runner.run(this);

        this.gui.close();
    }

    /**
     * remove Collidable from environment.
     * @param c the Collidable to remove from environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }


    /**
     * remove Sprite from sprites.
     * @param s the Sprite to remove from sprites.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * doOneFrame.
     * @param d the draw surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboardSensor));
        }

        if (this.blockRemover.getCounter().getValue() <= 0) {
            this.scoreTrackingListener.getCounter().increase(SCORE_FOR_PASSING_LEVEL);
            this.running = false;
        } else if (this.ballRemover.getCounter().getValue() <= 0) {
            this.running = false;
        }
    }

    /**
     * shouldStop.
     * @return if the animation should stop running.
     */
    @Override
    public boolean shouldStop() {
        return (!this.running);
    }
}