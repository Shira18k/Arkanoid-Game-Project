package Ass3Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    //private Animation animation;

    public AnimationRunner(GUI d , int framesPerSecond, Sleeper sleeper){
        this.gui = d;
        this.framesPerSecond = 60;
        this.sleeper =  sleeper;
    }

    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();


            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
