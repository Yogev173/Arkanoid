
import biuoop.DrawSurface;

import java.awt.Color;

public class Ball {
    final static double PAI = 3.14159;
    final static int DEFAULT_WIDTH = 200;
    final static int DEFAULT_HEIGHT = 200;
    final static Velocity DEFAULT_VELOCITY = new Velocity();
    final static Point DEFAULT_FRAME_EDGE = new Point();

    private Point center;
    private int radios;
    private java.awt.Color color;

    // has a default
    private Velocity velocity;
    private int width;
    private int height;
    private Point frameEdge;

    // constructor
    public Ball(Point center, int r, java.awt.Color color, Velocity v, int width, int height, Point frameEdge) {
        this.center = center;
        this.radios = r;
        this.color = color;
        this.velocity = v;
        this.width = width;
        this.height = height;
        this.frameEdge = frameEdge;
    }

    public Ball(Point center, int r, java.awt.Color color) {
        this(center, r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAME_EDGE);
    }

    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this(new Point(centerX, centerY), r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT,
                DEFAULT_FRAME_EDGE);
    }

    public Ball(Point center, int r, java.awt.Color color, double dx, double dy) {
        this(center, r, color, new Velocity(dx, dy), DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAME_EDGE);
    }

    public Ball(Point center, int r, java.awt.Color color, int width, int height) {
        this(center, r, color, DEFAULT_VELOCITY, width, height, DEFAULT_FRAME_EDGE);
    }

    public Ball(Point center, int r, java.awt.Color color, Velocity v, int width, int height) {
        this(center, r, color, v, width, height, DEFAULT_FRAME_EDGE);
    }

    // accessors
    public int getX() {
        return (int)this.center.getX();
    }

    public int getY() {
        return (int)this.center.getY();
    }

    public int getSize() {
        return (int)(this.radios * this.radios * PAI);
    }

    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int)this.center.getX(), (int)this.center.getY(), this.radios);
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    public void moveOneStep() {
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        //The edge of the frame
        int right = (int)this.frameEdge.getX() + this.width;
        int left = (int)this.frameEdge.getX();
        int up = (int)this.frameEdge.getY();
        int down = (int)this.frameEdge.getY() + this.height;

        //Checking the width - Right side
        if((int)newCenter.getX() + this.radios >= right) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(right - this.radios);
        }
        //Checking the width - Left side
        if((int)newCenter.getX() - this.radios <= left) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(left + this.radios);
        }
        //Checking the height - Up side
        if((int)newCenter.getY() - this.radios <= up) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(up + this.radios);
        }
        //Checking the width - Down side
        if((int)newCenter.getY() + this.radios >= down) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(down - this.radios);
        }

        this.center = newCenter;
    }

    public static Velocity velocityBySize(int size) {
        if(size >= 50) {
            return DEFAULT_VELOCITY;
        }

        return new Velocity( 8.6 - size / 6, 8.6 - size / 6);
    }
}