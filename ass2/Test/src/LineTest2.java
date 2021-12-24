/*
	Test class for Line
	David Dinkevich
*/

import java.awt.geom.Line2D;
import java.util.Random;

public class LineTest2 {

    private Random random;
    private int numSuccessfulTests;
    private int numRandomLineChecks;
    private double randomLineRangeX;
    private double randomLineRangeY;


    public LineTest2(long defaultSeed, int numRandomLineChecks) {
        random = new Random(defaultSeed);
        this.numRandomLineChecks = numRandomLineChecks;
        numSuccessfulTests = 0;
        randomLineRangeX = 50;
        randomLineRangeY = 50;
    }

    /*
        CONVENIENCE METHODS
     */

    private String pointToString(Point point) {
        return String.format("(%.2f, %.2f)", point.getX(), point.getY());
    }

    private String lineToString(Line line) {
        return "[ " + pointToString(line.start()) + ", " + pointToString(line.end()) + " ]";
    }

    private Line2D myLineToJavaLine(Line line) {
        return new Line2D.Double(line.start().getX(), line.start().getY(),
                            line.end().getX(), line.end().getY());
    }

    private Line createRandomLine(double rangeX, double rangeY) {
        Point p1 = new Point(-rangeX/2, random.nextDouble() * rangeY - rangeY/2);
        Point p2 = new Point(rangeX/2, random.nextDouble() * rangeY - rangeY/2);
        return new Line(p1, p2);
    }

    // ----------------

    /**
     * Finds the point of intersection (POI) of the given two segments, according to the
     * Line class in this project, and the java.awt.geom library. If verifyAnswer is true,
     * then this method will return true if and only if the Line class and the Java geom
     * library both agree that there does/does not exist a point of intersection. If
     * verifyAnswer is false, this method will return true if there exists a point of
     * intersection (according to the Line class) or false if there does not.
     * @param line1 the first line
     * @param line2 the second line
     * @param verifyAnswer whether or not to compare the result from the Line class
     *                     with the java.awt.geom library
     * @return see description
     */
    private boolean checkLines(Line line1, Line line2, boolean verifyAnswer) {
        System.out.println("Evaluating...");
        System.out.println("Segment #1: " + lineToString(line1));
        System.out.println("Segment #2: " + lineToString(line2));

        // Query official Java library for existence of intersection point
        Line2D line1v2 = myLineToJavaLine(line1);
        Line2D line2v2 = myLineToJavaLine(line2);
        boolean linesIntersect = line1v2.intersectsLine(line2v2);

        // Test my line class
        Point poi = line1.intersectionWith(line2);
        if (poi != null) {
            System.out.println("Intersection Point: " + pointToString(poi));
        } else {
            System.out.println("No single point of intersection");
        }

        // If verifyAnswer, success = my Line class says there is a POI + Java says there is POI
        if (verifyAnswer) {
            System.out.println("Lines Intersect (verified): " + linesIntersect);
            boolean success = linesIntersect && poi != null || !linesIntersect && poi == null;
            if (!success) {
                System.out.println("TEST FAILED!");
            }
            return success;
        }
        return poi != null;
    }

    public void runTest() {
        Line[][] linePairs = {
                // Collinear vertical segments
                { new Line(0, 0, 0, 5), new Line(0, 5, 0, 10) },
                // One point and one line
                { new Line(2, 2, 2, 2), new Line(0, 0, 4, 4) },
                // Two points
                { new Line(2, 2, 2, 2), new Line(2, 2, 2, 2) },
                // Collinear segments
                { new Line(-1, -1, 5, 5), new Line(5, 5, 10, 10) },
                // Vertical and horizontal segments
                { new Line(0, 0, 0, 10), new Line(0, 0, 10, 0) },
                // One point and one vertical line
                { new Line(0, 0, 0, 10), new Line(0, 2, 0, 2) }
        };

        Line[][] overlappingLinePairs = {
                // Overlapping vertical collinear segments
                { new Line(0, 0, 0, 10), new Line(0, 5, 0, 15) },
                // Overlapping vertical collinear segments with one common endpoint
                { new Line(0, 0, 0, 10), new Line(0, 0, 0, 5) },
                // Overlapping vertical collinear segments with one common endpoint (#2)
                { new Line(0, 0, 0, 10), new Line(0, 5, 0, 10) },
                // Overlapping, parallel and collinear line segments
                { new Line(-10, -10, -5, -5), new Line(-7, -7, 0, 0) },
                // One segment inside the other (non-vertical)
                { new Line(0, 0, 10, 10), new Line(2, 2, 9, 9) },
                // One segment inside the other (vertical)
                { new Line(0, 10, 0, 0), new Line(0, 9, 0, 2) }
        };

        System.out.println("////////////////////////////////");
        System.out.println("Initiating Evaluation...");
        System.out.println("Testing fringe cases...");
        System.out.println("////////////////////////////////");

        for (int i = 0; i < linePairs.length; i++) {
            boolean res = checkLines(linePairs[i][0], linePairs[i][1], true);
            numSuccessfulTests += res ? 1 : 0;
            System.out.println("----------------------------");
        }

        System.out.println("////////////////////////////////");
        System.out.println("Commencing overlapping-segment checks...");
        System.out.println("////////////////////////////////");

        for (int i = 0; i < overlappingLinePairs.length; i++) {
            boolean res = checkLines(overlappingLinePairs[i][0],
                    overlappingLinePairs[i][1], false);
            // Overlapping segments have no single intersection point. Therefore
            // the result of all of these tests must be false, i.e., no intersection point
            numSuccessfulTests += !res ? 1 : 0;
            if (res)
                System.out.println("TEST FAILED!");
            System.out.println("----------------------------");
        }

        System.out.println("////////////////////////////////");
        System.out.println("Finished testing fringe cases.");
        System.out.println("Commencing random-line checks...");
        System.out.println("////////////////////////////////");

        /*
            RANDOM LINE CHECKS
         */

        for (int i = 0; i < numRandomLineChecks; i++) {
            Line random1 = createRandomLine(randomLineRangeX, randomLineRangeY);
            Line random2 = createRandomLine(randomLineRangeX, randomLineRangeY);
            // True because assumption is that random lines won't perfectly overlap
            boolean res = checkLines(random1, random2, true);
            numSuccessfulTests += res ? 1 : 0;
            System.out.println("----------------------------");
        }

        System.out.println("////////////////////////////////");
        System.out.println("Evaluation completed.");

        // PRINT RESULTS

        int numTotalCheckedTests = linePairs.length + overlappingLinePairs.length
                + numRandomLineChecks;
        System.out.printf("Program passed %.2f%% (%d/%d) of the tests.\n",
                (double) numSuccessfulTests / numTotalCheckedTests * 100,
                numSuccessfulTests, numTotalCheckedTests);
    }

    public static void main(String[] args) {
        /*
         * Default seed for testing purposes. (Ensures that we get the same
         * sequence of random numbers every time.)
         */
        final long DEFAULT_SEED = 1585566991442L;
        // Number of checks to perform with random lines
        final int NUM_RANDOM_LINE_CHECKS = 10;
        // Run the test...
        new LineTest2(DEFAULT_SEED, NUM_RANDOM_LINE_CHECKS).runTest();
    }
}

