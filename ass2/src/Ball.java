import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author yogev abarbanel
 * Id: 326116910
 * one ball
 */
public class Ball {
    static final double PAI = 3.14159;
    static final int DEFAULT_WIDTH = 200;
    static final int DEFAULT_HEIGHT = 200;
    static final Velocity DEFAULT_VELOCITY = new Velocity();
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
        this.radios = r;
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
     * @param centerX the x coordinate center of the ball
     * @param centerY the y coordinate center of the ball
     * @param r the radios of the ball
     * @param color the color of the ball
     */
    public Ball(double centerX, double centerY, int r, Color color) {
        this(new Point(centerX, centerY), r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT,
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
        this(center, r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAME_EDGE);
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
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        //The edge of the frame
        int right = (int) this.frameEdge.getX() + this.width;
        int left = (int) this.frameEdge.getX();
        int up = (int) this.frameEdge.getY();
        int down = (int) this.frameEdge.getY() + this.height;

        //Checking the ball don't go out of its bounds.
        //Checking the width - Right side
        if ((int) newCenter.getX() + this.radios > right) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(right - this.radios);
        }
        //Checking the width - Left side
        if ((int) newCenter.getX() - this.radios < left) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(left + this.radios);
        }
        //Checking the height - Up side
        if ((int) newCenter.getY() - this.radios < up) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(up + this.radios);
        }
        //Checking the width - Down side
        if ((int) newCenter.getY() + this.radios > down) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(down - this.radios);
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
            return DEFAULT_VELOCITY;
        }

        return new Velocity((4.75 - radios / 12)/10, (4.75 - radios / 12)/10);
    }
}