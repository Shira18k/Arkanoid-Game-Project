package Sprites;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

public class LevelName implements Sprite {
    private String name;

    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED); // או כל צבע שמתאים לרקע שלך
        // מיקום באמצע למעלה כפי שביקשת
        d.drawText(45, 25, "Level Name: " + this.name, 15);
    }

    @Override
    public void timePassed() {
        // אין צורך במימוש כאן
    }
}
