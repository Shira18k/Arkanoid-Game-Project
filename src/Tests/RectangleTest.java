package Tests;

import Shapes.Point;
import Shapes.Line;
import Shapes.Rectangle;

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

        // Test 2: Intersection with vertical line (crosses top and bottom)
        System.out.println("Test 2: Intersection - Vertical Line");
        // מלבן: (0,10) רוחב=20, גובה=10 → פינות: (0,10), (20,10), (0,20), (20,20)
        Rectangle rect2 = new Rectangle(new Point(0.0, 10.0), 20.0, 10.0);
        // קו אנכי מ-(10, 5) ל-(10, 25) - עובר דרך כל המלבן
        Line verticalLine = new Line(10.0, 5.0, 10.0, 25.0);
        List<Point> intersections = rect2.intersectionPoints(verticalLine);
        System.out.println("Rectangle: top-left(0,10), bottom-right(20,20)");
        System.out.println("Vertical line: (10,5) → (10,25)");
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
        // קו אופקי מ-(-5, 15) ל-(25, 15) - עובר דרך אמצע המלבן
        Line horizontalLine = new Line(-5.0, 15.0, 25.0, 15.0);
        List<Point> intersections2 = rect2.intersectionPoints(horizontalLine);
        System.out.println("Horizontal line: (-5,15) → (25,15)");
        System.out.println("Number of intersections: " + intersections2.size());
        System.out.println("Expected: 2 (left and right sides)");
        if (intersections2.size() > 0) {
            for (Point p : intersections2) {
                System.out.println("  Intersection at: (" + p.getX() + ", " + p.getY() + ")");
            }
        }
        System.out.println("Pass: " + (intersections2.size() == 2) + "\n");

        // Test 4: Diagonal line through rectangle
        System.out.println("Test 4: Intersection - Diagonal Line");
        Line diagonalLine = new Line(-5.0, 15.0, 25.0, 15.0);
        List<Point> intersections3 = rect2.intersectionPoints(diagonalLine);
        System.out.println("Number of intersections: " + intersections3.size());
        System.out.println("Expected: 2");
        System.out.println("Pass: " + (intersections3.size() == 2) + "\n");

        // Test 5: No intersection
        System.out.println("Test 5: No Intersection");
        Line noIntersectLine = new Line(30.0, 5.0, 40.0, 8.0);
        List<Point> intersections4 = rect2.intersectionPoints(noIntersectLine);
        System.out.println("Number of intersections: " + intersections4.size());
        System.out.println("Expected: 0");
        System.out.println("Pass: " + (intersections4.size() == 0) + "\n");

        // Test 6: Line along rectangle top edge
        System.out.println("Test 6: Line Along Rectangle Edge");
        Line edgeLine = new Line(0.0, 10.0, 20.0, 10.0);
        List<Point> intersections5 = rect2.intersectionPoints(edgeLine);
        System.out.println("Number of intersections: " + intersections5.size());
        System.out.println("Note: Line on top edge - implementation dependent\n");

        // Test 7: Clear diagonal example
        System.out.println("Test 7: Clear Diagonal");
        // מלבן פשוט: (10,10) רוחב=20, גובה=20 → (10,10) עד (30,30)
        Rectangle rect3 = new Rectangle(new Point(10.0, 10.0), 20.0, 20.0);
        // קו אלכסוני מ-(5,15) ל-(35,25)
        Line diagLine = new Line(5.0, 15.0, 35.0, 25.0);
        List<Point> intersections6 = rect3.intersectionPoints(diagLine);
        System.out.println("Rectangle: (10,10) to (30,30)");
        System.out.println("Diagonal line: (5,15) → (35,25)");
        System.out.println("Number of intersections: " + intersections6.size());
        System.out.println("Expected: 2");
        if (intersections6.size() > 0) {
            for (Point p : intersections6) {
                System.out.println("  Intersection at: (" + p.getX() + ", " + p.getY() + ")");
            }
        }
        System.out.println("Pass: " + (intersections6.size() == 2) + "\n");

        System.out.println("Rectangle Tests Completed!");
    }
}