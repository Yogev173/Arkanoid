import java.util.ArrayList;
import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * one react angle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor.
     * Create a new Rectangle with location and width/height.
     * @param upperLeft the upper left edge of the Rectangle.
     * @param width the width of the Rectangle.
     * @param height the height of the Rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     * @param upperLeft the upper left edge of the Rectangle.
     * @param upperRight the upper right edge of the Rectangle.
     * @param lowerLeft the lower left edge of the Rectangle.
     */
    public Rectangle(Point upperLeft, Point upperRight, Point lowerLeft) {
        this(upperLeft, Math.abs(upperRight.getX() - upperLeft.getX()), Math.abs(upperLeft.getY() - lowerLeft.getY()));
    }

    /**
     * intersectionPoints.
     * @param line line to return intersection point with.
     * @return List (possibly empty) of intersection point between the Rectangle and the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<Point>();
        intersectionList.add(line.intersectionWith(this.getUpperSide()));
        intersectionList.add(line.intersectionWith(this.getLowerSide()));
        intersectionList.add(line.intersectionWith(this.getLeftSide()));
        intersectionList.add(line.intersectionWith(this.getRightSide()));

        int intersectionListSize = intersectionList.size();
        for (int i = 0; i < intersectionListSize; i++) {
            if (intersectionList.get(i) == null) {
                intersectionList.remove(i);
                intersectionListSize--;
                i--;
            }
        }

        return intersectionList;
    }

    //Field Getters

    /**
     * getWidth.
     * @return the width of the Rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight.
     * @return the height of the Rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    //Edge point Getters

    /**
     * getUpperLeft.
     * @return the upper-left point of the Rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getUpperRight.
     * @return the upper-Right point of the Rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
    }

    /**
     * getLowerLeft.
     * @return the lower-left point of the Rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * getLowerRight.
     * @return the lower-right point of the Rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    //Rectangle sides Getters

    /**
     * getUpperSide.
     * @return the upper side of the Rectangle.
     */
    public Line getUpperSide() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * getLowerSide.
     * @return the lower side of the Rectangle.
     */
    public Line getLowerSide() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }

    /**
     * getLeftSide.
     * @return the left side of the Rectangle.
     */
    public Line getLeftSide() {
        return new Line(this.getUpperLeft(), this.getLowerLeft());
    }

    /**
     * getRightSide.
     * @return the right side of the Rectangle.
     */
    public Line getRightSide() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }
}
