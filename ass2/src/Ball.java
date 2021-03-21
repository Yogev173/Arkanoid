
import biuoop.DrawSurface;

import java.awt.Color;

public class Ball {
    final static double PAI = 3.14159;
    final static int DEFAULT_WIDTH = 200;
    final static int DEFAULT_HEIGHT = 200;
    final static Velocity DEFAULT_VELOCITY = new Velocity();

    private Point center;
    private int radios;
    private java.awt.Color color;

    // has a default
    private Velocity velocity;
    private int width;
    private int height;

    // constructor
    public Ball(Point center, int r, java.awt.Color color, Velocity v, int width, int height) {
        this.center = center;
        this.radios = r;
        this.color = color;
        this.velocity = v;
        this.width = width;
        this.height = height;
    }

    public Ball(Point center, int r, java.awt.Color color) {
        this(center, r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this(new Point(centerX, centerY), r, color, DEFAULT_VELOCITY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Ball(Point center, int r, java.awt.Color color, double dx, double dy) {
        this(center, r, color, new Velocity(dx, dy), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Ball(Point center, int r, java.awt.Color color, int width, int height) {
        this(center, r, color, DEFAULT_VELOCITY, width, height);
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
        //Checking the width - Right side
        if((int)newCenter.getX() + this.radios >= this.width) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(this.width - this.radios);
        }
        //Checking the width - Left side
        if((int)newCenter.getX() - this.radios <= 0) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter.setX(this.radios);
        }
        //Checking the height - Up side
        if((int)newCenter.getY() - this.radios <= 0) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(this.radios);
        }
        //Checking the width - Down side
        if((int)newCenter.getY() + this.radios >= this.height) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter.setY(this.height - this.radios);
        }

        this.center = newCenter;
    }
}