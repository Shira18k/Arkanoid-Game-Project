package Engine;
import Ass4Game.GameLevel;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Shapes.Line;
import Shapes.Point;
import biuoop.DrawSurface;
import java.awt.*;

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    // not really matter
    private int xbound;
    private int ybound;
    private int xStart;
    private int yStart;


    // constructor
    public Ball(Point c, int r, java.awt.Color color) {
        this.center = c;
        this.r = r;
        this.color = color;
        xbound = 300;
        ybound = 400;

    }

    // the now one
    public Ball(Point c, int r, java.awt.Color color, GameEnvironment environment) {
        center = c;
        this.r = r;
        this.color = color;
        velocity = new Velocity(0, 0);
        this.environment = environment;
        System.out.println("✓ Ball constructor called: center=(" + c.getX() + "," + c.getY() + "), radius=" + r);
    }


    public void setbounds(int xStart, int yStart, int x, int y) {
        xbound = x;
        ybound = y;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    // draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    // accessors
    public int getX() {
        return (int) center.getX();
    }

    public int getY() {
        return (int) center.getY();
    }

    public int getSize() {
        return (int) (Math.PI * Math.pow(r, 2));
    }

    public java.awt.Color getColor() {
        return color;
    }


    // new methods by Engine.Velocity

    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }

    public Velocity getVelocity() {
        return this.velocity; //new object

    }
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void moveOneStep() {

        Point nextCenter = this.getVelocity().applyToPoint(this.center);// create point - (from the rest of the way) for create lin
        Line trajectory = new Line(this.center, nextCenter); // create the relevant line
        //add after claud
        if (environment != null) {
            CollisionInfo collision = environment.getClosestCollision(trajectory,this);

            if (collision != null) {
                Point where_hit = collision.collisionPoint();
                Collidable who_hit = collision.collisionObject();

                // שנה את המהירות
                Velocity newVelocity = who_hit.hit(this, where_hit, this.velocity);
                this.velocity = newVelocity;

                // חשב מחדש nextCenter עם המהירות החדשה
                nextCenter = this.getVelocity().applyToPoint(this.center);
            }
        }

        this.center = nextCenter;
    }
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }
}


