public class BlockTest {

    public static void main(String[] args) {
        Tester tester = new Tester("Block Test");
        hitTester(tester);
        tester.status();
    }

    private static void hitTester(Tester tester) {
        Rectangle rct = new Rectangle(new Point(3,4), 3, 3);
        Block b = new Block(rct);
        Point center;
        Velocity v, newV;

        //no collide
        center = new Point(4,3);
        v = new Velocity(3,5);
        newV = new Velocity(3,5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //upper
        center = new Point(4,4);
        v = new Velocity(3,5);
        newV = new Velocity(3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //lower
        center = new Point(4,7);
        v = new Velocity(3,5);
        newV = new Velocity(3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //right
        center = new Point(6,5);
        v = new Velocity(3,5);
        newV = new Velocity(-3,5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //left
        center = new Point(3,6.7);
        v = new Velocity(3,5);
        newV = new Velocity(-3,5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //upper+right
        center = new Point(6,4);
        v = new Velocity(3,5);
        newV = new Velocity(-3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //upper+left
        center = new Point(3,4);
        v = new Velocity(3,5);
        newV = new Velocity(-3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //lower+right
        center = new Point(6,7);
        v = new Velocity(3,5);
        newV = new Velocity(-3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));
        //lower+left
        center = new Point(3,7);
        v = new Velocity(3,5);
        newV = new Velocity(-3,-5);
        tester.testsCounter(newV.equals(b.hit(center, v)));


    }
}
