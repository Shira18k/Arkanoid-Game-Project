package Engine;

import Ass4Game.GameLevel;
import Sprites.Block;
import Interfaces.HitListener;

public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // remove the block from the screen
        beingHit.removeFromGame(this.game);
        // remove the block from the list of hitting
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
        System.out.println(this.remainingBlocks.getValue());
    }
}
