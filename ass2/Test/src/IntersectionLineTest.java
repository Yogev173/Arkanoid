public class IntersectionLineTest {
    public static void main(String[] args) {
        boolean failed = false;
        //test 1 - two point intersection.
        Line line1 = new Line(0, 0, 0, 0);
        Line line2 = new Line(0, 0, 0, 0);
        Point intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 0))) {
            System.out.println("test 1 failed. intersection point should be (0,0)");
            failed = true;
        }

        //test 2 - two point no intersection
        line1 = new Line(0, 0, 0, 0);
        line2 = new Line(1, 1, 1, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 2 failed. intersection point should be null");
            failed = true;
        }

        //test 3 - point and infinity m line intersection.
        line1 = new Line(0, 1.5, 0, 1.5);
        line2 = new Line(0, 1, 0, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1.5))) {
            System.out.println("test 3 failed. intersection point should be (0,1.5)");
            failed = true;
        }

        //test 4 - point and infinity m line intersection.
        line1 = new Line(0, 0.5, 0, 0.5);
        line2 = new Line(0, 1, 0, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 4 failed. intersection point should be null");
            failed = true;
        }

        //test 5 - infinity m line with point intersect.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(0, 1, 0, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1))) {
            System.out.println("test 5 failed. intersection point should be (0,1)");
            failed = true;
        }

        //test 6 - infinity m line with point no intersection.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(0, 0, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 6 failed. intersection point should be null");
            failed = true;
        }

        //test 7 - point with line intersection.
        line1 = new Line(5, 5, 5, 5);
        line2 = new Line(0, 0, 10, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(5, 5))) {
            System.out.println("test 7 failed. intersection point should be (5,5)");
            failed = true;
        }

        //test 8 - point with line no intersection.
        line1 = new Line(1, 2, 1, 2);
        line2 = new Line(0, 0, 10, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 8 failed. intersection point should be null");
            failed = true;
        }

        //test 9 - line with point intersection.
        line1 = new Line(0, 0, 10, 10);
        line2 = new Line(1, 1, 1, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(1, 1))) {
            System.out.println("test 9 failed. intersection point should be (1,1)");
            failed = true;
        }

        //test 10 - line with point no intersection.
        line1 = new Line(0, 0, 10, 11);
        line2 = new Line(1, 1, 1, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 10 failed. intersection point should be null");
            failed = true;
        }

        //test 11 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(1, 1, 1, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 11 failed. intersection point should be null");
            failed = true;
        }

        //test 12 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, -2);
        line2 = new Line(0, 0, 0, -1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 12 failed. intersection point should be null");
            failed = true;
        }

        //test 13 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, -2);
        line2 = new Line(0, -1, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 13 failed. intersection point should be null");
            failed = true;
        }

        //test 14 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -2, 0, 0);
        line2 = new Line(0, 0, 0, -1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 14 failed. intersection point should be null");
            failed = true;
        }

        //test 15 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -2, 0, 0);
        line2 = new Line(0, -1, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 15 failed. intersection point should be null");
            failed = true;
        }

        //test 16 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, -1);
        line2 = new Line(0, 0, 0, -2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 16 failed. intersection point should be null");
            failed = true;
        }

        //test 17 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, -1);
        line2 = new Line(0, -2, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 17 failed. intersection point should be null");
            failed = true;
        }

        //test 18 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -1, 0, 0);
        line2 = new Line(0, 0, 0, -2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 18 failed. intersection point should be null");
            failed = true;
        }

        //test 19 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -1, 0, 0);
        line2 = new Line(0, -2, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 19 failed. intersection point should be null");
            failed = true;
        }




        //test 20 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 2, 0, 0);
        line2 = new Line(0, 1, 0, -1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 20 failed. intersection point should be null");
            failed = true;
        }

        //test 21 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 2, 0, 0);
        line2 = new Line(0, -1, 0, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 11 failed. intersection point should be null");
            failed = true;
        }

        //test 22 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, 2);
        line2 = new Line(0, 1, 0, -1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 22 failed. intersection point should be null");
            failed = true;
        }

        //test 23 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, 2);
        line2 = new Line(0, -1, 0, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 23 failed. intersection point should be null");
            failed = true;
        }

        //test 24 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 1, 0, -1);
        line2 = new Line(0, 2, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 24 failed. intersection point should be null");
            failed = true;
        }

        //test 25 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -1, 0, 1);
        line2 = new Line(0, 2, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 25 failed. intersection point should be null");
            failed = true;
        }

        //test 26 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 1, 0, -1);
        line2 = new Line(0, 0, 0, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 26 failed. intersection point should be null");
            failed = true;
        }

        //test 27 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, -1, 0, 1);
        line2 = new Line(0, 0, 0, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 27 failed. intersection point should be null");
            failed = true;
        }

        //test 28 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 0, 0, 4);
        line2 = new Line(0, 1, 0, 2);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 28 failed. intersection point should be null");
            failed = true;
        }

        //test 29 - infinity m line with infinity m line no intersection.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(0, 0, 0, 4);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 29 failed. intersection point should be null");
            failed = true;
        }

        //test 30 - infinity m line with infinity m line intersection.
        line1 = new Line(0, 2, 0, 1);
        line2 = new Line(0, 0, 0, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1))) {
            System.out.println("test 30 failed. intersection point should be (0,1)");
            failed = true;
        }

        //test 31 - infinity m line with infinity m line intersection.
        line1 = new Line(0, 2, 0, 1);
        line2 = new Line(0, 1, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1))) {
            System.out.println("test 31 failed. intersection point should be (0,1)");
            failed = true;
        }

        //test 32 - infinity m line with infinity m line intersection.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(0, 1, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1))) {
            System.out.println("test 32 failed. intersection point should be (0,1)");
            failed = true;
        }

        //test 33 - infinity m line with infinity m line intersection.
        line1 = new Line(0, 1, 0, 2);
        line2 = new Line(0, 0, 0, 1);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(0, 1))) {
            System.out.println("test 33 failed. intersection point should be (0,1)");
            failed = true;
        }

        //test 34 - infinity m line with line intersection.
        line1 = new Line(3, 0, 3, 20);
        line2 = new Line(0, 0, 5, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 34 failed. intersection point should be (3,6)");
            failed = true;
        }

        //test 35 - line with infinity m line intersection.
        line1 = new Line(0, 0, 5, 10);
        line2 = new Line(3, 0, 3, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 35 failed. intersection point should be (3,6)");
            failed = true;
        }


        //test 36 - infinity m line with line no intersection.
        line1 = new Line(3, 0, 3, 20);
        line2 = new Line(5, 10, 10, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 36 failed. intersection point should be null");
            failed = true;
        }

        //test 37 - line with infinity m line no intersection.
        line1 = new Line(5, 10, 10, 20);
        line2 = new Line(3, 0, 3, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 37 failed. intersection point should be null");
            failed = true;
        }

        //test 38 - infinity m line with line intersection.
        line1 = new Line(0, 3, 3, 6);
        line2 = new Line(0, 0, 3, 6);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 38 failed. intersection point should be (3,6)");
            failed = true;
        }

        //test 39 - infinity m line with line intersection.
        line1 = new Line(0, 3, 3, 6);
        line2 = new Line(3, 6, 0, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 39 failed. intersection point should be (3,6)");
            failed = true;
        }

        //test 40 - infinity m line with line intersection.
        line1 = new Line(0, 3, 3, 6);
        line2 = new Line(0, 0, 3, 6);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 40 failed. intersection point should be (3,6)");
            failed = true;
        }

        //test 41 - infinity m line with line intersection.
        line1 = new Line(3, 6, 1, 3);
        line2 = new Line(0, 0, 3, 6);
        intersectionPoint = line1.intersectionWith(line2);
        if (!intersectionPoint.equals(new Point(3, 6))) {
            System.out.println("test 41 failed. intersection point should be (3,6)");
            failed = true;
        }

        //test 42 - infinity m line with line not intersection.
        line1 = new Line(0, 0, 5, 10);
        line2 = new Line(-5, 0, 5, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 42 failed. intersection point should be null");
            failed = true;
        }

        //test 43 - line with line not intersection.
        line1 = new Line(0, 10, 5, 5);
        line2 = new Line(3, 7, 10, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 43 failed. intersection point should be null");
            failed = true;
        }

        //test 44 - line with line not intersection.
        line1 = new Line(0, 10, 5, 5);
        line2 = new Line(10, 0, 3, 7);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 44 failed. intersection point should be null");
            failed = true;
        }

        //test 45 - line with line not intersection.
        line1 = new Line(5, 5, 0, 10);
        line2 = new Line(3, 7, 10, 0);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 45 failed. intersection point should be null");
            failed = true;
        }

        //test 46 - line with line not intersection.
        line1 = new Line(5, 5, 0, 10);
        line2 = new Line(0, 10, 3, 7);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 46 failed. intersection point should be null");
            failed = true;
        }

        //test 47 - line with line not intersection.
        line1 = new Line(0, 10, 20, 10);
        line2 = new Line(0, 0, 5, 5);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 47 failed. intersection point should be null");
            failed = true;
        }

        //test 48 - line with line not intersection.
        line1 = new Line(0, 0, 5, 5);
        line2 = new Line(0, 10, 20, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 48 failed. intersection point should be null");
            failed = true;
        }

        //test 49 - line with line not intersection.
        line1 = new Line(5, 10, 10, 20);
        line2 = new Line(5, 5, 10, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 49 failed. intersection point should be null");
            failed = true;
        }

        //test 50 - line with line not intersection.
        line1 = new Line(0, 0, 20, 20);
        line2 = new Line(5, 5, 10, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 50 failed. intersection point should be null");
            failed = true;
        }

        //test 51 - line with line not intersection.
        line1 = new Line(5, 5, 10, 10);
        line2 = new Line(0, 0, 20, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 51 failed. intersection point should be null");
            failed = true;
        }

        //test 52 - line with line not intersection.
        line1 = new Line(0, 0, 10, 10);
        line2 = new Line(0, 0, 20, 20);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 52 failed. intersection point should be null");
            failed = true;
        }

        //test 53 - line with line not intersection.
        line1 = new Line(0, 0, 20, 20);
        line2 = new Line(0, 0, 10, 10);
        intersectionPoint = line1.intersectionWith(line2);
        if (intersectionPoint != null) {
            System.out.println("test 53 failed. intersection point should be null");
            failed = true;
        }

        //test 54 -  line with line not intersection.
        line1 = new Line(-5, -10, 5, 10);
        line2 = new Line(-5, 5, 5, -5);
        intersectionPoint = line1.intersectionWith(line2);
        if (!(intersectionPoint.equals(new Point(0, 0)))) {
            System.out.println("test 54 failed. intersection point should be (0,0)");
            failed = true;
        }

        //test 54 -  line with line not intersection.
        line1 = new Line(-2, 2, 0, 0);
        line2 = new Line(-2, -1, 0, 3);
        intersectionPoint = line1.intersectionWith(line2);
        if (!(intersectionPoint.equals(new Point(-1, 1)))) {
            System.out.println("test 54 failed. intersection point should be (-1,1)");
            failed = true;
        }




        if (failed) {
            System.out.println("Test failed");
        }
        if (!failed) {
            System.out.println("Passed the test!");

        }
    }
}