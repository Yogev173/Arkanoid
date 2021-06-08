package geometry.shape;

import biuoop.DrawSurface;
import geometry.characteristics.PointByDistance;

import java.awt.Color;
import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * one Line.
 */
public class Line {
    private Point start;
    private Point end;
    private Color color;
    static final double COMPARISON_THRESHOLD = Point.COMPARISON_THRESHOLD;

    /**
     * Constructor.
     * @param start start point of the line.
     * @param end end point of the line.
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Constructor.
     * @param start start point of the line.
     * @param end end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = Color.BLACK;
    }

    /**
     * Constructor.
     * @param xStart the x coordinate of the start of the line.
     * @param yStart the y coordinate of the start of the line.
     * @param xEnd the x coordinate of the end of the line.
     * @param yEnd the y coordinate of the end of the line.
     */
    public Line(double xStart, double yStart, double xEnd, double yEnd) {
        this(new Point(xStart, yStart), new Point(xEnd, yEnd));
    }

    /**
     * length.
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle.
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * start.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * end.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting.
     * @param other the line to check if intersect with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        /*In case they parallel, so there is a lot of intersection point, the intersectionWith will return null,
        but these function should return true. */
        if (other.isInLine(this.start) || other.isInLine(this.end)
                || this.isInLine(other.start) || this.isInLine(other.end)) {
            return true;
        } else if (this.intersectionWith(other) != null) {
            return true;
        }

