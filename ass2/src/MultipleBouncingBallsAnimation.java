import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * display multiple ball animation.
 */
public class MultipleBouncingBallsAnimation {

    static final int DEFAULT_WIDTH = 200;
    static final int DEFAULT_HEIGHT = 200;
    static final Color DEFAULT_COLOR = Color.BLUE;

    /**
     * main.
     * get a array with a string that represent a radios of balls, and display them with animation.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("***no sizes were entered in the terminal***");
            return;
        }
        Ball[] balls = createBalls(args, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //Showing the balls
        GUI gui = new GUI("Multiple Bouncing Balls Animation", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            //moving and drawing the balls
            for (int index = 0; index < balls.length; index++) {
                balls[index].drawOn(d);
                balls[index].moveOneStep();
            }
            gui.show(d);
            sleeper.sleepFor(5);
        }
    }

    /**
     * createBalls.
     * @param sizes the array of the ball sizes.
     * @param width the frame width.
     * @param height the frame height.
     * @return array with the balls object.
     */
    private static Ball[] createBalls(String[] sizes, int width, int height) {
        Random rand = new Random();
        Ball[] balls = new Ball[sizes.length];
        int radios;
        int centerX;
        int centerY;
        Point center;

        // creating the balls
        for (int ballNum = 0; ballNum < sizes.length; ballNum++) {
            radios = Integer.valueOf(sizes[ballNum]);
            centerX = rand.nextInt(width - 2 * radios) + radios;
            centerY = rand.nextInt(height - 2 * radios) + radios;
            center = new Point(centerX, centerY);

            balls[ballNum] = new Ball(center, radios, DEFAULT_COLOR, Ball.velocityBySize(radios), width, height);
        }

        return balls;
    }


}
