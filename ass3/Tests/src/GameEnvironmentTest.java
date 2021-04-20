import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEnvironmentTest {

    public static void main(String[] args) {
        Tester tester = new Tester("GameEnvironmentTest");
        constructorTest(tester);
        addColidableTest(tester);
    }

    private static void constructorTest(Tester tester) {
        GameEnvironment gameEnvironment = new GameEnvironment();
        List<Collidable> collidableList = gameEnvironment.getCollidableList();
        tester.testsCounter(collidableList.isEmpty());
    }

    private static void addColidableTest(Tester tester) {
        GameEnvironment gameEnvironment = new GameEnvironment();
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new Rectangle(new Point(10, 10), Block.DEFAULT_WIDTH, Block.DEFAULT_HEIGHT)));

    }
}
