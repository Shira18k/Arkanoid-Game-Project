//public class saveBullshitFromLine {
//    public ex1.Point p3(ex1.Line other) {
//        double m1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
//        double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
//        double b1 = start.getY() - (m1 * start.getX());
//        double b2 = other.start.getY() - (m2 * other.start.getX());
//        double x3 = ((b2 - b1) / (m1 - m2));
//        double y3 = m1 * x3 + b1;
//        return (new ex1.Point(x3, y3));
//    }
//
//    // Returns true if the lines intersect, false otherwise
//    public boolean isIntersecting(ex1.Line other) {
//        double m1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
//        double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
//        if (m1 == m2) { // checking gradient
//            return false;
//        }
//        double b1 = start.getY() - (m1 * start.getX());
//        double b2 = other.start.getY() - (m1 * other.start.getX());
//        double x3 = ((b2 - b1) / (m1 - m2));
//        if (x3 >= Math.min(start.getX(), end.getX()) && x3 <= Math.max(start.getX(), end.getX()) && x3 >= Math.min(other.start.getX(), other.end.getX()) &&
//                x3 <= Math.max(other.start.getX(), other.end.getX())) {
//            ////if (x3 >= Math.min(start.getX(),other.start.getX()) && x3 <= Math.max(end.getX(), other.end.getX())){//check domain for x3 מקורי סגור
//            double y3 = m1 * x3 + b1;
//            if (y3 >= Math.min(start.getY(), end.getY()) && y3 <= Math.max(start.getY(), end.getY()) && y3 >= Math.min(other.start.getY(), other.end.getY()) && y3 <= Math.max(other.start.getY(), other.end.getX())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    // Returns the intersection point if the lines intersect,
//    // and null otherwise. - להחזיר את הנקודת חיתוך במידה ויש
//    public ex1.Point intersectionWith(ex1.Line other) {
//        if (isIntersecting(other)){
//            return (p3(other));
//        }
//        return null;
//    }
//
//    // equals -- return true is the lines are equal, false otherwise -אפשר לקחת מפוינט את איקוול עבור סטאר של הנקודה ושל other
//    public boolean equals(ex1.Line other) {
//        if (start.equals(other.start) && end.equals(other.end)){
//            return true;
//        }else{
//            return false;
//        }
//    }
//}
