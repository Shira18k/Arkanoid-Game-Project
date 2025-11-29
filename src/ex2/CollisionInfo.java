package ex2;
import ex1.Point;

public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidedObject;


    public CollisionInfo(Point collisionPoint, Collidable collidedObject){
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
}
