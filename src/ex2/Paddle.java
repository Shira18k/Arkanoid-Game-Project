package ex2;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import ex1.Point;

import ex1.Velocity;

import java.awt.*;

public class Paddle extends Block {
    protected Rectangle paddle;
    private KeyboardSensor keyboard;
    double MOVESPEED;
//    private int frameHeight;
    private int borderSize;
    private Velocity v_left = new Velocity(-MOVESPEED,0);
    private Velocity v_right = new Velocity(MOVESPEED,0);

    private Color color;

    public Paddle(double width, double length, double speed, GUI gui, Color c,int borderSize) {
        super(new Rectangle(new Point(width/2-width/10,length-length/10),width/5,length/17),c);
        MOVESPEED = speed;
        keyboard = gui.getKeyboardSensor();
//        this.frameWidth = frameWidth;
//        this.frameHeight = frameHeight;
        this.borderSize = borderSize;
    }

    public void moveLeft(){
        // if isPressed(left) = true
        //move left- cause the user press ->
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() - MOVESPEED;
        //if the block colliding with the left wall
        if (newX >= borderSize) {
            this.boundary = new Rectangle(new Point(newX, currentUpperLeft.getY()), this.boundary.getWidth(), this.boundary.getHeight());
        }
    }

    public void moveRight() {
        //if isPressed(right) = true
        //move right - cause the user press <-
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() + MOVESPEED;
        double paddleWidth = this.boundary.getWidth(); // (width frame/5)
        double frameWidth = paddleWidth*5;
        if (newX + paddleWidth <= frameWidth - borderSize) {
            this.boundary = new Rectangle(new Point(newX, currentUpperLeft.getY()), this.boundary.getWidth(), this.boundary.getHeight());
        }
    }

    //Sprite
//    @Override
//    public void drawOn(DrawSurface d){
//        d.setColor(Color.BLUE);
//
//    //Acting like block?
//        //up_left point
//        int x = (int) this.paddle.getUpperLeft().getX();
//        int y = (int) this.paddle.getUpperLeft().getY();
//        //Draw a block
//        d.fillRectangle(x, y, (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
//    }
//

    public void move(){
        if(keyboard.isPressed("left")){
            moveLeft();
        }
        if (keyboard.isPressed("right")){
            moveRight();
        }
    }

    @Override
    public void timePassed(){
        move();
    }

//
//    //Collidable
//    @Override
//    public Rectangle getCollisionRectangle() {
//        return this.paddle;
//    }
//    //hit
//
//    public void addToGame(Game g){
//        g.addSprite(this);
//        g.addCollidable(this);
//    }
}