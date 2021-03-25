import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * display multiple ball animation in multipul frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    // order of setting: edge-(x,y) width height
    static final int[] FRAME_SETTING1 = {50, 50, 500, 500};
    static final int[] FRAME_SETTING2 = {450, 450, 600, 600};
    static final int[][] FRAMES_SETTING = {FRAME_SETTING1, FRAME_SETTING2};

    static final int DEFAULT_WIDTH = 700;
    static final int DEFAULT_HEIGHT = 700;
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
        Ball[] balls = createBalls(args);

        int xEdge1 = FRAMES_SETTING[0][0];
        int yEdge1 = FRAMES_SETTING[0][1];
        int width1 = -xEdge1 + FRAMES_SETTING[0][2];
        int height1 = -yEdge1 + FRAMES_SETTING[0][3];
        int xEdge2 = FRAMES_SETTING[1][0];
        int yEdge2 = FRAMES_SETTING[1][1];
        int width2 = -xEdge2 + FRAMES_SETTING[1][2];
        int height2 = -yEdge2 + FRAMES_SETTING[1][3];

        //Showing the balls
        GUI gui = new GUI("Multiple Bouncing Balls Animation", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Sleeper sleeper = new Sleeper();
            while (true) {
                // get the DrawSurface
                DrawSurface d = gui.getDrawSurface();
                d.setColor(Color.darkGray);

                // draw frame 1
                d.fillRectangle(xEdge1, yEdge1, width1, height1);
                d.setColor(Color.yellow);

                // draw frame 2
                d.fillRectangle(xEdge2, yEdge2, width2, height2);
                d.setColor(DEFAULT_COLOR);

                // move and draw the balls
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
     * @return array with the balls object.
     */
    private static Ball[] createBalls(String[] sizes) {
        int middleIndex = sizes.length / 2;
        int farmeNum = 0;
        Random rand = new Random();
        Ball[] balls = new Ball[sizes.length];
        int radios;
        int centerX;
        int centerY;
        Point center;

        Color[] colors = {Color.BLACK, Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.pink, Color.RED,
        Color.WHITE, Color.ORANGE};
        int colorIndex = 0;
        for (int ballNum = 0; ballNum < sizes.length; ballNum++) {
            if (ballNum >= middleIndex) {
                farmeNum = 1;
            }

            radios = Integer.valueOf(sizes[ballNum]);
            int xEdge = FRAMES_SETTING[farmeNum][0];
            int yEdge = FRAMES_SETTING[farmeNum][1];
            int width = -xEdge + FRAMES_SETTING[farmeNum][2];
            int height = -yEdge + FRAMES_SETTING[farmeNum][3];

            // xBound = width - 2r
            int xBound = width - 2 * radios;
            centerX = rand.nextInt(xBound) + xEdge + radios + 1;

            // yBound = height - 2r
            int yBound = height - 2 * radios;
            centerY = rand.nextInt(yBound) + yEdge + radios + 1;
            center = new Point(centerX, centerY);

            colorIndex  = (colorIndex + 1) % colors.length;
            balls[ballNum] = new Ball(center, radios, colors[colorIndex], Ball.velocityBySize(radios), width, height,
                    new Point(xEdge, yEdge));
        }

        return balls;
    }


}
