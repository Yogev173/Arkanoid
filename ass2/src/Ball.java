import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * one ball.
 */
public class Ball {
    static final double PAI = 3.14159;
    static final int DEFAULT_WIDTH = 200;
    static final int DEFAULT_HEIGHT = 200;
    static final Point DEFAULT_FRAME_EDGE = new Point();
    static final Color DEFAULT_COLOR = Color.BLACK;

    private Point center;
    private int radios;
    private Color color;

    // has a default
    private Velocity velocity;
    private int width;
    private int height;
    private Point frameEdge;

    /**
     * Constructor.
     * @param center the center of the ball
     * @param r the radios of the bll
     * @param color the color of the ball
     * @param v the velocity of the ball
     * @param width the width of the ball bounds
     * @param height the height of the ball height
     * @param frameEdge the top left corner of the ball bounds
     */
    public Ball(Point center, int r, Color color, Velocity v, int width, int height, Point frameEdge) {
        this.center = center;
        this.radios = adjustRadiosToFrame(r, width, height);
        this.color = color;
        this.velocity = v;
        this.width = width;
        this.height = height;
        this.frameEdge = frameEdge;
    }

    /**
     * Constructor.
     * @param centerX the x coordinate center of the ball
     * @param centerY the y coordinate center of the ball
     * @param r the radios of the bll
     * @param v the velocity of the ball
     * @param width the width of the ball bounds
     * @param height the height of the ball height
     * @param frameEdge the top left corner of the ball bounds
     */
    public Ball(int centerX, int centerY, int r, Velocity v, int width, int height, Point frameEdge) {
        this(new Point(centerX, centerY), r, DEFAULT_COLOR, v, width, height, frameEdge);
    }

    /**
     * Constructor.
     * @param centerX the x center coordinate of the ball
     * @param centerY the y center coordinate of the ball
     * @param r the radios of the ball
     * @param color the color of the ball
     */
    public Ball(double centerX, double centerY, int r, Color color) {
        this(new Point(centerX, centerY), r, color, new Velocity(), DEFAULT_WIDTH, DEFAULT_HEIGHT,
                DEFAULT_FRAME_EDGE);
    }

    /**
     * Constructor.
     * @param center the center of the ball
     * @param r the radios of the bll
     * @param color the color of the ball
     * @param v the velocity of the ball
     * @param width the width of the ball bounds
     * @param height the height of the ball height
     */
    public Ball(Point center, int r, Color color, Velocity v, int width, int height) {
        this(center, r, color, v, width, height, DEFAULT_FRAME_EDGE);
    }

    /**
     * Constructor.
     * @param center the center of the ball
     * @param r the radios of the bll
     * @param color the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this(center, r, color, new Velocity(), DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAME_EDGE);
    }

    /**
     * Constructor.
     * @param centerX the x center coordinate of the ball
     * @param centerY the Y center coordinate of the ball
     * @param r the radios of the bll
     * @param color the color of the ball
     */
    public Ball(int centerX, int centerY, int r, Color color) {
        this(new Point(centerX, centerY), r, color,
                new Velocity(), DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAME_EDGE);
    }

    /**
     * randomBall.
     * @param width the width of the ball bounds
     * @param height the height of the ball height
     * @param frameEdge the top left corner of the ball bounds
     * @return the new Ball
     */
    public static Ball randomBall(int width, int height, Point frameEdge) {
        Random rand = new Random();
        int maxBoundRadios = (width < height ? width : height) / 3;
        int radios = rand.nextInt(maxBoundRadios) + 1;
        int xEdge = (int) frameEdge.getX();
        int yEdge = (int) frameEdge.getY();

        // xBound = width - 2r
        int xBound = width - 2 * radios;
        int  centerX = rand.nextInt(xBound) + xEdge + radios + 1;

        // yBound = height - 2r
        int yBound = height - 2 * radios;
        int centerY = rand.nextInt(yBound) + yEdge + radios + 1;
        Point center = new Point(centerX, centerY);

        return new Ball(center, radios, randomColor(), Ball.velocityBySize(radios), width, height,
                new Point(xEdge, yEdge));
    }

    /**
     * randomBall.
     * @param width the width of the ball bounds
     * @param height the height of the ball height
     * @param frameEdge the top left corner of the ball bounds
     * @param radios the radios of the ball.
     * @return the new Ball.
     */
    public static Ball randomBall(int width, int height, Point frameEdge, int radios) {
        Random rand = new Random();
        int xEdge = (int) frameEdge.getX();
        int yEdge = (int) frameEdge.getY();

        // xBound = width - 2r
        int xBound = width - 2 * radios;
        int  centerX = rand.nextInt(xBound) + xEdge + radios + 1;

        // yBound = height - 2r
        int yBound = height - 2 * radios;
        int centerY = rand.nextInt(yBound) + yEdge + radios + 1;
        Point center = new Point(centerX, centerY);

        return new Ball(center, radios, randomColor(), Ball.velocityBySize(radios), width, height,
                new Point(xEdge, yEdge));
    }

