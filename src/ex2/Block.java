package ex2;
import biuoop.DrawSurface;
import ex1.Line;
import ex1.Point;
import ex1.Velocity;

import java.awt.*;

public class Block implements Collidable, Sprite{
    private static final double EPSILON = 0.00001;
    protected Rectangle boundary;
    private java.awt.Color color;

    public Block(Rectangle rect) { //defined the physical information of the block
        this.boundary = rect;
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLUE);
        //up_left point
        int x = (int) this.boundary.getUpperLeft().getX();
        int y = (int) this.boundary.getUpperLeft().getY();
        //Draw a block
        surface.fillRectangle(x, y, (int) this.boundary.getWidth(), (int) this.boundary.getHeight());

    }

    @Override
    public void timePassed() {
    }

    @Override // to be sure that the name (is)? like the original name
    public Rectangle getCollisionRectangle() {
        return this.boundary; // return the physical information of the block
    }

    public void addToGame(Game g) {
        g.addSprite(this);      // ✅ כי צריך לצייר אותו
        g.addCollidable(this);  // ✅ כי כדורים מתנגשים בו
    }

    @Override// to be sure that the name the same like the original name
    // update (if hit)
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
        return newVelocity;
    }
}
