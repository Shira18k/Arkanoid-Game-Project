package Sprites;
import Ass3Game.GameLevel;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Engine.Ball;
import Shapes.Line;
import Shapes.Point;

import Engine.Velocity;

import java.awt.*;

public class Paddle implements Collidable, Sprite {
    private static final double EPSILON = 0.00001;
    protected Shapes.Rectangle paddle;
    private KeyboardSensor keyboard;
    double MOVESPEED;
    //    private int frameHeight;
    private int borderSize;
    private Velocity v_left = new Velocity(-MOVESPEED, 0);
    private Velocity v_right = new Velocity(MOVESPEED, 0);
    private Shapes.Rectangle boundary;
    private Color color;

    public Paddle(double width, double length, double speed, GUI gui, Color c, int borderSize) {
        this.color = c;
        this.boundary = (new Shapes.Rectangle(new Point(width / 2 - width / 10, length - length / 10), width / 5, length / 17));
        MOVESPEED = speed;
        keyboard = gui.getKeyboardSensor();
        this.borderSize = borderSize;
    }

    public void moveLeft() {
        // if isPressed(left) = true
        //move left- cause the user press ->
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() - MOVESPEED;
        //if the block colliding with the left wall
        if (newX >= borderSize) {
            this.boundary = new Shapes.Rectangle(new Point(newX, currentUpperLeft.getY()), this.boundary.getWidth(), this.boundary.getHeight());
        }
    }

    public void moveRight() {
        //if isPressed(right) = true
        //move right - cause the user press <-
        Point currentUpperLeft = this.boundary.getUpperLeft(); // the up_left point of the paddle(block)
        double newX = currentUpperLeft.getX() + MOVESPEED;
        double paddleWidth = this.boundary.getWidth(); // (width frame/5)
        double frameWidth = paddleWidth * 5;
        if (newX + paddleWidth <= frameWidth - borderSize) {
            this.boundary = new Shapes.Rectangle(new Point(newX, currentUpperLeft.getY()), this.boundary.getWidth(), this.boundary.getHeight());
        }
    }

    public void move() {
        if (keyboard.isPressed("left")) {
            moveLeft();
        }
        if (keyboard.isPressed("right")) {
            moveRight();
        }
    }

    //implements sprite
    @Override
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.color);
        //up_left point
        int x = (int) this.boundary.getUpperLeft().getX();
        int y = (int) this.boundary.getUpperLeft().getY();
        //Draw a block
        surface.fillRectangle(x, y, (int) this.boundary.getWidth(), (int) this.boundary.getHeight());
        //draw frame
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, (int) this.boundary.getWidth(), (int) this.boundary.getHeight());

    }

    @Override
    public void timePassed() {
        move();
    }

    //implements colladable
    @Override // to be sure that the name (is)? like the original name
    public Rectangle getCollisionRectangle() {
        return this.boundary; // return the physical information of the block
    }
    @Override// to be sure that the name the same like the original name
    // update (if hit)
    // !need to change for part 3
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDX(); // the x value of the speed
        double newDy = currentVelocity.getDY(); // the y value of the speed

        double x_up_left = boundary.getUpperLeft().getX(); //for the malben
        double y_up_left =  boundary.getUpperLeft().getY(); // for the malben
        double Width = boundary.getWidth();
        double Height = boundary.getHeight();

        Line l_left = new Line(new Point(x_up_left,y_up_left) , new Point(x_up_left, y_up_left+ Height));
        Line l_right = new Line(new Point(x_up_left+Width,y_up_left) , new Point(x_up_left + Width, y_up_left+Height));
        Line l_up = new Line(new Point(x_up_left,y_up_left) , new Point(x_up_left + Width, y_up_left));
        Line l_down = new Line(new Point(x_up_left,y_up_left + Height) , new Point(x_up_left + Width, y_up_left+Height));

        double x_collisionPoint = collisionPoint.getX();
        double y_collisionPoint = collisionPoint.getY();
        if( Math.abs(y_collisionPoint -l_down.start().getY())<EPSILON || Math.abs(y_collisionPoint -l_up.start().getY())<EPSILON){ // check if is X
            newDy = -newDy;
        }
        if(Math.abs(x_collisionPoint -l_left.start().getX())<EPSILON || Math.abs(x_collisionPoint - l_right.start().getX())<EPSILON){
            newDx = -newDx;
        }

        Velocity newVelocity = new Velocity(newDx, newDy);
        return newVelocity;
    }
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}