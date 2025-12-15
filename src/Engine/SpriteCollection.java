package Engine;
import biuoop.DrawSurface;
import Interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;

    public void addSprite(Sprite s){
        sprites.add(s);
    }

    //!new - part 3.1 for remove block from the list in game
    public void removeSprite(Sprite s){
        sprites.remove(s);
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
