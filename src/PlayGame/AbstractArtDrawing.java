package PlayGame;

import Shapes.Line;
import Shapes.Point;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] Lines = new Line[10];

        for (int i = 0; i < 10; i++) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300

            int r = 3;

            d.drawLine(x1, y1, x2, y2); //  עבור שימוש במחלקה הנוכחית אוטומט שחור
            Line L1 = new Line(x1, y1, x2, y2);// הליין הנוכחי לצורך בדיקת חיתוך במחלקה ליין

            d.setColor(Color.BLUE); // עד כאן שחור אוטומט ויצר ליין, נעדכן לכחול ניצור נקודה ואז נחזיר בסוף הלולאה לאוטמאט שזה שחור
            int middleX = (x1 + x2) / 2;
            int middleY = (y1 + y2) / 2;
            d.fillCircle(middleX, middleY, r);

            Lines[i] = L1;
            d.setColor(Color.black);
        }

        d.setColor(Color.RED);// from now is red
        for (int i=0; i<9; i++) { //for each line in Shapes.Line[]
            for (int j = i+1; j <10; j++) { // check with the other lines
                if (Lines[i].isIntersecting(Lines[j])) { // check in the fun that return if they have a cut point or not
                    Point P3 = Lines[i].intersectionWith(Lines[j]); // the cut point if true
                    d.fillCircle((int)Math.round(P3.getX()), (int)Math.round(P3.getY()), 3); //קאסטינג מדאבל לאינט לא מדויק
                }
            }
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}