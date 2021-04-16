import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;

public class Game {

    //Board Border
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_VERTICAL_BLOCK_WIDTH = 5;
    public static final int BORDER_VERTICAL_BLOCK_HEIGHT = HEIGHT;
    public static final int BORDER_HORIZONTAL_BLOCK_WIDTH = WIDTH;
    public static final int BORDER_HORIZONTAL_BLOCK_HEIGHT = 5;

    //Ball setup
    public static final double BALL_START_X = WIDTH / 2;
    public static final double BALL_START_Y = HEIGHT * 3 /4;
    public static final int BALL_RADIOS = 9;

    //Blocks setup
    public static final int ROWS_NUMBER = 9;
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

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
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
        Block paddleBlock = new Block(new Rectangle(new Point(PADDLE_START_X,PADDLE_START_Y)
                        , Paddle.PADDLE_DEFAULT_WIDTH, Paddle.PADDLE_DEFAULT_HEIGHT), true, Color.RED);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), paddleBlock);
        paddle.addToGame(this);

        Ball ball = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIOS, this.environment);
        ball.addToGame(this);

        this.initializeBoardBorder();
        this.initializeBlockRows(true);
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
    private void initializeBlockRows(boolean gradual) {
        int numOfBlocks = (int) Math.floor(MAX_BLOCKS_IN_ROW);
        int startOfTheRowX = BLOCK_ROWS_X_START;
        int startOfTheRowY = BLOCK_ROWS_Y_START;

        for (int rownumber = 0; rownumber < ROWS_NUMBER; rownumber++) {
            this.initializeBlockRow(startOfTheRowX, startOfTheRowY, numOfBlocks, Ball.randomColor());

            // to prevent overlap with next row of Block
            startOfTheRowY += BLOCK_DEFAULT_HEIGHT;

            if (gradual == true) {
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