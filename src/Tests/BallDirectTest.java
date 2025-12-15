package Tests;
import Engine.Ball;
import Engine.Velocity;
import Shapes.Point;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Engine.GameEnvironment;
import java.awt.Color;

public class BallDirectTest {
    public static void main(String[] args) {
        System.out.println("=== BALL DIRECT TEST ===\n");

        try {
            // Create GUI
            System.out.println("1. Creating GUI...");
            GUI gui = new GUI("Ball Test", 300, 400);
            System.out.println("   ✓ GUI created\n");

            // Create GameEnvironment
            System.out.println("2. Creating GameEnvironment...");
            GameEnvironment env = new GameEnvironment();
            System.out.println("   ✓ GameEnvironment created\n");

            // Create a ball
            System.out.println("3. Creating Ball...");
            Point center = new Point(150, 200);
            System.out.println("   Center point: (" + center.getX() + ", " + center.getY() + ")");

            Ball ball = new Ball(center, 20, Color.RED, env);
            System.out.println("   ✓ Ball created");
            System.out.println("   Ball position: (" + ball.getX() + ", " + ball.getY() + ")");
            System.out.println("   Ball color: " + ball.getColor() + "\n");

            // Set velocity
            System.out.println("4. Setting velocity...");
            Velocity v = new Velocity(2, 3);
            ball.setVelocity(v);
            Velocity check = ball.getVelocity();
            System.out.println("   ✓ Velocity set: (" + check.getDX() + ", " + check.getDY() + ")\n");

            // Draw loop
            System.out.println("5. Starting draw loop...\n");
            Sleeper sleeper = new Sleeper();

            for (int frame = 0; frame < 100; frame++) {
                if (frame % 20 == 0) {
                    System.out.println("Frame " + frame + ": Ball at (" +
                            ball.getX() + ", " + ball.getY() + ")");
                }

                // Get surface
                DrawSurface d = gui.getDrawSurface();

                // White background
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 300, 400);

                // Frame counter
                d.setColor(Color.BLACK);
                d.drawText(10, 20, "Frame: " + frame, 15);
                d.drawText(10, 40, "Ball: (" + ball.getX() + "," + ball.getY() + ")", 15);

                // Draw ball
                System.out.println("  Drawing ball...");
                ball.drawOn(d);

                // Show
                gui.show(d);

                // Move ball
                System.out.println("  Moving ball...");
                ball.moveOneStep();

                sleeper.sleepFor(50);
            }

            System.out.println("\n=== TEST COMPLETE ===");

        } catch (NullPointerException e) {
            System.out.println("\n!!! NULL POINTER EXCEPTION !!!");
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("\n!!! ERROR !!!");
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}