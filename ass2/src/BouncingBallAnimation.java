import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

public class BouncingBallAnimation {

    public static void main(String args[]) {
        int centerX = Integer.valueOf(args[0]);
        int centerY = Integer.valueOf(args[1]);
        double dx = Double.valueOf(args[2]);
        double dy = Double.valueOf(args[3]);
        Point center = new Point(centerX, centerY);
        drawAnimation(center, dx, dy);
    }

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
