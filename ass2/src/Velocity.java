// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    final static double DEFAULT_DX = 5.0;
    final static double DEFAULT_DY = 5.0;

    private double dx;
    private double dy;

    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Velocity() {
        this(DEFAULT_DX, DEFAULT_DY);
    }

    // accessors
    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}