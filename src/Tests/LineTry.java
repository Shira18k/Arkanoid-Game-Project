package Tests;
import Shapes.Point;

public class LineTry {
    //final static double EPS = 0.00001; //אפסילון מסריח
    private Point start;
    private Point end;



    // constructors
    public LineTry(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    public LineTry(double x1, double y1, double x2, double y2) {
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

    private double gradient(){
        return ((this.start.getY() - this.end.getY())/ (this.start.getX() - this.end.getX()));
    }

    private double getB(){
        return (this.start.getY() - (this.start.getX()*this.gradient()));
    }

    public Point p3(LineTry other) {
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
        System.out.println("x3= "+x3 +"y3= "+y3 );
        return (new Point(x3, y3));
    }

    // Returns true if the lines intersect, false otherwise

    public boolean isIntersecting(LineTry other) {
        LineTry line = new LineTry(start, end);
        //double m1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double m1 = this.gradient();
        double m2 = other.gradient();
        if (Double.isInfinite(m1) || (Double.isInfinite(m2))) {
            System.out.println("one of the line plumb :( ");
            return false;
        }
        if (m1 == m2) {
            System.out.println("they are parallels :( ");
            return false; // מקבילים
        }
        double x3 = (line.p3(other)).getX();
        double y3 = (line.p3(other).getY());

        double left_max_X = Math.max(start.getX(), other.start.getX());
        double right_min_X = Math.min(end.getX(), other.end.getX());
        //the tow low values
        double lower_start = Math.min(start.getY(), other.start.getY());
        double lower_end = Math.min(end.getY(), other.end.getY());
        double low_max_Y = Math.max(lower_end, lower_start);
        //the tow high values
        double higher_start = Math.max(start.getY(), other.start.getY());
        double higher_end = Math.max(end.getY(), other.end.getY());
        double high_min_Y = Math.min(higher_end, higher_start);

        if ((x3 >= left_max_X && x3 <= right_min_X) && (y3 >= low_max_Y && y3 <= high_min_Y)) {
            System.out.println("we have Intersecting :) ");
            return true;
        }
        return true;
    }

    public Point intersectionWith(LineTry other) {
        if (isIntersecting(other)){
            return (p3(other));
        }
        return null;
    }

    // equals -- return true is the lines are equal, false otherwise -אפשר לקחת מפוינט את איקוול עבור סטאר של הנקודה ושל other
    public boolean equals(LineTry other) {
        if (start.equals(other.start) && end.equals(other.end)){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        Point p1 = new Point(4.0, 7.0);
        Point p2 = new Point(4.0, 6.0);
        Point p3 = new Point(2.0, 4.0);
        Point p4 = new Point(6.0, 4.0);

        LineTry line = new LineTry(p1,p2);
        LineTry other = new LineTry(p3, p4);
        //System.out.println(line.p3(other));
        System.out.println(line.isIntersecting(other));
    }
}