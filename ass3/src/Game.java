import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.Sleeper;

public class Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_VERTICAL_BLOCK_WIDTH = 5;
    public static final int BORDER_VERTICAL_BLOCK_HEIGHT = HEIGHT;
    public static final int BORDER_HORIZONTAL_BLOCK_WIDTH = WIDTH;
    public static final int BORDER_HORIZONTAL_BLOCK_HEIGHT = 5;

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
        this.initializeBoardBorder();
    }

    private void initializeBoardBorder() {

        (new Block(new Rectangle(new Point(0,0)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT))).addToGame(this);

        (new Block(new Rectangle(new Point(WIDTH - BORDER_VERTICAL_BLOCK_WIDTH,0)
                , BORDER_VERTICAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT))).addToGame(this);

        (new Block(new Rectangle(new Point(0,0)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT))).addToGame(this);

        (new Block(new Rectangle(new Point(0,HEIGHT - BORDER_HORIZONTAL_BLOCK_HEIGHT)
                , BORDER_HORIZONTAL_BLOCK_WIDTH, BORDER_VERTICAL_BLOCK_HEIGHT))).addToGame(this);


    }

    // Run the game -- start the animation loop.
    public void run();
}