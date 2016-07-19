/*----------------|
|  CS350          |
|  Project #1     |
|  Trevor Murphy  |
|-----------------*/

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * NShape Class represents the different shapes constructed
 * with N sides (0 - Circle, 3 - Triangle, 4 - Square, etc.).
 * Extends Path2D.Double allowing very precise drawing.
 */

public class NShape extends Path2D.Double {

    /**
     * The NShape determines which shape to be drawn, then draws
     * the shape based on angles calculated from the rotation
     * method.
     *
     * @param g2d         Graphics2D is used for drawing.
     * @param origin      The center of the window.
     * @param radius      The value of the radius.
     * @param timesDrawn  The number of times the shape is drawn.
     * @param shape       Which polygon to draw.
     */

    public NShape(Graphics2D g2d, Point2D.Double origin, int radius, int timesDrawn, int shape) {

        double rotateDegrees;  // The angle value for rotating a point to create the polygon.
        double DegreesToDivide = Math.toRadians((double) 360 / timesDrawn);  // Evenly space out the radius
                                                                    // of each shape.

        // Change color based on which shape is chosen.

        switch (shape) {
            case 0:  g2d.setColor(Color.blue);    break;
            case 3:  g2d.setColor(Color.red);     break;
            case 4:  g2d.setColor(Color.orange);  break;
            case 5:  g2d.setColor(Color.magenta); break;
            default: g2d.setColor(Color.black);   break;
        }

        // The Center of the shape drawn. Point2D.Double used for extra precision.

        Point2D.Double centerOfShape = new Point2D.Double(origin.x, origin.y - radius);

        for (int i = 0; i < timesDrawn; i++) {

            // If the shape is a circle then just draw a circle.

            if (shape == 0) {
                g2d.drawOval(((int) (centerOfShape.x - radius)), ((int) (centerOfShape.y - radius)),
                            radius * 2, radius * 2);
            } else {
                rotateDegrees = Math.toRadians((double) 360 / shape);
                Point2D.Double shapeVertex = origin;                // The point that is rotated to build polygon.
                this.moveTo(shapeVertex.x, shapeVertex.y);
                for (int j = 0; j < shape - 1; j++) {
                    Point2D.Double nextShapeVertex = rotatePoint(centerOfShape, shapeVertex, rotateDegrees);
                    this.lineTo(nextShapeVertex.x, nextShapeVertex.y);
                    shapeVertex = nextShapeVertex;
                }

                this.closePath();  // Close the path and finish polygon.
                g2d.draw(this);
            }

            centerOfShape = rotatePoint(origin, centerOfShape, DegreesToDivide);  // Move to next point for polygon.
        }
    }

    /**
     * This method uses a rotation formula to rotate a point (vertex)
     * around an origin (origin) by a certain amount of degrees (radians).
     *
     * @param origin   The origin point.
     * @param vertex   The point to be rotated.
     * @param radians  How many radians to rotate point.
     * @return         A Point2D.Double which is a Point but more
     *                 accurate because it uses the Double type.
     */

    private Point2D.Double rotatePoint(Point2D.Double origin, Point2D.Double vertex, double radians) {

        double s = Math.sin(radians);
        double c = Math.cos(radians);
        double x = ((vertex.x-origin.x) * c) - ((vertex.y-origin.y) * s) + origin.x;
        double y = ((vertex.x-origin.x) * s) + ((vertex.y-origin.y) * c) + origin.y;

        return new Point2D.Double(x, y);
    }
}
