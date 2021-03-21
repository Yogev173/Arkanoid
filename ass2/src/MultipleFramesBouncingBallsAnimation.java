import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {
    // order of setting: edge-(x,y) width height
    final static int[] FRAME_SETTING1 = {50, 50, 500, 500};
    final static int[] FRAME_SETTING2 = {450, 450, 600, 600};
    final static int[][] FRAMES_SETTING = {FRAME_SETTING1, FRAME_SETTING2};

    final static int DEFAULT_WIDTH = 700;
    final static int DEFAULT_HEIGHT = 700;

    final static Color DEFAULT_COLOR = Color.BLUE;

    public static void main(String args[]) {
        if(args == null) {
            System.out.println("***no sizes were entered in the terminal***");
            return;
        }
        Ball[] balls = createBalls(args);

        int xEdge1 = FRAMES_SETTING[0][0];
        int yEdge1 = FRAMES_SETTING[0][1];
        int width1 = - xEdge1 + FRAMES_SETTING[0][2];
        int height1 = - yEdge1 + FRAMES_SETTING[0][3];
        int xEdge2 = FRAMES_SETTING[1][0];
        int yEdge2 = FRAMES_SETTING[1][1];
        int width2 = - xEdge2 + FRAMES_SETTING[1][2];
        int height2 = - yEdge2 + FRAMES_SETTING[1][3];

        //Showing the balls
        GUI gui = new GUI("Multiple Bouncing Balls Animation", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Sleeper sleeper = new Sleeper();
            while(true) {
                DrawSurface d = gui.getDrawSurface();
                d.setColor(Color.darkGray);
                d.fillRectangle(xEdge1, yEdge1, width1, height1);
                d.setColor(Color.yellow);
                d.fillRectangle(xEdge2, yEdge2, width2, height2);
                d.setColor(DEFAULT_COLOR);
                for(int index = 0; index < balls.length; index++) {
                    balls[index].drawOn(d);
                    balls[index].moveOneStep();
                }
                gui.show(d);
                sleeper.sleepFor(15);
            }
    }

    // Create the Balls and return array of them
    private static Ball[] createBalls(String sizes[]) {
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
        for(int ballNum = 0; ballNum < sizes.length; ballNum++) {
            if(ballNum >= middleIndex) {
                farmeNum = 1;
            }
            radios = Integer.valueOf(sizes[ballNum]);
            int xEdge = FRAMES_SETTING[farmeNum][0];
            int yEdge = FRAMES_SETTING[farmeNum][1];
            int width = - xEdge + FRAMES_SETTING[farmeNum][2];
            int height = - yEdge + FRAMES_SETTING[farmeNum][3];

            // width - 2r
            int xBound = width - 2*radios;
            System.out.println(width);
            centerX = rand.nextInt(xBound) + xEdge +radios + 1;

            // height - 2r
            int yBound = height - 2*radios;
            centerY = rand.nextInt(yBound) + yEdge + radios + 1;
            center = new Point(centerX, centerY);

            colorIndex  = (colorIndex + 1) % colors.length;
            balls[ballNum] = new Ball(center, radios, colors[colorIndex], Ball.velocityBySize(radios), width, height,
                    new Point(xEdge, yEdge));
        }

        return balls;
    }


}
