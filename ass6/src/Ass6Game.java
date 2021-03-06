import game.levels.Level4;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.LevelInformation;
import game.levels.GameFlow;

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

        //check the input from the user
        for (String string : args) {
            // check if the args is a int from 1 to 4
            try {
                int levelNumber = Integer.parseInt(string);
                if (1 <= levelNumber && levelNumber <= 4) {
                    levels.add(basicLevel[Integer.parseInt(string) - 1]);
                }
            } catch (Exception e) {
                continue;
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
