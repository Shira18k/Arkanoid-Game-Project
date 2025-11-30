package ex2;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;

    public void addSprite(Sprite s){
        sprites.add(s);
    }
    public SpriteCollection() {
        this.sprites = new ArrayList<>(); // **התיקון הוא כאן!**
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        //timePassed can change the list - so we're working on copy
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy){
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite s : sprites){
            s.drawOn(d);
        }
    }
}
