package game.levels;

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
import game.levels.LevelInformation;
import game.score.ScoreIndicator;
import game.score.ScoreTrackingListener;
import geometry.characteristics.Velocity;
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
 * Game Level.
 */
public class GameLevel implements Animation {

    //Board Border
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_VERTICAL_BLOCK_WIDTH = 10;
    public static final int BORDER_VERTICAL_BLOCK_HEIGHT = HEIGHT - 25;
    public static final int BORDER_HORIZONTAL_BLOCK_WIDTH = WIDTH;
    public static final int BORDER_HORIZONTAL_BLOCK_HEIGHT = 10;
    public static final Color BORDER_COLOR = Color.GRAY;

    //Paddle
    public static final int PADDLE_START_Y = HEIGHT - Paddle.PADDLE_DEFAULT_HEIGHT;

    //Ball
    public static final int BALL_RADIOS = 6;
    public static final double BALL_DEFAULT_SPEED = 4.0;

    //score
    public static final int SCORE_FOR_PASSING_LEVEL = 100;

    //animation
    public static final int FRAME_PER_SECOND = 60;

    //fields:
    //general
    private LevelInformation levelInformation;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboardSensor;


    //listener
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;

    //Animation related
    private AnimationRunner runner;
    private boolean running;

    /**
     * Constructor.
     * @param levelInformation information about the level.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI(levelInformation.levelName(), WIDTH, HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();

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
        this.initializeBackground();
        this.initializeDeathBlock();
        this.initializeScore();
        this.initializePaddle();
        this.initializeBalls();
        this.initializeBoardBorder();
        this.initializeBlocks();
        this.initializeName();
    }

    /**
     * initialize the background of the level.
     */
    private void initializeBackground() {
        this.levelInformation.getBackground().addToGame(this);
    }

    /**
     * initialize the name of the level.
     */
    private void initializeName() {
        (new LevelName(this.levelInformation.levelName())).addToGame(this);
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
        int startX = (WIDTH - this.levelInformation.paddleWidth()) / 2;
        Block paddleBlock = new Block(new Rectangle(new Point(startX, PADDLE_START_Y)
                , this.levelInformation.paddleWidth(), Paddle.PADDLE_DEFAULT_HEIGHT), Color.RED);
        Paddle paddle = new Paddle(this.keyboardSensor, paddleBlock);
        paddle.addToGame(this);
    }

    /**
     * initializeBalls.
     * initialize multiple Balls.
     */
    public void initializeBalls() {
        int startX = WIDTH / 2;
        int startY = PADDLE_START_Y - BALL_RADIOS;

        for (Velocity ballVelocity : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point(startX, startY)
                    , BALL_RADIOS, this.environment);
            ball.setVelocity(ballVelocity);

            ball.addToGame(this);
            this.ballRemover.getCounter().increase(1);
        }
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

    private void initializeBlocks() {
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.blockRemover.getCounter().increase(1);
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