package Tests;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("=== SIMPLE TEST START ===");

        try {
            // Step 1: Create GUI
            System.out.println("1. Creating GUI...");
            GUI gui = new GUI("Test", 300, 400);
            System.out.println("   ✓ GUI created");

            // Step 2: Get DrawSurface
            System.out.println("2. Getting DrawSurface...");
            DrawSurface d = gui.getDrawSurface();
            System.out.println("   ✓ DrawSurface: " + (d != null ? "OK" : "NULL"));

            // Step 3: Draw white background
            System.out.println("3. Drawing background...");
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 300, 400);
            System.out.println("   ✓ Background drawn");

            // Step 4: Draw test circle (without Ball class)
            System.out.println("4. Drawing test circle...");
            d.setColor(Color.RED);
            d.fillCircle(150, 200, 30);
            System.out.println("   ✓ Circle drawn at (150, 200) radius 30");

            // Step 5: Draw text
            System.out.println("5. Drawing text...");
            d.setColor(Color.BLACK);
            d.drawText(100, 100, "TEST", 20);
            System.out.println("   ✓ Text drawn");

            // Step 6: Show
            System.out.println("6. Showing GUI...");
            gui.show(d);
            System.out.println("   ✓ GUI shown");

            System.out.println("\n=== TEST COMPLETE ===");
            System.out.println("You should see:");
            System.out.println("  - White background");
            System.out.println("  - Red circle in the middle");
            System.out.println("  - Text 'TEST' at top");

            // Wait 5 seconds
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(5000);

        } catch (Exception e) {
            System.out.println("\n!!! ERROR !!!");
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}