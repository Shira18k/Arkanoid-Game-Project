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

public class Level_3 implements LevelInformation {

    @Override
    public int numberOfBalls(){
        return 3;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities(){
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(24, 4));
        velocities.add(Velocity.fromAngleAndSpeed(40, 4));
        velocities.add(Velocity.fromAngleAndSpeed(70, 4));
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
        return "Green 3";

    }

    // Returns a sprite with the background of the level
    @Override
    public Sprite getBackground() {

        return new Sprite() {

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#003049"));
                d.fillRectangle(0, 0, 800, 600);
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
        // from "game" - the original
        int BLOCK_WIDTH = 60;
        int BLOCK_HEIGHT = 20;
        int SPACING = 0; // no space

        int START_Y = 100;
        int START_X = 800;// the corner
        int BORDER_SIZE = 10;

        int BLOCKS_PER_ROW = 12;
        int NUM_ROWS = 6;

        //for different colors
        Color[] rowColors = {Color.decode("#9B2226"), Color.decode("#AE2012"), Color.decode("#BB3E03"), Color.decode("#CA6702"), Color.decode("#EE9B00"), Color.decode("#E9D8A6")};

        // create a block for checking

        for (int row = 0; row < NUM_ROWS; row++) { // rows of blocks
            Color currentColor = rowColors[row];   // different color for each row
            double currentY = START_Y + row * (BLOCK_HEIGHT);
            for (int col = 0; col < BLOCKS_PER_ROW; col++) {
                double currentX = ( START_X - col * (BLOCK_WIDTH) ) - BORDER_SIZE * 7;
                Rectangle rect = new Rectangle(new Point(currentX, currentY), BLOCK_WIDTH, BLOCK_HEIGHT);// create the block
                Block newBlock = new Block(rect,currentColor);
                blocks.add(newBlock);

            }
            BLOCKS_PER_ROW = BLOCKS_PER_ROW - 1;
        }

        return blocks;
    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    @Override
    public int numberOfBlocksToRemove(){
        return 57;
    }


    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("Level Preview", 800, 600);
        biuoop.DrawSurface d = gui.getDrawSurface();

        // יצירת מופע של השלב (למשל DirectHit)
        Level_3 level = new Level_3();

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
