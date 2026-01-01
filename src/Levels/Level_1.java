package Levels;

import Engine.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Block;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level_1 implements LevelInformation {

    @Override
    public int numberOfBalls(){
        return 1;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities(){
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth(){
        return 800;
    }

    // the level name will be displayed at the top of the screen.
    @Override
    public String levelName(){
        return "Direct Hit";

    }

    // Returns a sprite with the background of the level
    @Override
    public Sprite getBackground() {

        return new Sprite() {

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                d.drawCircle(400, 162, 60);
                d.drawCircle(400, 162, 90);
                d.drawCircle(400, 162, 120);
                d.drawLine(400, 192, 400, 302);
                d.drawLine(420, 162, 540, 162);
                d.drawLine(380, 162, 260, 162);
                d.drawLine(400, 132, 400, 22);
            }

            @Override
            public void timePassed() {}
        };
    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    @Override
    public List<Block> blocks(){
        List<Block> blocks = new ArrayList<>();
        Point upper_left = new Point(380, 142);
        Block block_1 = new Block(new Rectangle(upper_left,40 , 40), Color.RED);
        blocks.add(block_1);
        return blocks;
    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    @Override
    public int numberOfBlocksToRemove(){
        return 1;
    }



    // Check
    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("Level Preview", 800, 600);
        biuoop.DrawSurface d = gui.getDrawSurface();

        // יצירת מופע של השלב (למשל DirectHit)
        Level_1 level = new Level_1();

        // 1. ציור הרקע (השכבה התחתונה)
        level.getBackground().drawOn(d);

        // 2. מעבר על רשימת הבלוקים וציור של כל אחד מהם
        List<Sprites.Block> blocks = level.blocks();
        for (Sprites.Block b : blocks) {
            b.drawOn(d);
        }

        gui.show(d);
    }
}
