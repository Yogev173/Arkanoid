import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * display a ball animation.
 */
public class BouncingBallAnimation {

    /**
     * main.
     * create a ball at the coordinate of the first tow command line arguments, with a speed of
     * the last tow command line arguments.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("should be only 4 numbers at the command line arguments.");
        }

        int centerX = Integer.valueOf(args[0]);
        int centerY = Integer.valueOf(args[1]);
        double dx = Double.valueOf(args[2]);
        double dy = Double.valueOf(args[3]);
        Point center = new Point(centerX, centerY);
        drawAnimation(center, dx, dy);
    }

    /**
     * drawAnimation.
     * @param start the center starting point of the ball.
     * @param dx the x axis ball velocity.
     * @param dy the y axis ball velocity.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);

        //drawing the animation.
            while (true) {
                ball.moveOneStep();
                DrawSurface d = gui.getDrawSurface();
                ball.drawOn(d);
                gui.show(d);
                sleeper.sleepFor(15);
            }
    }
}
