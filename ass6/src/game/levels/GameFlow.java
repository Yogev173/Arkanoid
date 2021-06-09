package game.levels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.remove.Counter;
import game.animation.AnimationRunner;
import game.animation.GameOverScreen;
import game.animation.KeyPressStoppableAnimation;
import game.animation.WinningScreen;
import game.score.ScoreTrackingListener;

import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Run the Game.
 */
public class GameFlow {

    /**
     * run the level that it get.
     * @param levels the Level to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(new Counter(0));
        GUI gui = new GUI("Game", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, GameLevel.FRAME_PER_SECOND);
        boolean win = true;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, animationRunner, gui, scoreTrackingListener);

            level.initialize();
            win = level.run();
            if (!win) {
                break;
            }
        }

        //end screen
        DrawSurface d = gui.getDrawSurface();
        int score = scoreTrackingListener.getCounter().getValue();
        KeyboardSensor ks = gui.getKeyboardSensor();
        String key = KeyboardSensor.SPACE_KEY;
        if (win) {
            animationRunner.run(new KeyPressStoppableAnimation(ks, key, new WinningScreen(score)));
        } else {
            animationRunner.run(new KeyPressStoppableAnimation(ks, key, new GameOverScreen(score)));
        }

        gui.close();
    }
}


