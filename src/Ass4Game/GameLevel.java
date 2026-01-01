package Ass4Game;

import Engine.*;
import Interfaces.*;
import Shapes.Rectangle;
import Sprites.Block;
import Sprites.LevelName;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Shapes.Point;
import java.awt.Color;
import biuoop.KeyboardSensor;





public class GameLevel implements Animation {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BORDER_SIZE = 10; // גודל ה"קירות"
    private static final int BALL_RADIUS = 7;

    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private Counter blockCounter;
    protected Counter ballCounter;
    private Counter scoreCounter;

    // new - Animation level 4
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;


    //constructor
    public GameLevel(LevelInformation levelInfo, AnimationRunner runner , KeyboardSensor keyboard , Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        //this.scoreCounter = new Counter(0);
        this.scoreCounter = score;
        // new - part 4
        this.levelInfo = levelInfo; // the level of the game
        this.runner = runner;
        this.gui = gui;
        this.keyboard = keyboard;
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

        // now - printed by levelInfo
        Sprite background = levelInfo.getBackground();
        this.sprites.addSprite(background);

        String name = this.levelInfo.levelName(); // כאן את מושכת את השם ("Direct Hit" למשל)
        LevelName nameIndicator = new LevelName(name);

        // 3. הוספת המדפיס לרשימת ה-Sprites של המשחק
        this.addSprite(nameIndicator);


        // this object is a list of who need to know about hitting
        HitListener block_remove = new BlockRemover(this ,this.blockCounter);
        HitListener ball_remove = new BallRemover(this , this.ballCounter);
        HitListener score = new ScoreTrackingListener(this.scoreCounter);

        //the bourds
        Block leftWall = new Block(new Rectangle(new Point(0, 0), BORDER_SIZE, FRAME_HEIGHT),Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(FRAME_WIDTH - BORDER_SIZE, 0), BORDER_SIZE, FRAME_HEIGHT),Color.GRAY);
        Block topWall = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, BORDER_SIZE),Color.GRAY);
        Block bottomWall = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - BORDER_SIZE), FRAME_WIDTH, BORDER_SIZE),Color.GRAY);


        // add to sprite and collidable
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        topWall.addToGame(this);
        bottomWall.addToGame(this);

        //Add a death listener to a bottom wall
        bottomWall.addHitListener(ball_remove);

        // new - by info
        Paddle paddle = new Paddle(levelInfo.paddleWidth(),FRAME_HEIGHT, levelInfo.paddleSpeed(), this.keyboard ,Color.WHITE,BORDER_SIZE);
        // check
        System.out.println(paddle);
        paddle.addToGame(this);

        // new - create blocks by info
        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(block_remove);
            block.addHitListener(score);
        }
        // off for now
//        //create the blocks
//        final int BLOCK_WIDTH = 60;
//        final int BLOCK_HEIGHT = 20;
//        final int SPACING = 0; // no space
//        // the location of first row(for blocks)
//        final int START_Y = 100;
//        final int START_X = 800; // the corner
//
//        int BLOCKS_PER_ROW = 12;
//        final int NUM_ROWS = 6;
//
//        //for different colors
//        Color[] rowColors = {Color.decode("#9B2226"), Color.decode("#AE2012"), Color.decode("#BB3E03"), Color.decode("#CA6702"), Color.decode("#EE9B00"), Color.decode("#E9D8A6")};
//
//        // create a block for checking
//        for (int row = 0; row < NUM_ROWS; row++) { // rows of blocks
//            Color currentColor = rowColors[row];   // different color for each row
//            double currentY = START_Y + row * (BLOCK_HEIGHT);
//            for (int col = 0; col < BLOCKS_PER_ROW; col++) {
//                double currentX = ( START_X - col * (BLOCK_WIDTH) ) - BORDER_SIZE * 7;
//                Rectangle rect = new Rectangle(new Point(currentX, currentY), BLOCK_WIDTH, BLOCK_HEIGHT);// create the block
//                Block newBlock = new Block(rect,currentColor);
//                newBlock.addToGame(this);// add to sprite & collidable
//                this.blockCounter.increase(1);
//                newBlock.addHitListener(block_remove);
//                newBlock.addHitListener(score);
//            }
//            BLOCKS_PER_ROW = BLOCKS_PER_ROW - 1;
//        }

        // create the balls by info
        for (Velocity v : levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(new Point (400,500), 5, Color.WHITE, this.environment); // first position
            ball.setVelocity(v);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
// off for now
//        // create the ball
//        double BALL_START_X = 400;
//        double BALL_START_Y = 300;
//        Ball ball1 = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIUS, Color.decode("#001219"), this.environment);// 1. נקודת התחלה
//        ball1.setVelocity(Velocity.fromAngleAndSpeed(45, 4));
//        ball1.addToGame(this);
//        this.ballCounter.increase(1);
//
//        Ball ball2 =new Ball((new Point(BALL_START_X, BALL_START_Y)), BALL_RADIUS, Color.decode("#001219"), this.environment);
//        ball2.setVelocity(Velocity.fromAngleAndSpeed(20, 4));
//        ball2.addToGame(this);
//        this.ballCounter.increase(1);
//
//        Ball ball3 = new Ball(new Point(BALL_START_X, BALL_START_Y), BALL_RADIUS, Color.decode("#001219"), this.environment);// 1. נקודת התחלה
//        ball3.setVelocity(Velocity.fromAngleAndSpeed(70, 4));
//        ball3.addToGame(this);
//        this.ballCounter.increase(1);
        }


    // new - Animation
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with

        this.sprites.drawAllOn(d);

        updateScore(d, this.scoreCounter);

        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (ballCounter.getValue() == 0 ){
            this.running = false;
        }
        if(blockCounter.getValue() == 0){
            this.scoreCounter.increase(100);
            this.running = false;
        }
    }

    // Run the game -- start the animation loop.
    public void run() {
        this.initialize();
        this.running = true;
        System.out.println(ballCounter);
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    public void updateScore(DrawSurface d, Counter score){
        String msg = "score = :" + score.getValue();
        d.setColor(Color.RED);
        d.drawText(45, 45, msg, 20);
    }
    public void removeCollidable(Collidable c){
        this.environment.removeCollidable(c);
    }
    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }

    public int getRAmountOfBlocks() {
        return this.blockCounter.getValue();
    }

    public int getAmountOfBalls() {
        return this.ballCounter.getValue();
    }

}