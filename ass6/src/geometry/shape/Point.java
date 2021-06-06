package geometry.shape;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * one decimal point.
 */
public class Point {
    public static final double COMPARISON_THRESHOLD = 0.00001;
    static final int DEFAULT_X = 0;
    static final int DEFAULT_Y = 0;

    private double x;
    private double y;

    /**
     * constructor.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor.
     */
    public Point() {
        this(DEFAULT_X, DEFAULT_Y);
    }

    /**
     * distance.
     * @param other the point to get the distance from.
     * @return distance of this point to the other point.
     */
    public double distance(Point other) {
        //sqrt((x1-x2)^2 + (y1-y2)^2)
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * equals.
     * @param other the point to check if equal to.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        } else if (isDoubleTheSame(this.x, other.x) && isDoubleTheSame(this.y, other.y)) {
            return true;
        }

        return false;
    }

    /**
     * getX.
     * @return x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY.
     * @return y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setX.
     * @param newX the new x coordinate.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * setY.
     * @param newY the new x coordinate.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * drawPoint.
     * draw the Point in RED
     * @param d the DrawSurface.
     * @param radios the radios to draw the point eith.
     */
    public void drawPoint(DrawSurface d, int radios) {
        if (this == null) {
            System.out.println("*****point is null*****");
            return;
        }

        d.setColor(Color.RED);
        d.fillCircle((int) this.x, (int) this.y, radios);
    }

    /**
     * isDoubleTheSame.
     * Check if tow number are the same until 0.00001.
     * @param num1 the first number.
     * @param num2 the second number.
     * @return true if equals, and false otherwise.
     */
    public static boolean isDoubleTheSame(double num1, double num2) {
        if (num1 - COMPARISON_THRESHOLD <= num2 && num2 <= num1 + COMPARISON_THRESHOLD) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return new String("(" + this.getX() + "," + this.getY() + ")");
    }
}
