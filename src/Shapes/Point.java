package Shapes;

public class Point {
    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return (Math.sqrt((x-other.getX())*(x-other.getX())+(y-other.getY())*(y-other.getY())));

    }
    public boolean isClose(Point p, double epsilon) {
        if (this.distance(p) < epsilon) {
            return true;
        }
        return false;
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return  (x == other.getX() && y == other.getY());
    }

    // Return the x and y values of this point
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public static void main(String args[]){
        Point p1 = new Point(3,5);
        Point p2 = new Point(5,5);
        System.out.println(p1.equals(p2));
        System.out.println(p1.distance(p2));
    }
}