        return false;
    }

    /**
     * intersectionWith.
     * @param other the line to find intersection with.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //If the one of the lines is a single point
        if (this.start.equals(this.end)) {
            if (other.isInLine(this.start)) {
                return this.start;
            }

            return null;
        } else if (other.start.equals(other.end)) {
            if (this.isInLine(other.start)) {
                return other.start;
            }

            return null;
        }

        //checking if the incline is infinite
        if (this.isInclineInfinity()) {
            return this.inclineInfinityIntersection(other);
        } else if (other.isInclineInfinity()) {
            return other.inclineInfinityIntersection(this);
        }

        return this.regularLineIntersection(other);
    }

    /**
     * regularLineIntersection.
     * found intersection between tow Line that aren't parallel or with infinity incline.
     * @param other Line to return intersection Point with.
     * @return the intersection Point if exist, null otherwise.
     */
    private Point regularLineIntersection(Line other) {
        //Getting m and c in the equation: y = m*x + c.
        double incline1 = this.incline();
        double incline2 = other.incline();
        double cConst1 = this.cConst();
        double cConst2 = other.cConst();

        //If the two lines are parallel and do not merge
        if (Point.isDoubleTheSame(incline1, incline2)) {
            return parallelsLineIntersection(this.start, this.end, other.start, other.end);
        }

        /* y = m1*x + c1 , y = m2*x + c2
        => m1*x + c1 = m2*x + c2
        => x = (c1 - c2) / (m2 - m1); */
        double pointX = (cConst1 - cConst2) / (incline2 - incline1);
        double pointY1 = incline1 * pointX + cConst1;
        double pointY2 = incline2 * pointX + cConst2;

        //Checking if the points are the same (we already know the x value equals),and inside the lines.
        Point point = new Point(pointX, pointY1);
        if (Point.isDoubleTheSame(pointY1, pointY2) && this.isInLine(point) && other.isInLine(point)) {
            return point;
        }

        return null;
    }

    /**
     * incline.
     * @return the incline of line.
     */
    public double incline() {
        double xDifference = this.start.getX() - this.end.getX();
        double yDifference = this.start.getY() - this.end.getY();
        return (yDifference / xDifference);
    }

    /**
     * cConst.
     * @return the--c (the const) in the equation: y = m*x + c.
     */
    public double cConst() {
        double incline = this.incline();
        //c = y-m*x
        return (this.start.getY() - incline * this.start.getX());
    }

    /**
     * isInLine.
     * Check if a point is inside the line.
     * @param point the point to check if inside the line.
     * @return true if the Point inside the Line, else return false.
     */
    public boolean isInLine(Point point) {
        double lineLength = this.length();

        //if the point in the line the distance from it node will be equal to the length
        if (Point.isDoubleTheSame(lineLength, this.start.distance(point) + this.end.distance(point))) {
            return true;
        }
        return false;
    }

    /**
     * isInclineInfinity.
     * @return true if a line incline is infinity, otherwise false.
     */
    private boolean isInclineInfinity() {
        return (this.start.getX() - this.end.getX() == 0);
    }

    /**
     * inclineInfinityIntersection.
     * @param other the line to find intersection with.
     * @return the intersection point of tow lines if one of them has infinity incline.
     */
    private Point inclineInfinityIntersection(Line other) {
        //Checks if the other line also has a infinite incline.
        if (other.isInclineInfinity()) {
            Point intersection = parallelsLineIntersection(this.start, this.end, other.start, other.end);
            return intersection;
        }

        // founding the y-coordinate according to the other line
        double x = this.start.getX();
        Point point = new Point(x, other.incline() * x + other.cConst());
        if (this.isInLine(point) && other.isInLine(point)) {
            return point;
        }

        return null;
    }

    /**
     * parallelsLineIntersection.
     * @param p1 start point of first line.
     * @param p2 end point of first line.
     * @param p3 start point of second line.
     * @param p4 end point of second line.
     * @return the intersection point if exists, otherwise return null;
     */
    private static Point parallelsLineIntersection(Point p1, Point p2, Point p3, Point p4) {
        /* If tow line are parallel the only option they has one intersection point,
         it's if they connected only at the edge */
        if (p1.equals(p3)) {
            if (!isLineMerge(p1, p2, p4)) {
                return p1;
            }
        } else if (p1.equals(p4)) {
            if (!isLineMerge(p1, p2, p3)) {
                return p1;
            }
        } else if (p2.equals(p3)) {
            if (!isLineMerge(p2, p1, p4)) {
                return p2;
            }
        } else if (p2.equals(p4)) {
            if (!isLineMerge(p2, p1, p3)) {
                return p2;
            }
        }

        return null;
    }

    /**
     * isLineMerge.
     * merge = has more then one intersection point.
     * @param intersection the shared point of tow lines.
     * @param p1 first line other point.
     * @param p2 second line other point.
     * @return true if the line merge, and false otherwise.
     */
    private static boolean isLineMerge(Point intersection, Point p1, Point p2) {
        /* we know the line has a shared edge, so it checked if the other edge are on the same "side" of the line
        relative to the shared point (if so they has more then one intersection point. */
        if (intersection.getX() < p1.getX() && intersection.getX() > p2.getX()) {
            return false;
        } else if (intersection.getX() > p1.getX() && intersection.getX() < p2.getX()) {
            return false;
        } else if (intersection.getY() < p1.getY() && intersection.getY() > p2.getY()) {
            return false;
        } else if (intersection.getY() > p1.getY() && intersection.getY() < p2.getY()) {
            return false;
        }

        return true;
    }

    /**
     * equals.
     * @param other the line to check if equal to.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }

        return false;
    }

    /**
     * drawLine.
     *  draw a line in BLACK.
     * @param d the DrawSurface.
     */
    public void drawLine(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * draw middle.
     * draw the middle of a line in BLUE.
     * @param d the DrawSurface.
     * @param radios the radios to draw the point.
     */
    public void drawMiddle(DrawSurface d, int radios) {
        Point middle = this.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) middle.getX(), (int) middle.getY(), radios);
    }


    /**
     * closestIntersectionToStartOfLine.
     * @param rect the Rectangle.
     * @return If this line does not intersect with the rectangle, return null.
     *      Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }

        //sorting to find the closet
        PointByDistance comparator = new PointByDistance(this.start());
        points.sort(comparator);

        return points.get(0);
    }

    /**
     * toString.
     * @return String represent the Line.
     */
    @Override
    public String toString() {
        return new String("{ " + this.start.toString() + " , " + this.end.toString()
                + " }");
    }
}
