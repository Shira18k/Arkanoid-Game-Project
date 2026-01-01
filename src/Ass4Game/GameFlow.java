package Ass4Game;

import Engine.Counter;
import Interfaces.LevelInformation;
import biuoop.KeyboardSensor;


import java.util.List;

public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score; //  score - from every level

    // constructor
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = new Counter(0);
    }

    public void runLevels(List<LevelInformation> levels) {

        boolean playerWon = true; // נניח שהשחקן ניצח אלא אם נגמרו הכדורים
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score);
            level.initialize();

            while (level.getRAmountOfBlocks() > 0 && level.getAmountOfBalls() > 0) {
                level.run();
            }

            // no balls anymore :(
            if (level.getAmountOfBalls() == 0) {
                playerWon = false; // השחקן הפסיד
                break;
            }// next level - out of while

        }
        EndScreen endScreen = new EndScreen(this.keyboardSensor, this.score, playerWon);
        this.animationRunner.run(endScreen);

    }
}

