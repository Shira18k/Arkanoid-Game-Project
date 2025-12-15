package Shapes;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    protected Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    // after checking the point and defined the lines of rectangle - this fun checking
    // intersection between them to some line

    public java.util.List<Point> intersectionPoints(Line line){
        List<Point> pointsList = new ArrayList<Point>();
        Line[] l = this.fromR_ToLines();
        for (int i=0; i<4; i++){
            if(l[i].isIntersecting(line)){
                Point p_cut = l[i].intersectionWith(line);
                pointsList.add(p_cut);
            }
        }
        return pointsList;
    }


    //find the points of the rectangle and return in an array
    // important - the rectangle are aligned with the axes

    private Point[] fromR_ToPoints(){
        Point[] point_of_r = new Point[4];

        Point uppLeft = this.upperLeft;
        double x =uppLeft.getX();
        double y =  uppLeft.getY();

        Point p_upp_left = new Point(x,y);
        point_of_r[0] = p_upp_left;
        Point p_upp_right = new Point(x + this.getWidth(), y );
        point_of_r[1] = p_upp_right;
        Point p_down_left = new Point(x, y + this.getHeight());
        point_of_r[2] = p_down_left;
        Point p_down_right = new Point(x + this.getWidth(), y + this.getHeight());
        point_of_r[3] = p_down_right;
        return point_of_r;
    }

    //find the Lines of the rectangle and return in an array by the points
    private Line[] fromR_ToLines(){
        Point[] p = this.fromR_ToPoints();
        Line[] lines_of_r = new Line[4];

        Line l_left= new Line(p[0],p[2]);
        lines_of_r[0] = l_left;
        Line l_right = new Line(p[1],p[3]);
        lines_of_r[1] = l_right;
        Line l_down = new Line(p[2], p[3]);
        lines_of_r[2] = l_down;
        Line l_upp = new Line(p[0], p[1]);
        lines_of_r[3] = l_upp;
        return lines_of_r;
    }

    // Return the width and height of the rectangle
    public double getWidth(){
        return this.width;
    }
    public double getHeight(){
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){
        return this.upperLeft;

    }
    public static void main(String[] args){
//        Rectangle r = new Rectangle(new Point(0.0,0.0), 5.0 , 3);
//        Line l = new Line(new Point(0.0,0.0), new Point(3.0,-3.0));
//        System.out.println(r.intersectionPoints(l));
    }
}
