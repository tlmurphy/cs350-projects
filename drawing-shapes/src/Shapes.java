/*----------------|
|  CS350          |
|  Project #1     |
|  Trevor Murphy  |
|-----------------*/

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Main Class for the DrawingShapes Project. Extends JFrame
 * to build the window.
 *
 * @author Trevor Murphy
 * @version 1.1 (Added functionality for ALL variations of TimesDrawn,
 *              not just numbers divisible by 360).
 *              See NShape.java lines 34 and 59.
 */

public class Shapes extends JFrame {

    /**
     * Constructor for Shapes class.
     * Calls the initialization method
     * and adds the JPanel component.
     *
     * @param shape      Polygon type: 0 -- Circle
     *                                 3 -- Triangle
     *                                 4 -- Square
     *                                 5 -- Pentagon
     * @param radius     Length from center of frame to the center
     *                   of the shape.
     * @param timesDrawn The number of times the polygon will be drawn.
     */

    public Shapes(int shape, int radius, int timesDrawn) {
        super("Draw a Shape!");
        init();
        add(new PaintPanel(shape, radius, timesDrawn));
    }

    /**
     * Initialize the JFrame window.
     */

    public void init() {
        setVisible(true);
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //App starts in center of screen
    }

    /**
     * Main Function.
     * Accepts a text file as command line
     * argument. Reads three integers.
     *
     * First integer is the shape type, second is
     * the radius value, and third is the number
     * of times the polygon should be drawn.
     *
     * Calls the constructor of the Shapes class.
     *
     * @param args Command line arguments.
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {

        // Check for input file and exit if not found

        if (args.length != 1) {
            System.out.println("NO INPUT FILE FOUND...EXITING");
            System.exit(-1);
        }

        File inputFile = new File(args[0]);
        Scanner fileInput = new Scanner(new FileReader(inputFile));
        int shape = fileInput.nextInt();
        int radius = fileInput.nextInt();
        int timesDrawn = fileInput.nextInt();
        new Shapes(shape, radius, timesDrawn);

    }
}
