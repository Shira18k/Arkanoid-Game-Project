package Ass4Game;

import Interfaces.LevelInformation;
import Levels.Level_1;
import Levels.Level_2;
import Levels.Level_3;
import Levels.Level_4;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

public class Ass3Game {

    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);


        List<LevelInformation> levels = new ArrayList<>();

        levels.add(new Level_1());
        levels.add(new Level_2());
        levels.add(new Level_3());
        levels.add(new Level_4());

        AnimationRunner runner = new AnimationRunner(gui, 60, new Sleeper());
        KeyboardSensor keyboard = gui.getKeyboardSensor();
//        GameLevel game = new GameLevel(level, runner, keyboard, 0);
        GameFlow game = new GameFlow(runner, keyboard);
        game.runLevels(levels);
        gui.close();
    }
}