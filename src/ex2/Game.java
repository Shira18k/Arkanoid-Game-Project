package ex2;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import ex1.*;
import ex1.Point;

import java.awt.*;


public class Game {

    // קבועים להגדרת גודל המסך וגבולות בשביל initialize
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BORDER_SIZE = 25; // גודל ה"קירות"
    private static final int BALL_RADIUS = 5;

    // אובייקטים פנימיים (מנהלים)
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;

    //constructor
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();

    }

    // accept every obj with the methods from Collidable
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    // accept every obj with the methods from Sprite (for remembering
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);

    }

    // Initialize a new game:
    //create frame(env)
    // create the Blocks and Ball (and Paddle)
    // and add them to the game.

    public void initialize() {
        //the frame
        this.gui = new GUI("Game", FRAME_WIDTH, FRAME_HEIGHT);

        //the blocks
        Block leftWall = new Block(new Rectangle(new Point(0, 0), BORDER_SIZE, FRAME_HEIGHT));
        Block rightWall = new Block(new Rectangle(new Point(FRAME_WIDTH - BORDER_SIZE, 0), BORDER_SIZE, FRAME_HEIGHT));
        Block topWall = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, BORDER_SIZE));
        Block bottomWall = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - BORDER_SIZE), FRAME_WIDTH, BORDER_SIZE));
        Paddle paddle = new Paddle(FRAME_WIDTH,FRAME_HEIGHT,10,gui);
        // add to sprite and collidable
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        topWall.addToGame(this);
        bottomWall.addToGame(this);

        paddle.addToGame(this);

        //create the blocks
        final int BLOCK_WIDTH = 100;
        final int BLOCK_HEIGHT = 30;
        final int SPACING = 30;
        // the location of first row(for blocks)
        final int START_Y = 100;
        final int BLOCKS_PER_ROW = 3;
        final int NUM_ROWS = 5;
        final int START_X = 220;

        Color rowColor = Color.BLACK; //could be any color
        for (int row = 0; row < NUM_ROWS; row++) {
            double currentY = START_Y + row * (BLOCK_HEIGHT + SPACING);
            for (int col = 0; col < BLOCKS_PER_ROW; col++) {
                double currentX = START_X + col * (BLOCK_WIDTH + SPACING);
                Rectangle rect = new Rectangle(new Point(currentX, currentY), BLOCK_WIDTH, BLOCK_HEIGHT);// create the block
                Block newBlock = new Block(rect);
                newBlock.addToGame(this); // add to sprite & collidable
            }
        }
        // create the ball
        Color color = Color.BLACK;
        double BALL_START_X = 400;
        double BALL_START_Y = 300;
        Ball ball = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIUS, Color.BLUE, this.environment);// 1. נקודת התחלה
        ball.setVelocity(Velocity.fromAngleAndSpeed(45, 4)); // כיוון 45 מעלות, מהירות 4
        ball.addToGame(this);
    }

    // Run the game -- start the animation loop.
    public void run() {
        //

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}