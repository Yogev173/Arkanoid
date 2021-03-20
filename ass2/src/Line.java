import javax.swing.*;

public class Line {
    private Point start;
    private Point end;

    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x2, y2), new Point(x1, y1));
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX())/2;
        double middleY = (this.start.getY() + this.end.getY())/2;
        return new Point(middleX, middleY);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if(this.intersectionWith(other) != null)
            return true;
        return false;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        //checking if the incline is infinite
        if(this.isInclineInfinity()) {
            return this.inclineInfinityIntersection(other);
        } else if(other.isInclineInfinity()) {
            return other.inclineInfinityIntersection(this);
        }

        //Getting m and c in the equation: y = m*x + c.
        double incline1 = this.incline();
        double incline2 = other.incline();
        double cConst1 = this.cConst();
        double cConst2 = other.cConst();

        //If the two lines are parallel and do not merge
        if(incline1 == incline2)
            return null;

        /*
        y = m1*x + c1 , y = m2*x + c2
        => m1*x + c1 = m2*x + c2
        => x = (c1 - c2) / (m2 - m1);
         */
        double pointX = (cConst1 - cConst2) / (incline2 - incline1);
        double pointY1 = incline1 * pointX + cConst1;
        double pointY2 = incline2 * pointX + cConst2;

        //Checking if the points are the same (we already know the x value equals),and inside the lines.
        Point point = new Point(pointX, pointY1);
        if(pointY1 == pointY2 && this.isInLine(point) && other.isInLine(point)) {
            return point;
        }

        return null;
    }

    //Returns the incline of line.
    public double incline(){
        double xDifference = this.start.getX() - this.end.getX();
        double yDifference = this.start.getY() - this.end.getY();
        return (yDifference / xDifference);
    }

    //Returns the--c in the equation: y = m*x + c.
    public double cConst(){
        double incline = this.incline();
        return (this.start.getY() - incline * this.start.getX());
    }

    //Returns-true if the Point inside the Line, else return false.
    //if the point in the line the dictance from it node will be equal to the length
    public boolean isInLine(Point point){
        double lineLength = this.length();
        if(lineLength == (this.start.distance(point) + this.end.distance(point))){
            return true;
        }
        return false;
    }

    //Returns if a line incline is infinity
    private boolean isInclineInfinity(){
        return (this.start.getX() - this.end.getX() == 0);
    }

    //Returns the intersection point of tow lines if one of them has infinity incline
    //*
    private Point inclineInfinityIntersection(Line other){
        if(other.isInclineInfinity()) {
            return null;
        }

        double x = this.start.getX();
        return new Point(x, other.incline() * x + other.cConst());
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if(this.start.equals(other.start) && this.end.equals(other.end))
            return true;
        else if(this.start.equals(other.end) && this.end.equals(other.start))
            return true;
        else
            return false;
    }
}
