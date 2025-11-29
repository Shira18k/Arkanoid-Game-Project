package ex2;
import ex1.Line;
import ex1.Point;
import ex1.Velocity;

public class Block implements Collidable {
    private static final double EPSILON = 0.00001;
    private Rectangle boundary;

    public Block(Rectangle rect) { //defined the physical information of the block
        this.boundary = rect;
    }


    @Override // to be sure that the name the same like the original name
    public Rectangle getCollisionRectangle() {
        return this.boundary; // return the physical information of the block
    }

    @Override // to be sure that the name the same like the original name
    // update (if hit)
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDX(); // the x value of the speed
        double newDy = currentVelocity.getDY(); // the y value of the speed

        double x_upp_left = boundary.getUpperLeft().getX(); //for the malben
        double y_upp_left =  boundary.getUpperLeft().getY(); // for the malben
        double Width = boundary.getWidth();
        double Height = boundary.getHeight();

        Line l_left = new Line(new Point(x_upp_left,y_upp_left) , new Point(x_upp_left, y_upp_left- Height));
        Line l_right = new Line(new Point(x_upp_left+Width,y_upp_left) , new Point(x_upp_left + Width, y_upp_left-Height));
        Line l_upp = new Line(new Point(x_upp_left,y_upp_left) , new Point(x_upp_left + Width, y_upp_left));
        Line l_down = new Line(new Point(x_upp_left,y_upp_left - Height) , new Point(x_upp_left + Width, y_upp_left-Height));

        double x_collisionPoint = collisionPoint.getX();
        double y_collisionPoint = collisionPoint.getY();
        if( Math.abs(y_collisionPoint -l_down.start().getY())<EPSILON || Math.abs(y_collisionPoint -l_upp.start().getY())<EPSILON){ // check if is X
            newDy = -newDy;
        }
        if(Math.abs(x_collisionPoint -l_left.start().getX())<EPSILON || Math.abs(x_collisionPoint - l_right.start().getX())<EPSILON){
            newDx = -newDx;
        }

        Velocity newVelocity = new Velocity(newDx, newDy);
        return newVelocity;
    }
}
