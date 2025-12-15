package Ass3Game;

import Engine.*;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.Sprite;
import Shapes.Rectangle;
import Sprites.Block;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Shapes.Point;
import java.awt.Color;



public class Game {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BORDER_SIZE = 10; // גודל ה"קירות"
    private static final int BALL_RADIUS = 7;

    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;

    //constructor
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.scoreCounter = new Counter(0);
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
    // create frame(env)
    // create the Blocks and Ball (and Paddle)
    // and add them to the game.

    public void initialize() {
        //the frame
        this.gui = new GUI("Ass3Game.Game", FRAME_WIDTH, FRAME_HEIGHT);

        // this object is a list of who need to know about hitting
        HitListener block_remove = new BlockRemover(this ,this.blockCounter);
        HitListener ball_remove = new BallRemover(this , this.ballCounter);
        HitListener score = new ScoreTrackingListener(this.scoreCounter);

        //the blocks
        Block leftWall = new Block(new Rectangle(new Point(0, 0), BORDER_SIZE, FRAME_HEIGHT),Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(FRAME_WIDTH - BORDER_SIZE, 0), BORDER_SIZE, FRAME_HEIGHT),Color.GRAY);
        Block topWall = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, BORDER_SIZE),Color.GRAY);
        Block bottomWall = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - BORDER_SIZE), FRAME_WIDTH, BORDER_SIZE),Color.GRAY);
        Paddle paddle = new Paddle(FRAME_WIDTH,FRAME_HEIGHT,10,gui,Color.decode("#001219"),BORDER_SIZE);

        // add to sprite and collidable
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        topWall.addToGame(this);
        bottomWall.addToGame(this);
        paddle.addToGame(this);

        //Add a death listener to a bottom wall
        bottomWall.addHitListener(ball_remove);

        //create the blocks
        final int BLOCK_WIDTH = 60;
        final int BLOCK_HEIGHT = 20;
        final int SPACING = 0; // no space
        // the location of first row(for blocks)
        final int START_Y = 100;
        final int START_X = 800; // the corner

        int BLOCKS_PER_ROW = 12;
        final int NUM_ROWS = 6;

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
                newBlock.addToGame(this);// add to sprite & collidable
                this.blockCounter.increase(1);
                newBlock.addHitListener(block_remove);
                newBlock.addHitListener(score);
            }
            BLOCKS_PER_ROW = BLOCKS_PER_ROW - 1;
        }
        // create the ball
        double BALL_START_X = 400;
        double BALL_START_Y = 300;
        Ball ball1 = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIUS, Color.decode("#001219"), this.environment);// 1. נקודת התחלה
        ball1.setVelocity(Velocity.fromAngleAndSpeed(45, 4));
        ball1.addToGame(this);
        this.ballCounter.increase(1);

        Ball ball2 =new Ball((new Point(BALL_START_X, BALL_START_Y)), BALL_RADIUS, Color.decode("#001219"), this.environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(20, 4));
        ball2.addToGame(this);
        this.ballCounter.increase(1);

        Ball ball3 = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIUS, Color.decode("#001219"), this.environment);// 1. נקודת התחלה
        ball1.setVelocity(Velocity.fromAngleAndSpeed(70, 4));
        ball1.addToGame(this);
        this.ballCounter.increase(1);
    }

    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if(blockCounter.getValue() == 0){
                this.scoreCounter.increase(100);
                gui.close();
            }
            if (ballCounter.getValue() == 0 ){
                gui.close();
            }

            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            // blue frame
            d.setColor(Color.decode("#003049"));
            d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

            updateScore(d, this.scoreCounter);
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

    public void updateScore(DrawSurface d, Counter score){
        String msg = "score = :" + score.getValue();
        d.setColor(Color.black);
        d.drawText(45, 45, msg, 20);
    }
    public void removeCollidable(Collidable c){
        this.environment.removeCollidable(c);
    }
    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }
}