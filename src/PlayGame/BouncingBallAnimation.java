package PlayGame;

import Engine.Ball;
import Shapes.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class BouncingBallAnimation {

        static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 300, 400);
        Sleeper sleeper = new Sleeper();
        Point p = new Point(start.getX(), start.getY());
        Ball ball = new Ball(p, 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void publicDrawAnimation (Point start, double dx, double dy) {
        drawAnimation(start, dx, dy);
    }

    public static void main(String[] args) {
        System.out.println(args.length);

        if (args.length <4){
            System.out.println("The terminal input is empty or incorrect");
            return;
        }
        String arg0 = args[0];
        String arg1 = args[1];
        String arg2 = args[2];
        String arg3 = args[3];

        double n0 = Double.parseDouble(arg0);
        System.out.println(n0);
        double n1 = Double.parseDouble(arg1);
        System.out.println(n1);
        double n2 = Double.parseDouble(arg2);
        System.out.println(n2);
        double n3 = Double.parseDouble(arg3);
        System.out.println(n3);


        BouncingBallAnimation example = new BouncingBallAnimation();
        example.drawAnimation(new Point(1,2),4, 9);
    }
}
