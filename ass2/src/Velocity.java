/**
 * @author yogev abarbanel
 * Id: 326116910
 * velocity of object.
 */
public class Velocity {
    static final double DEFAULT_DX = 0.05;
    static final double DEFAULT_DY = 0.05;

    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx velocity in x coordinate.
     * @param dy velocity in y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     */
    public Velocity() {
        this(DEFAULT_DX, DEFAULT_DY);
    }

    /**
     * fromAngleAndSpeed.
     * create Velocity by angle and speed.
     * @param angle the angle of the velocity.
     * @param speed the length of the velocity vector.
     * @return the new Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = - Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * getDx.
     * @return the velocity in x coordinate.
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy.
     * @return velocity in y coordinate.
     */
    public double getDy() {
        return dy;
    }

    /**
     * setDx.
     * @param newDx the new value of velocity in x coordinate.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * setDyx.
     * @param newDy the new value of velocity in x coordinate.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * applyToPoint.
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point to move.
     * @return the new point after it move according to it velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}