package Levels;

import Engine.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Shapes.Rectangle;
import Shapes.Point;
import Sprites.Block;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls(){
        return 1;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities(){
        List<Velocity> velocities = new ArrayList<>();
        // 10 balls
        velocities.add(Velocity.fromAngleAndSpeed(20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(50, 5));
        velocities.add(Velocity.fromAngleAndSpeed(60, 5));
        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-50, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-60, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-40, 5));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth(){
        return 20;
    }

    // the level name will be displayed at the top of the screen.
    @Override
    public String levelName(){
        return "WideEasy";

    }

    // Returns a sprite with the background of the level
    @Override
    public Sprite getBackground() {

        return new Sprite() {

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.YELLOW);
                Point center = new Point (142, 162);
                d.fillCircle((int) center.getX(), (int) center.getY(), 50);
                d.setColor(Color.YELLOW);
                d.drawCircle((int) center.getX(), (int) center.getY(), 50);
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

        int BLOCK_WIDTH = 48;
        int BORDER_SIZE = 10;
        int BLOCK_HEIGHT = 20;


        Point upper_left_first = new Point(0,250);
        double startY = upper_left_first.getY();
        double startX = upper_left_first.getX();

        Color[] colors = {Color.decode("#9B2226"), Color.decode("#AE2012"), Color.decode("#BB3E03"), Color.decode("#CA6702"), Color.decode("#EE9B00"), Color.decode("#E9D8A6")};

        while (startX < 710) {

            for (int col = 0; col < 15; col++) {
                Color currentColor = colors[(col / 2) % colors.length];

                double nextX = startX + 48;
                Rectangle rect = new Rectangle(new Point(nextX, startY), 48, BLOCK_HEIGHT); // create the block
                Block newBlock = new Block(rect, currentColor);
                startX = nextX;
                blocks.add(newBlock);
            }
        }
        return blocks;
    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    @Override
    public int numberOfBlocksToRemove(){
        return 15;
    }

    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("Level Preview", 800, 600);
        biuoop.DrawSurface d = gui.getDrawSurface();

        // יצירת מופע של השלב (למשל DirectHit)
        WideEasy level = new WideEasy();

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
