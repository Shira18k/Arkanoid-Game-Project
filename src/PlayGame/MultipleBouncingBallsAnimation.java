package PlayGame;

import Engine.Ball;
import Engine.Velocity;
import Shapes.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {

        //if i want to run
        String[] array = new String[6];
        array[0] = "12";
        array[1] = "2";
        array[2] = "3";
        array[3] = "4";
        array[4] = "2";
        array[5] = "9";


        MultipleBouncingBallsAnimation obj = new MultipleBouncingBallsAnimation();
        obj.sixBall(array);


    }

    public void sixBall(String[] args) { //6 balls with random location
        Random rand = new Random(); // create a random-number generator
        Ball[] balls = new Ball[6];
        System.out.println(args.length);

        GUI gui = new GUI("title", 300, 400);

        Sleeper sleeper = new Sleeper();

        //Initialize the array of the balls
        for (int i = 0; i < 6; i++) {
            int x1 = rand.nextInt(300) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(400) + 1; // get integer in range 1-300
            Point point = new Shapes.Point(x1, y1);
            System.out.println(args[i]);
            int size = Integer.parseInt(args[i]); // from main
            Ball current = new Ball(point, size, Color.blue); // defined ball by size and random location

            //Initialize velocity and a random angle
            int angle = rand.nextInt(360); //angle for v
            //find a max in balls
            int max = 0;
            for (int j = 0; j < 6 ; j++) {
                if (Integer.parseInt(args[j]) > max) {
                    max = Integer.parseInt(args[j]);
                }
            }
            Velocity v = Velocity.fromAngleAndSpeed(angle, (max * 1.2 - (size * 10)) * 0.1);
            current.setVelocity(v);
            balls[i] = current;
        }
        while (true) {
            //Initialize the d object
            DrawSurface d = gui.getDrawSurface();
            //Create a for loop for moving every ball
            for (int i = 0; i < balls.length; i++) {
                Ball ball = balls[i];
                ball.moveOneStep();
                //Create a for loop for drawing all the balls on the surface
            }
            for (int i = 0; i < balls.length; i++) {
                Ball ball = balls[i];
                ball.drawOn(d); //draw the ball on the screen
                //Show the gui
                //Wait for 50 msc
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}