    /**
     * adjustRadiosToFrame.
     * in cases the radios of the Ball is to big, it adjust it to be suitable for the frame size.
     * @param currentRadios current radios
     * @param currentWidth width of Ball frame
     * @param currentHeight height of Ball frame
     * @return radios that suitable for the frame
     */
    private int adjustRadiosToFrame(int currentRadios, int currentWidth, int currentHeight) {
        if (currentRadios * 2 >  currentWidth) {
            currentRadios = (int) (currentWidth / 2.1);
        }

        if (currentRadios * 2 >  currentHeight) {
            currentRadios = (int) (currentWidth / 2.1);
        }

        return currentRadios;
    }

    /**
     * getX.
     * @return the x coordinate of the ball center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     * @return the y coordinate of the ball center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     * @return the size of the ball.
     */
    public int getSize() {
        return (int) (this.radios * this.radios * PAI);
    }

    /**
     * getColor.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * setVelocity.
     * @param v the new velocity for the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity.
     * @param dx the new x velocity for the ball.
     * @param dy the y velocity for the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * getVelocity.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * drawOn.
     * draw the ball on the DrawSurface.
     * @param surface the DrawSurface of the GUI.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radios);
    }

    /**
     * moveOneStep.
     * move the ball one time according to his velocity, if it go out of the ball bounds, it reverses its direction of
     * velocity in the same axis, and return it to the bounds it allowed in.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        //The edge of the frame
        int rightBorder = (int) this.frameEdge.getX() + this.width;
        int leftBorder = (int) this.frameEdge.getX();
        int upperBorder = (int) this.frameEdge.getY();
        int downBorder = (int) this.frameEdge.getY() + this.height;

        //Checking the ball don't go out of its bounds.
        //Checking the width - Right side
        rightBorderCheck(this, rightBorder);
        //Checking the width - Left side
        leftBorderCheck(this, leftBorder);
        //Checking the height - upper side
        upperBorderCheck(this, upperBorder);
        //Checking the width - Down side
        downBorderCheck(this, downBorder);
    }

    /**
     * rightBorderCheck.
     * check if the ball cross his right border, if so it reverse it x velocity,
     * and getting it center to the right side of the border.
     * @param newBall Ball to chek if pass the right border.
     * @param rightBorder the coordinate of the right border.
     */
    public void rightBorderCheck(Ball newBall, int rightBorder) {
        Point newCenter = newBall.center;
        if ((int) newCenter.getX() + this.radios > rightBorder) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(rightBorder - this.radios);
        }

        this.center = newCenter;
    }

    /**
     * leftBorderCheck.
     * check if the ball cross his right border, if so it reverse it x velocity,
     * and getting it center to the right side of the border.
     * @param newBall Ball to chek if pass the left border.
     * @param leftBorder the coordinate of the left border.
     */
    public void leftBorderCheck(Ball newBall, int leftBorder) {
        Point newCenter = newBall.center;
        if ((int) newCenter.getX() - this.radios < leftBorder) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(leftBorder + this.radios);
        }

        this.center = newCenter;
    }

    /**
     * upperBorderCheck.
     * check if the ball cross his right border, if so it reverse it y velocity,
     * and getting it center to the right side of the border.
     * @param newBall Ball to chek if pass the left border.
     * @param upperBorder the coordinate of the upper border.
     */
    public void upperBorderCheck(Ball newBall, int upperBorder) {
        Point newCenter = newBall.center;
        if ((int) newCenter.getY() - this.radios < upperBorder) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(upperBorder + this.radios);
        }

        this.center = newCenter;
    }

    /**
     * downBorderCheck.
     * check if the ball cross his right border, if so it reverse it y velocity,
     * and getting it center to the right side of the border.
     * @param newBall Ball to chek if pass the down border.
     * @param downBorder the coordinate of the down border.
     */
    public void downBorderCheck(Ball newBall, int downBorder) {
        Point newCenter = newBall.center;
        if ((int) newCenter.getY() + this.radios > downBorder) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(downBorder - this.radios);
        }

        this.center = newCenter;
    }

    /**
     * velocityBySize.
     * calculate the velocity the ball should have, according to its size
     * (all balls above radios 50 has the same velocity)
     * @param radios the radios of the ball.
     * @return the velocity the ball should have.
     */
    public static Velocity velocityBySize(int radios) {
        if (radios >= 50) {
            return new Velocity();
        }

        return new Velocity((4.75 - radios / 12.0) / 5, (4.75 - radios / 12.0) / 5);
    }

    /**
     * randomColor.
     * @return the color
     */
    public static Color randomColor() {
        Random rnd = new Random();
        int red = rnd.nextInt();
        int green = rnd.nextInt();
        int blue = rnd.nextInt();
        return new Color(red, green, blue);
    }
}