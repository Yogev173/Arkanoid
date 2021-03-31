public class LineTest {
    static final double COMPARISON_THRESHOLD = 0.00001;

    public static void main(String[] args) {
        Tester tester = new Tester("Line Test", 51);
        testConstructor(tester);
        testIntersection(tester);
        testMiddle(tester);
        testLength(tester);

        tester.status();
    }

    /* constructor */
    //1-2
    private static void testConstructor(Tester tester) {
        System.out.println("\ntesting the Constructors:");
        System.out.println("* constructor by point");
        //1
        Line l1 = new Line(new Point(1.0, 2), new Point(3, 4));
        tester.printMessage(l1.start().equals(new Point(1.0, 2.0)) &&
                l1.end().equals(new Point(3.0, 4.0)));

        System.out.println("* constructor by x,y coordinate");
        //2
        Line l2 = new Line(1, 2.0,3.0, 4);
        tester.printMessage(l2.start().equals(new Point(1.0, 2.0)) &&
                l2.end().equals(new Point(3.0, 4.0)));
    }

    /* intersection */
    //3-46
    private static void testIntersection(Tester tester) {
        System.out.println("\ntesting the intersection method:");
        //3-16
        notParallelLine(tester);
        //17-24
        parallelNotInfinityIncline(tester);
        //25-
        parallelInfinityIncline(tester);
    }

    //3-16
    private static void notParallelLine(Tester tester) {
        System.out.println("* simple not parallel line");
        //3
        Line l1 = new Line(1, 1, -1, -1);
        Line l2 = new Line(1, -1, -0.5, 0.5);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(0, 0)));
        //4
        tester.printMessage((l1.isIntersecting(l2) == true));

        //5
        l1 = new Line(1, 1, -1, -1);
        l2 = new Line(1, -1, 1.5, -1.5);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //6
        tester.printMessage((l1.isIntersecting(l2) == false));

        //7
        l1 = new Line(0, 0, 1, 1);
        l2 = new Line(1, -1, 0, 0);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(0, 0)));
        //8
        tester.printMessage((l1.isIntersecting(l2) == true));

        //9
        l1 = new Line(1, 6, -1, 4);
        l2 = new Line(1, 1, -1, 5);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(-0.6666666, 4.3333333)));
        //10
        tester.printMessage((l1.isIntersecting(l2) == true));

        //11
        l1 = new Line(1, 6, -1, 4);
        l2 = new Line(1, 1, 2, -1);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //12
        tester.printMessage((l1.isIntersecting(l2) == false));

        //13
        l1 = new Line(0, 5, 2, 7);
        l2 = new Line(1, 7, 1, 6);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(1, 6)));
        //14
        tester.printMessage((l1.isIntersecting(l2) == true));

        //15
        l1 = new Line(0, 5, 2, 7);
        l2 = new Line(1, 7, 1, 8);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //16
        tester.printMessage((l1.isIntersecting(l2) == false));

        //17
        l1 = new Line(0, 5, 2, 7);
        l2 = new Line(1, 6, 1, 6);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(1, 6)));
        //18
        tester.printMessage((l1.isIntersecting(l2) == true));
    }

    //19-24
    private static void parallelNotInfinityIncline(Tester tester){
        System.out.println("* parallel line with a incline (not infinity)");
        //19
        Line l1 = new Line(1, 6, 2, 7);
        Line l2 = new Line(3, 8, 2, 7);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(2, 7)));
        //20
        tester.printMessage((l1.isIntersecting(l2) == true));

        //21
        l1 = new Line(1, 6, 2, 7);
        l2 = new Line(3.1, 8.1, 3.8, 8.8);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //22
        tester.printMessage((l1.isIntersecting(l2) == false));

        //23
        l1 = new Line(1, 6, 2, 7);
        l2 = new Line(1.5, 6.5, 1.2, 6.2);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //24
        tester.printMessage((l1.isIntersecting(l2) == true));

        //25
        l1 = new Line(1, 6, 2, 7);
        l2 = new Line(3, 8, 1.5, 6.5);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //26
        tester.printMessage((l1.isIntersecting(l2) == true));
    }

    //27-46
    private static void  parallelInfinityIncline(Tester tester) {
        System.out.println("* parallel line with a infinity incline");
        //27
        Line l1 = new Line(2, 6, 2, 2);
        Line l2 = new Line(2, -3, 2, 1.5);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //28
        tester.printMessage((l1.isIntersecting(l2) == false));

        //29
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 5, 2, 3);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //30
        tester.printMessage((l1.isIntersecting(l2) == true));

        //31
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 8, 2, 3);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //32
        tester.printMessage((l1.isIntersecting(l2) == true));

        //33
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 2, 2, 5.5);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //34
        tester.printMessage((l1.isIntersecting(l2) == true));

        //35
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 2, 2, 6);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //36
        tester.printMessage((l1.isIntersecting(l2) == true));

        //37
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 5, 2, 3);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //38
        tester.printMessage((l1.isIntersecting(l2) == true));

        //39
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(3, 3, 3, 5);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //40
        tester.printMessage((l1.isIntersecting(l2) == false));

        //41
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 5, 2, 3);
        tester.printMessage(l1.intersectionWith(l2) == null);
        //42
        tester.printMessage((l1.isIntersecting(l2) == true));

        //43
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 8, 2, 6);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(2, 6)));
        //44
        tester.printMessage((l1.isIntersecting(l2) == true));

        //45
        l1 = new Line(2, 6, 2, 2);
        l2 = new Line(2, 4, 2, 4);
        tester.printMessage(l1.intersectionWith(l2).equals(new Point(2, 4)));
        //46
        tester.printMessage((l1.isIntersecting(l2) == true));
    }

    /* middle */
    //47-49
    private static void testMiddle(Tester tester) {
        System.out.println("\ntesting the middle point method:");
        //47
        Line l1 = new Line(2, 6, 2, 2);
        tester.printMessage(l1.middle().equals(new Point(2, 4)));


        //48
        l1 = new Line(2, 7, 8, 13);
        tester.printMessage(l1.middle().equals(new Point(5, 10)));

        //49
        l1 = new Line(3.5, 1.44, 8, 0);
        tester.printMessage(l1.middle().equals(new Point(5.75, 0.72)));
    }

    /* length */
    //50-51
    private static void testLength(Tester tester) {
        System.out.println("\ntesting the length method:");
        //50
        Line l1 = new Line(2, 6, 2, 2);
        tester.printMessage(isDoubleTheSame(l1.length(), 4));

        //51
        l1 = new Line(2, 7, 8, 13);
        tester.printMessage(isDoubleTheSame(l1.length(), 8.485281374));
    }


    public static boolean isDoubleTheSame(double num1, double num2) {
        if (num1 - COMPARISON_THRESHOLD <= num2 && num2 <= num1 + COMPARISON_THRESHOLD) {
            return true;
        }

        return false;
    }
}
