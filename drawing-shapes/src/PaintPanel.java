/*----------------|
|  CS350          |
|  Project #1     |
|  Trevor Murphy  |
|-----------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * PaintPanel extends JPanel and is used as a "drawing board."
 * This class is added to the Shapes class as a component for
 * drawing graphics.
 */

public class PaintPanel extends JPanel {

    private int radius;
    private int shape;
    private int timesDrawn;


    public PaintPanel(int shape, int radius, int timesDrawn) {
        this.shape = shape;
        this.radius = radius;
        this.timesDrawn = timesDrawn;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        int xCenter = super.getWidth() / 2;
        int yCenter = super.getHeight() / 2;

        // Initialize origin

        Point2D.Double origin = new Point2D.Double(xCenter, yCenter);

        new NShape(g2d, origin, radius, timesDrawn, shape);

    }
}
