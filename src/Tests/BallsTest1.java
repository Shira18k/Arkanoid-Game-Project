package Tests;

import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.*;

import ex1.Ball;
import ex1.Point;


public class BallsTest1 {
    public static void main(String[] args) {
        GUI gui = new GUI("Balls Test 1", 400, 400);
        DrawSurface d = gui.getDrawSurface();
        Point p1 = new Point(150,200);
        Point p2 = new Point(250,300);
        Point p3 = new Point(300,350);

        Ball b1 = new Ball(p1,60,Color.PINK);
        Ball b2 = new Ball(p2,30,java.awt.Color.BLUE);
        Ball b3 = new Ball(p3,40, Color.yellow);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);

        gui.show(d);
    }
}