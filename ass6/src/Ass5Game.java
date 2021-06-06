import game.GameLevel;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * run a game of Pong.
 */
public class Ass5Game {

    /**
     * main.
     * run a game of Pong.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(6, 2, 12, true);
        gameLevel.initialize();
        gameLevel.run();
    }
}
