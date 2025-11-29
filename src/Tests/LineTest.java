package Tests;
import ex1.*;
import ex2.*;

public class LineTest {
    public static void main(String[] args) {
        System.out.println("Starting Line Tests...\n");

        // Test 1: Constructor and basic methods
        System.out.println("Test 1: Constructor and Basic Methods");
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(3.0, 4.0);
        Line line1 = new Line(p1, p2);
        System.out.println("Line from (0,0) to (3,4)");
        System.out.println("Expected length: 5.0, Actual: " + line1.length());
        System.out.println("Pass: " + (Math.abs(line1.length() - 5.0) < 0.0001) + "\n");

        // Test 2: Middle point
        System.out.println("Test 2: Middle Point");
        Point middle = line1.middle();
        System.out.println("Expected middle: (1.5, 2.0)");
        System.out.println("Actual: (" + middle.getX() + ", " + middle.getY() + ")");
        System.out.println("Pass: " + (middle.getX() == 1.5 && middle.getY() == 2.0) + "\n");

        // Test 3: Start and End points
        System.out.println("Test 3: Start and End Points");
        Point start = line1.start();
        Point end = line1.end();
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("End: (" + end.getX() + ", " + end.getY() + ")");
        System.out.println("Pass: " + (start.equals(p1) && end.equals(p2)) + "\n");

        // Test 4: Intersection - intersecting lines
        System.out.println("Test 4: Intersection - Lines Cross");
        Line line2 = new Line(0.0, 4.0, 3.0, 0.0);
        boolean intersecting = line1.isIntersecting(line2);
        Point intersection = line1.intersectionWith(line2);
        System.out.println("Lines intersecting: " + intersecting);
        if (intersection != null) {
            System.out.println("Intersection point: (" + intersection.getX() + ", " + intersection.getY() + ")");
        }
        System.out.println("Pass: " + intersecting + "\n");

        // Test 5: Intersection - parallel lines
        System.out.println("Test 5: Intersection - Parallel Lines");
        Line line3 = new Line(0.0, 1.0, 3.0, 5.0);
        boolean notIntersecting = line1.isIntersecting(line3);
        System.out.println("Lines intersecting: " + notIntersecting);
        System.out.println("Expected: false");
        System.out.println("Pass: " + (!notIntersecting) + "\n");

        // Test 6: Contains point
        System.out.println("Test 6: Contains Point");
        Point testPoint = new Point(1.5, 2.0);
        boolean contains = line1.contains(testPoint);
        System.out.println("Line contains middle point (1.5, 2.0)");
        System.out.println("Expected: true, Actual: " + contains);
        System.out.println("Pass: " + contains + "\n");

        // Test 7: Equals
        System.out.println("Test 7: Equals");
        Line line4 = new Line(p1, p2);
        boolean areEqual = line1.equals(line4);
        System.out.println("Lines are equal: " + areEqual);
        System.out.println("Expected: true");
        System.out.println("Pass: " + areEqual + "\n");

        System.out.println("Line Tests Completed!");
    }
}