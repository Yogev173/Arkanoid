import game.levels.GameLevel;
import game.levels.Level1;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * run a game of Pong.
 */
public class Ass6Game {

    /**
     * main.
     * run a game of Pong.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new Level1());
        gameLevel.initialize();
        gameLevel.run();
    }
}
