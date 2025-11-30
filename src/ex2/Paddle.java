package ex2;
import ex1.Point;

import ex1.Velocity;

public class Paddle implements Collidable {

    @Override
    public Rectangle getCollisionRectangle() {
        return null;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return null;
    }
}