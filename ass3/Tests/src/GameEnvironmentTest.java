import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;

public class GameEnvironmentTest {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 10;

    public static void main(String[] args) {
        GUI gui = new GUI("Game Environment Test", 500, 500);
        Ball ball = new Ball(new Point(50, 50), 10, new GameEnvironment());
        create10Blocks(ball.getGameEnvironment());

        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            animation(drawSurface, ball);
            gui.show(drawSurface);
            sleeper.sleepFor(10);
        }
    }

    private static void create10Blocks(GameEnvironment gameEnvironment ) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            gameEnvironment.addCollidable(new Block(new Rectangle(new Point(rnd.nextInt(400) + 20,
                    rnd.nextInt(400) + 20), BLOCK_WIDTH, BLOCK_HEIGHT)));
        }

        //Board border Blocks

        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(0,0), 5, 500)));

        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(495,0), 5, 500)));

        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(0,0), 500, 5)));

        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(0,495), 500, 5)));
    }

    private static void animation(DrawSurface drawSurface, Ball ball) {
        for (Collidable c : ball.getGameEnvironment().getCollidableList()) {
            c.drawOn(drawSurface);
        }

        Velocity oldVelocity = ball.getVelocity();
        ball.moveOneStep();
        ball.drawOn(drawSurface);
    }
}
