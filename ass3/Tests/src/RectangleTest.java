import java.util.List;
import java.util.ArrayList;

public class RectangleTest {
    public static void main(String[] args) {
        Tester tester = new Tester("Rectangle Test");
        constructorTest(tester);
        edgePointsTest(tester);
        sidesTest(tester);
        intersectionPointsTest(tester);
        tester.status();
    }

    private static void constructorTest(Tester tester) {

    }

    private static void intersectionPointsTest(Tester tester) {
        Line l = new Line(new Point(2,1), new Point(2,8));
        Rectangle r = new Rectangle(new Point(1,2), new Point(4, 2), new Point(1, 8));
        List<Point> points1 = new ArrayList<Point>();
        points1.add(new Point(2,2));
        points1.add(new Point(2,8));
        List<Point> points2 = r.intersectionPoints(l);
        tester.testsCounter(points1.get(0).equals(points2.get(0)));
        tester.testsCounter(points1.get(1).equals(points2.get(1)));
    }

    private static void edgePointsTest(Tester tester) {
        Point ul = new Point(1,2);
        Point ur = new Point(4, 2);
        Point ll = new Point(1, 8);
        Point lr = new Point(4,8);
        Rectangle r = new Rectangle(ul, ur, ll);
        tester.compare(r.getUpperLeft().toString(), ul.toString());
        tester.compare(r.getUpperRight().toString(), ur.toString());
        tester.compare(r.getLowerLeft().toString(), ll.toString());
        tester.compare(r.getLowerRight().toString(), lr.toString());
    }

    private static void sidesTest(Tester tester) {
        Point ul = new Point(1,2);
        Point ur = new Point(4, 2);
        Point ll = new Point(1, 8);
        Point lr = new Point(4,8);
        Rectangle r = new Rectangle(ul, ur, ll);
        Line upper = new Line(ul, ur);
        Line lower = new Line(ll, lr);
        Line left = new Line(ul, ll);
        Line right = new Line(ur, lr);
        tester.testsCounter(upper.equals(r.getUpperSide()));
        tester.testsCounter(lower.equals(r.getLowerSide()));
        tester.testsCounter(left.equals(r.getLeftSide()));
        tester.testsCounter(right.equals(r.getRightSide()));
    }
}
