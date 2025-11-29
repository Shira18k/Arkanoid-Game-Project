package ex1;
import ex2.Collidable;
import ex2.CollisionInfo;
import ex2.GameEnvironment;

import biuoop.DrawSurface;
import ex2.Rectangle;

public class Ball {
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
    public Ball(Point c, int r, java.awt.Color color) { ////need to add GameEnvironment environment
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

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }
    // new methods by ex1.Velocity

    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }

    public Velocity getVelocity() {
        return this.velocity; //new object

    }

    public void moveOneStep() {

        Point nextCenter = this.getVelocity().applyToPoint(this.center);// create point - (from the rest of the way) for create lin
        Line trajectory = new Line(this.center, nextCenter); // create the relevant line
        //add after claud
        if (environment != null) {
            CollisionInfo collision = environment.getClosestCollision(trajectory);

            if (collision != null) {
                Point where_hit = collision.collisionPoint();
                Collidable who_hit = collision.collisionObject();

                // שנה את המהירות
                Velocity newVelocity = who_hit.hit(where_hit, this.velocity);
                this.velocity = newVelocity;

                // חשב מחדש nextCenter עם המהירות החדשה
                nextCenter = this.getVelocity().applyToPoint(this.center);
            }
        }

        this.center = nextCenter;
    }

//        CollisionInfo collision = environment.getClosestCollision(trajectory); //the fun that checking closest cut point between line and objs
//        // now, we get the point the obj that is cutting
//
//         if (collision!= null) {
//            //Check whether the current center point is close to the collision point
//            Point where_hit = collision.collisionPoint(); //not null
//            Collidable who_hit = collision.collisionObject(); //not null
//            if(this.center.isClose(where_hit,r + 1)) {
//                //defined the current velocity
//                Velocity velocity_hit = this.velocity;
//                Velocity newVelocity = who_hit.hit(where_hit, velocity_hit); // the new velocity if hitting
//                this.velocity = newVelocity;
//            }
//        }
//        //add after claud
//        this.center = nextCenter;
//      this.center = this.getVelocity().applyToPoint(nextCenter);


    //OneStepBefore
//        double x = center.getX();
//        double y = center.getY();
//
//        Point p = getVelocity().applyToPoint(this.center); //new point after add dx
//        double dx = p.getX()-x; // dx for x
//        double dy = p.getY()-y; // dy for y
//
//        if (center.getX() - r <= xStart) {
//            dx = -dx;
//            x = r;
//            Velocity v = new Velocity(dx , dy);
//        }
//        if (center.getX() + r >= xbound){
//            dx = -dx;
//            x = xbound -r;
//        }
//        if (center.getY() - r <= yStart) {
//            dy = -dy;
//            y = r;
//        }
//        if (center.getY() + r >= ybound) {
//            dy = -dy;
//            y = ybound - r;
//        }
//        Velocity v = new Velocity(dx , dy);
//        Point P = new Point(x, y);
//        this.setVelocity(new Velocity(dx, dy));
//        this.center = this.getVelocity().applyToPoint(P);
//    }

    public static void main(String[] args) {
//        ex1.Point start = new ex1.Point(30,50);
//        double dx = 5;
//        double dy = 6;
//        drawAnimation(start, dx, dy);
    }
}

