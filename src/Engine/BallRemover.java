package Engine;

import Sprites.Block;
import PlayGame.Game;
import Interfaces.HitListener;

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    private Block deathArea;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
        // the bottom block

    }

    // Balls that are hit on "death area" should be removed
    // from the game. Remember to remove this listener from the ball
    // that is being removed from the game.
    @Override
    public void hitEvent(Block deathArea, Ball hitter) {
        // remove the ball from the screen
        hitter.removeFromGame(this.game);

        this.remainingBalls.decrease(1);
        System.out.println(this.remainingBalls.getValue());
    }
}