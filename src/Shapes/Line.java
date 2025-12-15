package Shapes;

import java.util.List;

public class Line {
    final static double EPS = 0.00001; //אפסילון מסריח
    private Point start;
    private Point end;


    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line- אורך קטע  זה חישוב במרחק בין שתי הנקודות שלו מה שיש כבר בפוינט שורה מוגדרת כשתי נקודות מפוינט לכן נבצע עבור שורה הכנסה לפונקצית דיסטנס
    public double length() {
        return start().distance(end);

    }

    // Returns the middle point of the line - נחשב את וואי ואיקס החדשים
    public Point middle() {
        return (new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2));
    }

    // Returns the start point of the line - like get start
    public Point start() {
        return start;
    }

    // Returns the end point of the line - like get end
    public Point end() {
        return end;
    }

    private double gradient() {
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }

    private double getB() {
        return (this.start.getY() - (this.start.getX() * this.gradient()));
    }

    public Point p3(Line other) {
        double m1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        System.out.println(m1);
        System.out.println(m2);

        double b1 = start.getY() - (m1 * start.getX());
        double b2 = other.start.getY() - (m2 * other.start.getX());
        System.out.println(b1);
        System.out.println(b2);

        double x3 = ((b2 - b1) / (m1 - m2));
        double y3 = m1 * x3 + b1;
        System.out.println("x3= " + x3 + "y3= " + y3);
        return (new Point(x3, y3));
    }

    // הוסף את המתודות האלה למחלקת Line שלך במקום הקיימות:

    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // בדיקה אם הקווים מקבילים
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (Math.abs(denominator) < EPS) {
            // הקווים מקבילים או זהים
            return null;
        }

        // חישוב נקודת החיתוך
        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominator;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denominator;

        // בדיקה אם נקודת החיתוך נמצאת על שני הקווים
        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            double intersectX = x1 + t * (x2 - x1);
            double intersectY = y1 + t * (y2 - y1);
            return new Point(intersectX, intersectY);
        }

        return null;
    }
    // equals -- return true is the lines are equal, false otherwise -אפשר לקחת מפוינט את איקוול עבור סטאר של הנקודה ושל other
    public boolean equals(Line other) {
        if (start.equals(other.start) && end.equals(other.end)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(Point point) {
        if (point == null) return false;
        // For vertical lines: x coordinate equality within EPS
        if (Math.abs(this.start().getX() - this.end().getX()) < EPS) {
            if (Math.abs(point.getX() - this.start().getX()) > EPS) return false;
        } else {
            double expectedY = this.gradient() * point.getX() + this.getB();
            if (Math.abs(point.getY() - expectedY) > EPS) return false;
        }
        // Now check if within bounding box (with EPS)
        double minX = Math.min(this.start().getX(), this.end().getX()) - EPS;
        double maxX = Math.max(this.start().getX(), this.end().getX()) + EPS;
        double minY = Math.min(this.start().getY(), this.end().getY()) - EPS;
        double maxY = Math.max(this.start().getY(), this.end().getY()) + EPS;

        return point.getX() >= minX && point.getX() <= maxX
                && point.getY() >= minY && point.getY() <= maxY;
    }

    /**add for ex2**/

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this); //list of all the cut points between tne line and the rectangle
        if (intersectionPoints == null || intersectionPoints.isEmpty()){
            return null;
        }
        // now, we have at least one common point
        // so we need to check the distance(method from this class) between them to the line.start
        // defined min_distance that updating for every lowest dis
        // if the min_distance changed- save the point from the level
        //return the last point

        Point closest_point = intersectionPoints.get(0); //the first point
        double min_distance = closest_point.distance(this.start);
        for (int i = 1; i < intersectionPoints.size(); i++) { // check from the second point
            Point this_point = intersectionPoints.get(i);
            if (this_point.distance(this.start) < min_distance){
                min_distance = this_point.distance(this.start);
                closest_point = this_point;
            }
        }
        return closest_point;
    }

    public static void main(String[] args){
        Point p1 = new Point(4.0, 7.0);
        Point p2 = new Point(4.0, 6.0);
        Point p3 = new Point(2.0, 4.0);
        Point p4 = new Point(6.0, 4.0);

        Line line = new Line(p1,p2);
        Line other = new Line (p3, p4);
        //System.out.println(line.p3(other));
        System.out.println(line.isIntersecting(other));
    }
}