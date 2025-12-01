package Mains;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import ex1.Ball;
import ex1.Point;
import ex1.Velocity;
import ex2.Block;
import ex2.GameEnvironment;
import ex2.Rectangle;

import java.awt.*;
import java.util.Random;

public class Main2_BlockAndBall {
    public static void main(String[] args) {
        Random rand = new Random();
        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 400;
        final int WALL_THICKNESS = 10;

        GUI gui = new GUI("Collision Test - Single Ball & Block", FRAME_WIDTH, FRAME_HEIGHT);
        Sleeper sleeper = new Sleeper();

        // Create GameEnvironment
        GameEnvironment env = new GameEnvironment();

        // Frame (Blocks)
        // left
        Block leftWall = new Block(new Rectangle(new Point(0, 0), WALL_THICKNESS, FRAME_HEIGHT),Color.gray);
        // right
        Block rightWall = new Block(new Rectangle(new Point(FRAME_WIDTH - WALL_THICKNESS, 0), WALL_THICKNESS, FRAME_HEIGHT),Color.gray);
        // up
        Block topWall = new Block(new Rectangle(new Point(WALL_THICKNESS, 0), FRAME_WIDTH - 2 * WALL_THICKNESS, WALL_THICKNESS),Color.gray);
        // down
        Block bottomWall = new Block(new Rectangle(new Point(WALL_THICKNESS, FRAME_HEIGHT - WALL_THICKNESS), FRAME_WIDTH - 2 * WALL_THICKNESS, WALL_THICKNESS),Color.gray);

        env.addCollidable(leftWall);
        env.addCollidable(rightWall);
        env.addCollidable(topWall);
        env.addCollidable(bottomWall);


        // Create the block
        int blockWidth = 100;
        int blockHeight = 10;

        //random location - block
        int blockX = rand.nextInt(FRAME_WIDTH - 2 * WALL_THICKNESS - blockWidth - 20) + WALL_THICKNESS + 10;
        int blockY = rand.nextInt(FRAME_HEIGHT - 2 * WALL_THICKNESS - blockHeight - 20) + WALL_THICKNESS + 10;

        Rectangle centerRect = new Rectangle(new Point(blockX, blockY), blockWidth, blockHeight);
        Block centerBlock = new Block(centerRect,Color.gray);


        final Color CENTER_BLOCK_COLOR = new Color(156, 50, 90);
        env.addCollidable(centerBlock);

        System.out.println("✓ 4 Walls and 1 Central Block added to environment.");


        // Create the ball
        int ballSize = 10;

        int startX = rand.nextInt(FRAME_WIDTH - 2 * WALL_THICKNESS - ballSize * 2) + WALL_THICKNESS + ballSize;
        int startY = rand.nextInt(FRAME_HEIGHT - 2 * WALL_THICKNESS - ballSize * 2) + WALL_THICKNESS + ballSize;
        Point startPoint = new Point(startX, startY);

        Ball singleBall = new Ball(startPoint, ballSize, Color.BLACK, env);

        // Speed
        Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), 5.0);
        singleBall.setVelocity(v);

        // Animation Loop

        while (true) {

            DrawSurface d = gui.getDrawSurface();

            //White background
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

            //Set color for block
            d.setColor(CENTER_BLOCK_COLOR);
            d.fillRectangle((int)centerRect.getUpperLeft().getX(), (int)centerRect.getUpperLeft().getY(), blockWidth, blockHeight);
            d.drawRectangle((int)centerRect.getUpperLeft().getX(), (int)centerRect.getUpperLeft().getY(), blockWidth, blockHeight);

            d.setColor(Color.WHITE);
            // left
            d.fillRectangle(0, 0, WALL_THICKNESS, FRAME_HEIGHT);
            // right
            d.fillRectangle(FRAME_WIDTH - WALL_THICKNESS, 0, WALL_THICKNESS, FRAME_HEIGHT);
            // up
            d.fillRectangle(WALL_THICKNESS, 0, FRAME_WIDTH - 2 * WALL_THICKNESS, WALL_THICKNESS);
            // down
            d.fillRectangle(WALL_THICKNESS, FRAME_HEIGHT - WALL_THICKNESS, FRAME_WIDTH - 2 * WALL_THICKNESS, WALL_THICKNESS);

            // move the ball
            singleBall.moveOneStep();
            singleBall.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(20);
        }
    }
}