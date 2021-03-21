import biuoop.DrawSurface;

import java.awt.Color;

public class Point {
    final static double Comparison_threshold = 0.00001;
    final static int DEFAULT_X = 0;
    final static int DEFAULT_Y = 0;

    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(DEFAULT_X, DEFAULT_Y);
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        //sqrt((x1-x2)^2 + (y1-y2)^2)
        return Math.sqrt((this.x-other.x)*(this.x-other.x) + (this.y-other.y)*(this.y-other.y));
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if(isDoubleTheSame(this.x, other.x) && isDoubleTheSame(this.y, other.y))
            return true;
        return false;
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    //draw the Point in RED
    public void drawPoint(DrawSurface d, int radios) {
        if(this == null) {
            System.out.println("*****point is null*****");
            return;
        }
        d.setColor(Color.RED);
        d.fillCircle((int)this.x, (int)this.y, radios);
    }

    //Check if tow number are the same until 0.00001
    public static boolean isDoubleTheSame(double num1, double num2) {
        if(num1 - Comparison_threshold <= num2 && num2 <= num1 + Comparison_threshold) {
            return true;
        }

        return false;
    }
}
