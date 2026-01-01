package Ass4Game;

import Engine.Counter;
import Interfaces.Animation;
import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

import java.awt.Color;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private Counter score;
    private boolean isWin;
    private boolean stop;

    public EndScreen(KeyboardSensor k, Counter s, boolean win) {
        this.keyboard = k;
        this.score = s;
        this.isWin = win;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);

        // הדפסה לפי המקרה (ניצחון או הפסד)
        if (this.isWin) {
            d.drawText(100, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(100, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }

        d.drawText(100, d.getHeight() / 2 + 50, "Press space to exit", 20);

        // בדיקה ישירה של המקלדת לעצירת המסך
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}