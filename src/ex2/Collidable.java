package ex2;
import ex1.Velocity;
import ex1.Point;

public interface Collidable {
/**Return the "collision shape" of the object
//     זה כמו "שכבת ביטחון" לאובייקט שנשלח לפונקציה אחרת לצורך בדיקת חיתוך,
//     כלומר במקום שאובייקט תחת מחלקה מסוימת ישלח כמו שהוא עם כל הפרטים לפונקציה חיצונית
//     , הפונקציה הזו כאשר נממש אותה תחת המחלקה תקבל את האובייקט ותחזיר אותו כצורה פיזית עם הערכים
 שזה מה שהפונקצית חיתוך צריכה בשביל החיתוך של ישר עם הצורה*/

    Rectangle getCollisionRectangle();

    /** Notify the object that we collided with it at collisionPoint with
     a given velocity.
     The return is the new velocity expected after the hit (based on
     the force the object inflicted on us).**/
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
