public class PointTest {

    /**
     * Main.
     * test the Point class.
     * @param args
     */
    public static void main(String[] args) {
        Tester tester = new Tester("Point Test");
        constructorTest(tester);
        distanceTest(tester);

        tester.status();
    }

    /* constructor */
    //1
    private static void constructorTest(Tester tester) {
        System.out.println("\ntesting the Constructors:");
        //1
        Point p1 = new Point(4.89, 9.56);
        tester.testsCounter((Tester.isDoubleTheSame(p1.getX(), 4.89) && Tester.isDoubleTheSame(p1.getY(), 9.56)));
    }

    /* distance */
    //2
    private static void distanceTest(Tester tester) {
        System.out.println("\ntesting the distance method:");
        //2
        Point p1 = new Point(1.4,2);
        Point p2 = new Point(1.78, 9.345);
        tester.testsCounter(Tester.isDoubleTheSame(p1.distance(p2), 7.354823247));

    }
}
