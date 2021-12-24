import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;

public class AbstractArtDrawing {

    static final int WIDTH = 400;
    static final int HEIGHT = 300;
    static final int NUM_OF_LINE = 10;
    static final int RADIOS = 3;

    public static void main(String[] args) {
        GUI gui = new GUI("Random lines", WIDTH, HEIGHT);
        DrawSurface d =gui.getDrawSurface();
        drawRandomLine(d);

        /*Line l1 = new Line(12,34,200,90);
        l1.drawLine(d);
        Line l2 = new Line(149,200,200,90);
        l2.drawLine(d);
        Point p = l1.intersectionWith(l2);
        if(p != null)
            p.drawPoint(d, RADIOS);*/
        gui.show(d);
    }

    public static void drawRandomLine(DrawSurface d) {
        Line[] lines = new Line[NUM_OF_LINE];

        //Generating the lines and drawing them and their middle
        for(int index = 0; index < NUM_OF_LINE; index++) {
            lines[index] = generateRandomLine();
            lines[index].drawLine(d);
            lines[index].drawMiddle(d, RADIOS);
        }

        for(int currentLine = 0; currentLine < NUM_OF_LINE; currentLine++) {
            for(int index = currentLine + 1; index < NUM_OF_LINE; index++) {
                Point intersectionPoint = lines[currentLine].intersectionWith(lines[index]);
                if(intersectionPoint != null) {
                    intersectionPoint.drawPoint(d, RADIOS);
                }
            }
        }
    }

    //generate a random line
    public static Line generateRandomLine() {
        Random rand = new Random();
        // get double in range 1-400
        double point1X = rand.nextInt(WIDTH) + 1;
        double point2X = rand.nextInt(WIDTH) + 1;
        // get double in range 1-300
        double point1Y = rand.nextInt(HEIGHT) + 1;
        double point2Y = rand.nextInt(HEIGHT) + 1;

        return new Line(point1X, point1Y, point2X, point2Y);
    }


}
