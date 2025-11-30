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
    private Velocity v_left = new Velocity(-MOVESPEED,0);
    private Velocity v_right = new Velocity(MOVESPEED,0);

    private Color color;

    public Paddle(double width, double length, double speed, GUI gui) {
        super(new Rectangle(new Point(width/2-width/10,length-length/10),width/5,length/17));
        MOVESPEED = speed;
        keyboard = gui.getKeyboardSensor();
    }

    public void moveLeft(){
        // if isPressed(left) = true
        //move left- cause the user press ->
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() - MOVESPEED;
        double newY = currentUpperLeft.getY();
        this.boundary.upperLeft = new Point(newX, newY);
    }
    public void moveRight(){
        //if isPressed(right) = true
        //move right - cause the user press <-
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() + MOVESPEED;
        double newY = currentUpperLeft.getY();
        this.boundary.upperLeft = new Point(newX, newY);
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