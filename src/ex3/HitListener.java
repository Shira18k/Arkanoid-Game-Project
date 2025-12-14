package ex3;
import ex1.Ball;
import ex2.Block;

//this interface made for who needs to now about hitting in block
public interface HitListener {

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting .
    void hitEvent(Block beingHit, Ball hitter);
}
