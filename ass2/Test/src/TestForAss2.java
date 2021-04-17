import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestForAss2 {
    public static final int SLEEP = 50;

    private Velocity previousV = null;
    private Point previousP = null;

    public boolean isInRangeOfEpsilon(double x, double y) {
        double epsilon = 0.000000000000001;
        return x >= y - epsilon && x <= y + epsilon;
    }

    public void checkPoint(){
        Point p1 = new Point(3.5, 4.6);
        Point p2 = new Point(3.5, 4.6);
        Point p3 = new Point(5.4, 7.8);
        Point p4 = new Point(-5.4, 7.8);
        Point p5 = new Point(-5.4, -7.8);
        Point p6 = new Point(5.4, -7.8);
        Point p7 = new Point(0.0, 0.0);

        assert (p7.getY() == 0);
        assert (p1.getX() == 3.5);
        assert (p1.getY() == 4.6);
        assert (p1.equals(p1));
        assert (p1.equals(p2));
        assert (!p1.equals(p3));
        assert (p1.distance(p1) == 0.0);
        assert (p1.distance(p2) == 0.0);

        assert(isInRangeOfEpsilon(p1.distance(p3),Math.sqrt(Math.pow(1.9, 2) + Math.pow(3.2, 2))));
        assert ((int)p1.distance(p3) == 3);
        assert(isInRangeOfEpsilon(p1.distance(p4),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(3.2, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p5),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(-12.4, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p6),Math.sqrt(Math.pow(1.9, 2) + Math.pow(-12.4, 2))));

        System.out.println("passed tests for Point");
    }



    public void checkLine() {
        Line f = new Line(49.45, 68.3,93.23, 27.3);
        Point c1 = new Point(49.45, 68.3);
        Point c2 = new Point(93.23, 27.3);
        // is same as lf
        Line l2f = new Line(c1, c2);
        Line g = new Line(151.94528, 65.76442,54.76838, 16.41678);
        // l4 and l5 parallel to y axis but don't touch
        Line h = new Line(20.0, 140.0,20.0, 120.0);
        Line i = new Line(20.0, 100.0,20.0, 60.0);

        Line j = new Line(71.72, 55.3,80.07, 64.24);
        Line k = new Line(70.52, 93.75,65.46, 62.00);

        // l8, l9 are parallel to Y axis and share one point
        Line l = new Line(40.0, 140.0,40.0, 100.0);
        Line m = new Line(40.0, 100.0,40.0, 80.0);

        // l10, l11 are parallel to X axis and share one point
        Line n = new Line(60.0, 40.0,40.0, 40.0);
        Line p = new Line(40.0, 40.0,20.0, 40.0);

        // l12, l13 are parallel to X axis and don't share any points
        Line q = new Line(60.0, 120.0,80.0, 120.0);
        Line r = new Line(100.0, 120.0,120.0, 120.0);

        Line s = new Line(102.04805, 77.17004,119.96, 49.52);

//            Line t = new Line(136.24, 57.79,180.0, 80.04);

        // a is on y axis
        Line a = new Line(0.0, 0.0,0.0, 40.0);
        Line b = new Line(9.13, 31.2,-23.16, 16.44);
        // j1 is on x axis
        Line j1 = new Line(0.0,0.0,160.0,0.0);
        //intersects with x axis
        Line k1 = new Line(120.0,20.0,120.0,-20.0);
        Line l1 = new Line(100.0,20.0,160.0,-20.0);

        Line m1 = new Line(40.0,60.0,40.0,20.0);
        Line n1 = new Line(00.0,80.0,40.0,80.0);


        Line f1 = new Line(80.0, 164.0,90.0, 184.0);
        Line g1 = new Line(85.0, 174.0,95.0, 194.0);

        Point t1 = new Point(100.0, 160.0);
        Point s1 = new Point(120.0, 160.0);
        Point r1 = new Point(1400.0, 160.0);

        //are the same but start point and end point are opposite
        Line h1 = new Line(t1,s1);
        Line lh1 = new Line(s1,t1);

        //are the same but start point and end point are opposite
        Line i1 = new Line(t1,r1);
        Line li1 = new Line(r1,t1);




        assert (f.start().equals(c1));
        assert (f.end().equals(c2));
        assert(f.middle().equals(new Point(71.34, 47.8)));
        assert (f.length() == c1.distance(c2));
        assert (f.equals(f));
        assert (f.equals(l2f));
        assert (!f.equals(g));

        assert (f.isIntersecting(l2f));//same line
        assert (f.isIntersecting(g));
        // same but opposite order
        assert (g.isIntersecting(f));
        // both parallel to y but don't touch
        assert (!h.isIntersecting(i));
        // parallel to each other
        assert (!h.isIntersecting(l));
        // perpendicular to each other, but don't touch
        assert (!i.isIntersecting(p));

        // have one in common**********************************************
        assert (l.isIntersecting(m));
        // have one in common
        assert (n.isIntersecting(p));

        assert (!q.isIntersecting(r));
        assert (!l2f.isIntersecting(k));
        assert (!l2f.isIntersecting(j));
        assert (s.isIntersecting(g));
//            assert (l15.isIntersecting(l3));
        assert(f1.isIntersecting(g1));

        assert (!k.isIntersecting(q));
        assert (b.isIntersecting(a));

        assert (f.intersectionWith(f) == null);
        assert (f.intersectionWith(l2f) == null);
//            assert (l3.intersectionWith(l15) == null);
        assert (f1.intersectionWith(g1) == null);
        assert (k.intersectionWith(f) == null);

        // lines that have other line completely in them,
        // wildest sharing a start or end point
        assert(h1.intersectionWith(lh1) == null);
        assert(h1.intersectionWith(i1) == null);
        assert(h1.intersectionWith(li1) == null);

        assert(lh1.intersectionWith(h1) == null);
        assert(lh1.intersectionWith(i1) == null);
        assert(lh1.intersectionWith(li1) == null);

        assert(i1.intersectionWith(h1) == null);
        assert(i1.intersectionWith(lh1) == null);
        assert(i1.intersectionWith(li1) == null);

        assert(li1.intersectionWith(lh1) == null);
        assert(li1.intersectionWith(i1) == null);
        assert(li1.intersectionWith(h1) == null);

        assert (n.intersectionWith(p).equals(new Point(40.0, 40.0)));
        assert (s.intersectionWith(g).equals(new Point(119.95907554822693, 49.521427043314844)));
        assert (f.intersectionWith(g).equals(new Point(87.242330580114, 32.907456514740204)));
        assert (a.intersectionWith(b).equals(new Point(0.0, 27.02660885723134)));

        //added tests
        assert(a.isIntersecting(j1));
        assert(a.intersectionWith(j1).equals(new Point(0.0,0.0)));

        assert(k1.isIntersecting(j1));
        assert(k1.intersectionWith(j1).equals(new Point(120.0,0.0)));

        assert(k1.isIntersecting(l1));
        assert(k1.intersectionWith(l1).equals(new Point(120.0,6.666666666666657)));

        assert(j1.isIntersecting(l1));
        assert(j1.intersectionWith(l1).equals(new Point(130.0,0.0)));

        assert(m1.isIntersecting(p));
        assert(m1.intersectionWith(p).equals(new Point(40.0,40.0)));

        assert(n1.isIntersecting(i));
        assert(n1.intersectionWith(i).equals(new Point(20.0,80.0)));


        System.out.println("passed tests for Line");

    }

    public void checkVelocity() {
        Velocity v1 = new Velocity(3.5, 6.7);
        Velocity v2 = new Velocity(-3.5, 6.7);
        Velocity v3 = new Velocity(-3.5, -6.7);
        Velocity v4 = new Velocity(3.5, -6.7);
        Velocity v5 = new Velocity(0.0, 0.0);
        Velocity v6 = Velocity.fromAngleAndSpeed(90.0, 5.4);
        Velocity v7 = Velocity.fromAngleAndSpeed(0.0, 5.4);
        Velocity v8 = Velocity.fromAngleAndSpeed(30.0, 6.0);

        Point p1 = new Point(3.5, 7.8);
        Point p2 = new Point(-3.5, 7.8);
        Point p3 = new Point(-3.5, -7.8);
        Point p4 = new Point(3.5, -7.8);

        assert (v1.applyToPoint(p1).equals(new Point(7.0, 14.5)));
        assert (v1.applyToPoint(p2).equals(new Point(0.0, 14.5)));
        assert (v1.applyToPoint(p3).equals(new Point(0.0, -1.1)));
        assert (v1.applyToPoint(p4).equals(new Point(7.0, -1.1)));

        assert (v2.applyToPoint(p1).equals(new Point(0.0, 14.5)));
        assert (v2.applyToPoint(p2).equals(new Point(-7.0, 14.5)));
        assert (v2.applyToPoint(p3).equals(new Point(-7.0, -1.1)));
        assert (v2.applyToPoint(p4).equals(new Point(0.0, -1.1)));

        assert (v3.applyToPoint(p1).equals(new Point(0.0, 1.1)));
        assert (v3.applyToPoint(p2).equals(new Point(-7.0, 1.1)));
        assert (v3.applyToPoint(p3).equals(new Point(-7.0, -14.5)));
        assert (v3.applyToPoint(p4).equals(new Point(0.0, -14.5)));

        assert (v4.applyToPoint(p1).equals(new Point(7.0, 1.1)));
        assert (v4.applyToPoint(p2).equals(new Point(0.0, 1.1)));
        assert (v4.applyToPoint(p3).equals(new Point(0.0, -14.5)));
        assert (v4.applyToPoint(p4).equals(new Point(7.0, -14.5)));

        assert (v5.applyToPoint(p1).equals(new Point(3.5, 7.8)));
        assert (v5.applyToPoint(p2).equals(new Point(-3.5, 7.8)));
        assert (v5.applyToPoint(p3).equals(new Point(-3.5, -7.8)));
        assert (v5.applyToPoint(p4).equals(new Point(3.5, -7.8)));

//         Point p4 = new Point(3.5, -7.8);
        assert (v6.applyToPoint(p4).equals(new Point(8.9, -7.8)));
//        assert (v7.applyToPoint(p4).equals(new Point(3.5, -2.4)));
        assert (v7.applyToPoint(p4).equals(new Point(3.5, -13.2)));
//        assert (v8.applyToPoint(p4).equals(new Point(((6.0 * Math.sin(Math.PI/6)))
//                + 3.5, (6.0 * Math.sin(Math.PI / 3)) -7.8)));
        assert (v8.applyToPoint(p4).equals(new Point(((6.0 * Math.sin(Math.PI/6)))
                + 3.5, -(6.0 * Math.sin(Math.PI / 3)) -7.8)));

        System.out.println("passed tests for Velocity");


    }

    /**
     * In this method I assumed there is a getDx and getDy
     * method in velocity class.
     * */
    public void checkBall(){
        Point c1 = new Point(50.5, 60.3);
        Point c2 = new Point(0.0, 0.0);
        Point c3 = new Point(-90.8, 130.5);
        Point c4 = new Point(-90.8, -130.5);
        Point c5 = new Point(90.8, -130.5);
        Point c6 = new Point(90.8, 130.5);
        Point c7= new Point(150.0,150.0);
        Point c8= new Point(100.0,100.0);


        Ball b1 = new Ball(c1, 5, Color.PINK);
        Ball b2 = new Ball(c1, 5, Color.BLACK);
        Ball b3 = new Ball(c1, 6, Color.PINK);
        Ball b4 = new Ball(c2, 5, Color.PINK);
        Ball b5 = new Ball(c3, 23, Color.MAGENTA);
        Ball b6 = new Ball(c4, 13, Color.MAGENTA);
        Ball b7 = new Ball(c5, 3, Color.CYAN);
        Ball b8 = new Ball(c6, 9, Color.ORANGE);

        Ball b9 = new Ball(c7, 50, Color.BLUE);//only x velocity changes
        Ball b10 = new Ball(c8, 100, Color.GREEN);
        Ball b11 = new Ball(c7, 50, Color.BLUE);//only y velocity changes
        Ball b12 = new Ball(c7, 50, Color.BLUE);//both x and y changes

        //check that program will not fail:
        Ball b13 = new Ball(c6, -9, Color.ORANGE);   // radius is negative
        Ball b14 = new Ball(c6, 150, Color.ORANGE);//radius bigger than 200*200 screen;
        GUI u = new GUI("TestbadBalls", 200, 200);
        DrawSurface a = u.getDrawSurface();
        b13.drawOn(a);
        b14.drawOn(a);
        u.show(a);

        assert (b1.getX() == 50);
        assert (b1.getY() == 60);
        assert (b1.getSize() == 5);
        assert (b1.getColor() == Color.PINK);
        assert (b2.getColor() == Color.BLACK);

        GUI gui = new GUI("TestBalls1", 200, 200);
        DrawSurface d = gui.getDrawSurface();
        b1.drawOn(d);
        // this will over ride b1
        b2.drawOn(d);
        //this will overide b2
        b3.drawOn(d);
        b4.drawOn(d);
        //these balls are out of range of screen
        b5.drawOn(d);
        b6.drawOn(d);
        b7.drawOn(d);
        //the orange ball
        b8.drawOn(d);
        gui.show(d);

        Velocity v1 = new Velocity(3.5, 6.7);
        Velocity v2 = new Velocity(5.4, 8.9);
        ;Velocity v3 = new Velocity(-6.7, 3.5);
        Velocity v4 = new Velocity(3.5, -6.7);
        Velocity v5 = new Velocity(3.5, 3.5);
        b1.setVelocity(v4);

        assert(b1.getVelocity() == v4);
        b2.setVelocity(3.5,6.7);
        assert (b2.getVelocity().getDx() == v1.getDx() && b2.getVelocity().getDy() == v1.getDy());
        b2.setVelocity(5.4,8.9);
        assert(b2.getVelocity().getDx() == v2.getDx() && b2.getVelocity().getDy() == v2.getDy());
        b9.setVelocity(v4);
        assert(b9.getVelocity().getDx() == v4.getDx() && b9.getVelocity().getDy() == v4.getDy());
        b10.setVelocity(v2);
        assert (b10.getVelocity().getDx() == v2.getDx() && b10.getVelocity().getDy() == v2.getDy());
        b11.setVelocity(v3);
        assert (b11.getVelocity().getDx() == v3.getDx() && b11.getVelocity().getDy() == v3.getDy());
        b12.setVelocity(v5);
        assert (b12.getVelocity().getDx() == v5.getDx() && b12.getVelocity().getDy() == v5.getDy());

        b1.moveOneStep();
        assert (b1.getX()== 54 && b1.getY()==53);

        b3.moveOneStep();//check system doesn't crash if I move without setting velocity


//        b9.moveOneStep();
//        assert (b9.getX() == 146 && b9.getY()==143);

//            b10.moveOneStep();
//            assert (b9.getX() == 100 && b9.getY()==100);// piazza says no need to try

//        b11.moveOneStep();
//        assert (b11.getX() == 143 && b11.getY() == 146);
//
//        b12.moveOneStep();
//        assert (b12.getX() == 146 && b12.getY() == 146);

        /*if wanted- print these balls as well.*/
//            GUI g = new GUI("TestBalls2", 200, 200);
//            DrawSurface t = gui.getDrawSurface();
//            b9.drawOn(t);
////            b11.drawOn(t);
////            b12.drawOn(t);
//            g.show(t);

        System.out.println("passed tests for Ball");

    }

    /**
     * This method makes sure the smaller the ball the faster it is.
     * note: 1)I assumed you have a getDx and getDy method in the velocity
     *       class. if not- add it, it never hurts to have.
     *       2) I assumed the time you used to sleep in animation was 50 millisecends.
     *       if not- change the SLEEP value at the top.
     *
     * */
    public void checkMultipleBouncingBalls(Ball[] balls) {
        for (Ball b : balls) {
            for (Ball c: balls){
                // make sure balls increase in speed as they decrease in size
                assert (((Math.sqrt(Math.pow(b.getX()-b.getVelocity().getDx(), 2))
                        + Math.pow(b.getY()-b.getVelocity().getDy(), 2) / SLEEP
                        > Math.sqrt(Math.pow(c.getX()-c.getVelocity().getDx(), 2))
                        + Math.pow(c.getY()-c.getVelocity().getDy(), 2) / SLEEP) && b.getSize()>c.getSize())
                        || ((Math.sqrt(Math.pow(b.getX()-b.getVelocity().getDx(), 2))
                        + Math.pow(b.getY()-b.getVelocity().getDy(), 2) / SLEEP
                        < Math.sqrt(Math.pow(c.getX()-c.getVelocity().getDx(), 2))
                        + Math.pow(c.getY()-c.getVelocity().getDy(), 2) / SLEEP) && b.getSize()< c.getSize())
                        || ((Math.sqrt(Math.pow(b.getX()-b.getVelocity().getDx(), 2))
                        + Math.pow(b.getY()-b.getVelocity().getDy(), 2) / SLEEP
                        == Math.sqrt(Math.pow(c.getX()-c.getVelocity().getDx(), 2))
                        + Math.pow(c.getY()-c.getVelocity().getDy(), 2) / SLEEP) && ((b.getSize()==c.getSize())
                        || (b.getSize() >=50 && c.getSize() >= 50))));
            }

        }
    }


    /**
     * This function asserts all coordinates of balls
     * given- are in range of rectangles
     * */
    public void checkBallCoords(Ball b) {
        assert (((b.getX() - b.getSize()) >= 450 && (b.getX() + b.getSize()) <= 600
                && (b.getY() - b.getSize()) >= 450
                && (b.getY() + b.getSize()) <= 600)
                || ((b.getX() - b.getSize()) >= 50 && (b.getX() + b.getSize()) <= 500
                && (b.getY() - b.getSize()) >= 50
                && (b.getY() + b.getSize()) <= 500));
        checkWhenBouncesOff(b);
        this.previousV = b.getVelocity();
        this.previousP = new Point(b.getX(), b.getY());
    }

    /**
     * This function makes sure ball changes direction at right time
     * */
    public void checkWhenBouncesOff(Ball b) {
        if (previousV != null && previousP != null) {
            if (b.getVelocity().getDx() != previousV.getDx()) {
                assert ((previousP.getX() - b.getSize() <= 50)
                        || (previousP.getX() + b.getSize() >= 500)
                        || (previousP.getX() - b.getSize() <= 450)
                        || (previousP.getX() + b.getSize() >= 600));
            } if (b.getVelocity().getDy() != previousV.getDy()) {
                assert ((previousP.getY() - b.getSize() <= 50 )
                        || (previousP.getY() + b.getSize() >= 500 )
                        || (previousP.getY() - b.getSize() <= 450 )
                        || (previousP.getY() + b.getSize() >= 600 ));
            }

        }
    }

    public static void main(String[] args) {
        TestForAss2 test = new TestForAss2();
        test.checkPoint();
        test.checkLine();
        test.checkVelocity();
        test.checkBall();
        System.out.println("passed all tests for basic classes!");

    }

}
