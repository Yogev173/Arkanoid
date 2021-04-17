import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class Game {

    //Board Border
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_VERTICAL_BLOCK_WIDTH = 10;
    public static final int BORDER_VERTICAL_BLOCK_HEIGHT = HEIGHT;
    public static final int BORDER_HORIZONTAL_BLOCK_WIDTH = WIDTH;
    public static final int BORDER_HORIZONTAL_BLOCK_HEIGHT = 10;
    public static final int DEFAULT_MAX_BLOCKS_IN_ROW = Integer.MAX_VALUE;

    //Ball setup
    public static final int DEFAULT_NUM_OF_BALLS = 1;
    public static final double BALL_START_X = WIDTH / 2;
    public static final double BALL_START_Y = HEIGHT * 3 /4;
    public static final int BALL_RADIOS = 6;
    public static final double BALL_START_VELOCITY_DX = 0;
    public static final double BALL_START_VELOCITY_DY = -3.5;
    public static final int RANDOM_LIMIT = (int) Paddle.PADDLE_DEFAULT_WIDTH / 2;

    //Blocks setup
    public static final int DEFAULT_ROWS_NUMBER = 6;
    public static final int BLOCK_DEFAULT_WIDTH = Block.DEFAULT_WIDTH;
    public static final int BLOCK_DEFAULT_HEIGHT = Block.DEFAULT_HEIGHT;

    public static final int BLOCK_ROWS_X_START = WIDTH - BLOCK_DEFAULT_WIDTH - BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int BLOCK_ROWS_Y_START = HEIGHT * 1 /4;
    public static final int BLOCK_ROWS_WIDTH_BORDER = WIDTH - 2 * BORDER_VERTICAL_BLOCK_WIDTH;
    public static final int MAX_BLOCKS_IN_ROW = BLOCK_ROWS_WIDTH_BORDER / BLOCK_DEFAULT_WIDTH;

    //Paddle
    public static final double PADDLE_START_X = WIDTH / 2 - Paddle.PADDLE_DEFAULT_WIDTH / 2;
    public static final double PADDLE_START_Y = BALL_START_Y + BALL_RADIOS;


    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int numOfRow;
    private int numOfBalls;
    private boolean isGradual;
    private int maxNumOfBlocksInRow;

    /**
     * Constructor.
     */
    public Game() {
        this(DEFAULT_ROWS_NUMBER, DEFAULT_NUM_OF_BALLS, DEFAULT_MAX_BLOCKS_IN_ROW, true);
    }


    /**
     * Constructor.
     * @param numOfRow amount of Blocks rows in the game.
     * @param numOfBalls amount of balls in the game.
     * @param isGradual to draw the Blocks in gradual shape.
     * @param maxNumOfBlocksInRow the max number of Blocks in the same row.
     */
    public Game(int numOfRow, int numOfBalls, int maxNumOfBlocksInRow, boolean isGradual) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.numOfRow = numOfRow;
        this.numOfBalls = numOfBalls;
        this.isGradual = isGradual;
        this.maxNumOfBlocksInRow = maxNumOfBlocksInRow;
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
        gui = new GUI("Game", WIDTH, HEIGHT);
        this.initializePaddle();
        this.initializeBalls();
        this.initializeBoardBorder();
        this.initializeBlockRows();
    }

    /**
     * initializePaddle.
     * initialize one Paddle.
     */
    private void initializePaddle() {
        Block paddleBlock = new Block(new Rectangle(new Point(PADDLE_START_X,PADDLE_START_Y)
                , Paddle.PADDLE_DEFAULT_WIDTH, Paddle.PADDLE_DEFAULT_HEIGHT), true, Color.RED);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), paddleBlock);
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
    }

    /**
     * initializeBoardBorder.
     */
    private void initializeBoardBorder() {

        (new Block(new Rectangle(new Point(0,0)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT), true)).addToGame(this);

        (new Block(new Rectangle(new Point(WIDTH - BORDER_VERTICAL_BLOCK_WIDTH,0)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT), true)).addToGame(this);

        (new Block(new Rectangle(new Point(0,0)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_HORIZONTAL_BLOCK_HEIGHT), true)).addToGame(this);

        (new Block(new Rectangle(new Point(0,HEIGHT - BORDER_HORIZONTAL_BLOCK_HEIGHT)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_HORIZONTAL_BLOCK_HEIGHT), true)).addToGame(this);


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

            if (this.isGradual == true) {
                numOfBlocks--;
            }
        }
    }

    /**
     * initializeBlockRow
     */
    private void initializeBlockRow(int startOfTheRowX, int startOfTheRowY, int numOfBlocks, Color color) {
        int upperLeftX = startOfTheRowX;
        int upperLeftY = startOfTheRowY;
        for (int blockNumber = 0; blockNumber < numOfBlocks; blockNumber++) {
            Block block = new Block(new Rectangle(new Point(upperLeftX, upperLeftY)
                    , BLOCK_DEFAULT_WIDTH, BLOCK_DEFAULT_HEIGHT), false, color);
            block.addToGame(this);

            // to prevent overlap with next Block
            upperLeftX -= BLOCK_DEFAULT_WIDTH;
        }
    }

    /**
     * run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        //Animation
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}