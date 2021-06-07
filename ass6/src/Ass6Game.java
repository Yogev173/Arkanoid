import game.animation.AnimationRunner;
import game.levels.*;

import java.util.ArrayList;
import java.util.List;

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
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] basicLevel = {new Level1(), new Level2(), new Level3(), new Level4()};
        for (String string : args) {
            try {
                if (1 <= Integer.parseInt(string) && Integer.parseInt(string) <= 4) {
                    levels.add(basicLevel[Integer.parseInt(string) - 1]);
                }
            } catch (Exception e) {

            }
        }

        if (levels.isEmpty()) {
            for (LevelInformation lI : basicLevel) {
                levels.add(lI);
            }
        }

        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
    }
}
