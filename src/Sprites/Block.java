package Sprites;

import Interfaces.Collidable;
import Interfaces.Sprite;
import Ass3Game.Game;
import Shapes.Rectangle;
import biuoop.DrawSurface;
import Engine.Ball;
import Shapes.Line;
import Shapes.Point;
import Engine.Velocity;
import Interfaces.HitListener;
import Interfaces.HitNotifier;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;


public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.00001;
    protected Shapes.Rectangle boundary;
    private java.awt.Color color;
    private List<HitListener> hitListeners;


    public Block(Shapes.Rectangle rect, Color c) {
        this(rect, c, new ArrayList<HitListener>());
    }

    public Block(Shapes.Rectangle rect, Color c, List<HitListener> hitListeners) { //defined the physical information of the block
        this.boundary = rect;
        this.color = c;
        this.hitListeners = new ArrayList<>(hitListeners);
    }


    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        //up_left point
        int x = (int) this.boundary.getUpperLeft().getX();
        int y = (int) this.boundary.getUpperLeft().getY();
        //Draw a block
        surface.fillRectangle(x, y, (int) this.boundary.getWidth(), (int) this.boundary.getHeight());
        //draw frame
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, (int) this.boundary.getWidth(), (int) this.boundary.getHeight());

    }

    @Override
    public void timePassed() {
    }

    @Override // to be sure that the name (is)? like the original name
    public Rectangle getCollisionRectangle() {
        return this.boundary; // return the physical information of the block
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override// to be sure that the name the same like the original name
    // update (if hit)
    // !need to change for part 3
    public Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDX(); // the x value of the speed
        double newDy = currentVelocity.getDY(); // the y value of the speed

        double x_up_left = boundary.getUpperLeft().getX(); //for the malben
        double y_up_left =  boundary.getUpperLeft().getY(); // for the malben
        double Width = boundary.getWidth();
        double Height = boundary.getHeight();

        Line l_left = new Line(new Point(x_up_left,y_up_left) , new Point(x_up_left, y_up_left+ Height));
        Line l_right = new Line(new Point(x_up_left+Width,y_up_left) , new Point(x_up_left + Width, y_up_left+Height));
        Line l_up = new Line(new Point(x_up_left,y_up_left) , new Point(x_up_left + Width, y_up_left));
        Line l_down = new Line(new Point(x_up_left,y_up_left + Height) , new Point(x_up_left + Width, y_up_left+Height));

        double x_collisionPoint = collisionPoint.getX();
        double y_collisionPoint = collisionPoint.getY();
        if( Math.abs(y_collisionPoint -l_down.start().getY())<EPSILON || Math.abs(y_collisionPoint -l_up.start().getY())<EPSILON){ // check if is X
            newDy = -newDy;
        }
        if(Math.abs(x_collisionPoint -l_left.start().getX())<EPSILON || Math.abs(x_collisionPoint - l_right.start().getX())<EPSILON){
            newDx = -newDx;
        }

        Velocity newVelocity = new Velocity(newDx, newDy);
        //!new - part 3.1 - for update the other about hitting for removing
        this.notifyHit(hitter);
        return newVelocity;
    }

    // implements hitNotifier
    @Override
    public void addHitListener(HitListener hl){
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl){
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    //!new - part 3.1
    public void removeFromGame(Game game){
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}
