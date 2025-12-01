package ex2;
import ex1.Line;

import java.util.ArrayList;
import java.util.List;
import ex1.Point;

public class GameEnvironment {

    private List<Collidable> collidables; // collection of


    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    // add the given collidable to the environment.

    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

//    public int getSizeOfLits(){
//        return this.size();
//    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    public CollisionInfo getClosestCollision(Line trajectory) {
        //add after claud
        if (collidables == null || collidables.isEmpty()) {
            return null;
        }
        double min_dis = Double.MAX_VALUE; // min distance
        Point closestPoint = null;         // the closet insertion point
        Collidable closest_C = null;       // the closet obj

        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect); // the cut point between the line and c
            if (intersection != null) {
                if (min_dis > intersection.distance(trajectory.start())) {
                    min_dis = intersection.distance(trajectory.start());
                    closestPoint = intersection;
                    closest_C = c;
                }
            }
        }
        if (closest_C == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closest_C);
    }
}