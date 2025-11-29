package Tests;

import ex1.Point;

public class PointTest {
    public static void main(String[] args) {
        System.out.println("Starting Point Tests...\n");
        
        // Test 1: Constructor and getters
        System.out.println("Test 1: Constructor and Getters");
        Point p1 = new Point(3.0, 4.0);
        System.out.println("Expected x: 3.0, Actual: " + p1.getX());
        System.out.println("Expected y: 4.0, Actual: " + p1.getY());
        System.out.println("Pass: " + (p1.getX() == 3.0 && p1.getY() == 4.0) + "\n");
        
        // Test 2: Distance calculation
        System.out.println("Test 2: Distance Calculation");
        Point p2 = new Point(0.0, 0.0);
        double distance = p1.distance(p2);
        System.out.println("Distance from (3,4) to (0,0)");
        System.out.println("Expected: 5.0, Actual: " + distance);
        System.out.println("Pass: " + (Math.abs(distance - 5.0) < 0.0001) + "\n");
        
        // Test 3: Equals - same points
        System.out.println("Test 3: Equals - Same Points");
        Point p3 = new Point(3.0, 4.0);
        System.out.println("Expected: true, Actual: " + p1.equals(p3));
        System.out.println("Pass: " + p1.equals(p3) + "\n");
        
        // Test 4: Equals - different points
        System.out.println("Test 4: Equals - Different Points");
        Point p4 = new Point(5.0, 6.0);
        System.out.println("Expected: false, Actual: " + p1.equals(p4));
        System.out.println("Pass: " + (!p1.equals(p4)) + "\n");
        
        System.out.println("Point Tests Completed!");
    }
}