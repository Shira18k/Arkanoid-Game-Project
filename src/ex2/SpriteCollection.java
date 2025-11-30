package ex2;


public class SpriteCollection {
    public void addSprite(Sprite s);

    // call timePassed() on all sprites.
    public void notifyAllTimePassed();

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d);
}
