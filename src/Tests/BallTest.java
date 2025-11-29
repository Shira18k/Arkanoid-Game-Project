package Tests;
import ex1.*;
import ex2.*;

import java.awt.Color;

public class BallTest {
    public static void main(String[] args) {
        System.out.println("Starting Ball Tests...\n");

        // Test 1: Constructor and basic getters
        System.out.println("Test 1: Constructor and Getters");
        Point center = new Point(100.0, 150.0);
        Ball ball = new Ball(center, 20, Color.RED);
        System.out.println("Ball center: (" + ball.getX() + ", " + ball.getY() + ")");
        System.out.println("Expected: (100, 150)");
        System.out.println("Pass: " + (ball.getX() == 100 && ball.getY() == 150) + "\n");

        // Test 2: Color
        System.out.println("Test 2: Color");
        Color ballColor = ball.getColor();
        System.out.println("Expected: RED, Actual: " + ballColor);
        System.out.println("Pass: " + (ballColor.equals(Color.RED)) + "\n");

        // Test 3: Velocity setting and getting
        System.out.println("Test 3: Velocity");
        Velocity v = new Velocity(5.0, -3.0);
        ball.setVelocity(v);
        Velocity retrievedV = ball.getVelocity();
        System.out.println("Set velocity: dx=5.0, dy=-3.0");
        System.out.println("Retrieved velocity dx: " + retrievedV.getDX() + ", dy: " + retrievedV.getDY());
        System.out.println("Pass: " + (retrievedV.getDX() == 5.0 && retrievedV.getDY() == -3.0) + "\n");

        // Test 4: Alternative velocity setter
        System.out.println("Test 4: Alternative Velocity Setter");
        ball.setVelocity(10.0, 15.0);
        Velocity v2 = ball.getVelocity();
        System.out.println("Set velocity: dx=10.0, dy=15.0");
        System.out.println("Retrieved velocity dx: " + v2.getDX() + ", dy: " + v2.getDY());
        System.out.println("Pass: " + (v2.getDX() == 10.0 && v2.getDY() == 15.0) + "\n");

        // Test 5: Size calculation
        System.out.println("Test 5: Size (Area)");
        int size = ball.getSize();
        int expectedSize = (int)(Math.PI * 20 * 20);
        System.out.println("Expected size: " + expectedSize + ", Actual: " + size);
        System.out.println("Pass: " + (size == expectedSize) + "\n");

        System.out.println("Ball Tests Completed!");
    }
}
