package Tests;

import ex1.Point;
import ex1.Line;
import ex2.Rectangle;

import java.util.List;

public class RectangleTest {
    public static void main(String[] args) {
        System.out.println("Starting Rectangle Tests...\n");
        
        // Test 1: Constructor and basic getters
        System.out.println("Test 1: Constructor and Getters");
        Point upperLeft = new Point(10.0, 50.0);
        Rectangle rect = new Rectangle(upperLeft, 20.0, 30.0);
        System.out.println("Upper left: (" + rect.getUpperLeft().getX() + ", " + rect.getUpperLeft().getY() + ")");
        System.out.println("Width: " + rect.getWidth() + ", Height: " + rect.getHeight());
        System.out.println("Expected: (10.0, 50.0), Width: 20.0, Height: 30.0");
        System.out.println("Pass: " + (rect.getWidth() == 20.0 && rect.getHeight() == 30.0) + "\n");
        
        // Test 2: Intersection with vertical line (crosses left and right sides)
        System.out.println("Test 2: Intersection - Vertical Line");
        Rectangle rect2 = new Rectangle(new Point(0.0, 10.0), 20.0, 10.0);
        Line verticalLine = new Line(10.0, 15.0, 10.0, -5.0);
        List<Point> intersections = rect2.intersectionPoints(verticalLine);
        System.out.println("Number of intersections: " + intersections.size());
        System.out.println("Expected: 2 (top and bottom)");
        if (intersections.size() > 0) {
            for (Point p : intersections) {
                System.out.println("  Intersection at: (" + p.getX() + ", " + p.getY() + ")");
            }
        }
        System.out.println("Pass: " + (intersections.size() == 2) + "\n");
        
        // Test 3: Intersection with horizontal line
        System.out.println("Test 3: Intersection - Horizontal Line");
        Line horizontalLine = new Line(-5.0, 5.0, 25.0, 5.0);
        List<Point> intersections2 = rect2.intersectionPoints(horizontalLine);
        System.out.println("Number of intersections: " + intersections2.size());
        System.out.println("Expected: 2 (left and right sides)");
        System.out.println("Pass: " + (intersections2.size() == 2) + "\n");
        
        // Test 4: Diagonal line through rectangle
        System.out.println("Test 4: Intersection - Diagonal Line");
        Line diagonalLine = new Line(-5.0, -5.0, 25.0, 15.0);
        List<Point> intersections3 = rect2.intersectionPoints(diagonalLine);
        System.out.println("Number of intersections: " + intersections3.size());
        System.out.println("Expected: 2");
        System.out.println("Pass: " + (intersections3.size() == 2) + "\n");
        
        // Test 5: No intersection
        System.out.println("Test 5: No Intersection");
        Line noIntersectLine = new Line(30.0, 20.0, 40.0, 25.0);
        List<Point> intersections4 = rect2.intersectionPoints(noIntersectLine);
        System.out.println("Number of intersections: " + intersections4.size());
        System.out.println("Expected: 0");
        System.out.println("Pass: " + (intersections4.size() == 0) + "\n");
        
        // Test 6: Line along rectangle edge
        System.out.println("Test 6: Line Along Rectangle Edge");
        Line edgeLine = new Line(0.0, 10.0, 20.0, 10.0);
        List<Point> intersections5 = rect2.intersectionPoints(edgeLine);
        System.out.println("Number of intersections: " + intersections5.size());
        System.out.println("Note: Edge case - may have multiple interpretations\n");
        
        System.out.println("Rectangle Tests Completed!");
    }
}