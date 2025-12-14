package ex2;
import ex1.Ball;
import ex1.Point;
import ex3.HitListener;

import java.awt.*;

public class CollisionInfo {
    private Ball hitter;
    private Point collisionPoint;
    private Collidable collidedObject;


    public CollisionInfo(Ball hitter, Point collisionPoint, Collidable collidedObject){
        this.hitter = hitter;
        this.collisionPoint = collisionPoint;
        this.collidedObject = collidedObject;
    }
    // the point at which the collision occurs.
    public Point collisionPoint(){
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this.collidedObject;

    }
    public Ball hitter(){
        return this.hitter;
    }
}
