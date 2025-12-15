package PlayGame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Engine.Ball;
import Shapes.Point;
import Engine.Velocity;
import Sprites.Block;
import Engine.GameEnvironment;
import Shapes.Rectangle;

import java.awt.*;
import java.util.Random;

public class Main1_justBalls {
    public static void main(String[] args) {
        Random rand = new Random();

        // יצירת סביבה
        GameEnvironment env = new GameEnvironment();

        // ⭐⭐⭐ הקירות של הפריים = 4 בלוקים ⭐⭐⭐

        // קיר שמאלי (x=0, y=0, רוחב=10, גובה=400)
        Rectangle leftRect = new Rectangle(new Point(0, 0), 10, 400);
        Block leftWall = new Block(leftRect, Color.gray);
        env.addCollidable(leftWall);

        // קיר ימני (x=290, y=0, רוחב=10, גובה=400)
        Rectangle rightRect = new Rectangle(new Point(290, 0), 10, 400);
        Block rightWall = new Block(rightRect,Color.gray);
        env.addCollidable(rightWall);

        // קיר עליון (x=10, y=0, רוחב=280, גובה=10)
        Rectangle topRect = new Rectangle(new Point(10, 0), 280, 10);
        Block topWall = new Block(topRect,Color.gray);
        env.addCollidable(topWall);

        // קיר תחתון (x=10, y=390, רוחב=280, גובה=10)
        Rectangle bottomRect = new Rectangle(new Point(10, 390), 280, 10);
        Block bottomWall = new Block(bottomRect,Color.gray);
        env.addCollidable(bottomWall);

        System.out.println("✓ 4 walls (Block) added to environment\n");

        GUI gui = new GUI("Balls Animation - CHECK", 300, 400);
        Sleeper sleeper = new Sleeper();

        Ball[] balls = new Ball[6];

        // יצירת כדורים עם environment
        for (int i = 0; i < 6; i++) {
            int x = rand.nextInt(200) + 50;  // 50-250
            int y = rand.nextInt(300) + 50;  // 50-350
            Point point = new Point(x, y);

            int size = 10;

            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN,
                    Color.YELLOW, Color.MAGENTA, Color.CYAN};

            // ⭐ עם Ball!
            Ball current = new Ball(point, size, colors[i], env);

            int angle = rand.nextInt(360);
            double speed = 5; // מהירות בינונית
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);

            current.setVelocity(v);
            balls[i] = current;

            System.out.println("Ball[" + i + "]: pos(" + x + "," + y +
                    ") vel(" + v.getDX() + "," + v.getDY() + ")");
        }

        System.out.println("\nStarting animation...\n");

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // רקע לבן
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 300, 400);

            // צייר את הקירות (בלוקים אפורים)
            d.setColor(Color.WHITE);
            // קיר שמאלי
            d.fillRectangle(0, 0, 10, 400);
            // קיר ימני
            d.fillRectangle(290, 0, 10, 400);
            // קיר עליון
            d.fillRectangle(10, 0, 280, 10);
            // קיר תחתון
            d.fillRectangle(10, 390, 280, 10);

            // הזז וצייר כל כדור
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
