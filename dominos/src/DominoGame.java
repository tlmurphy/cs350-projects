/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import javax.swing.*;

/**
 * Main Class for the Dominoes Project. Extends JFrame
 * to build the window.
 *
 * @author Trevor Murphy
 * @version 1.0
 */

public class DominoGame extends JFrame {

    /**
     * Constructor for DominoGame class.
     * Calls the initialization method.
     */

    public DominoGame() {
        super("Dominos!");
        init();
    }

    /**
     * Initialize the JFrame window.
     * Add JPanel and make it request
     * focus so that key events work.
     */

    public void init () {
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //App starts in center of screen
        //setResizable(false);
        DominoPanel panel = new DominoPanel();
        add(panel);
        panel.requestFocusInWindow();
    }

    /**
     * Main function that creates a new DominoGame
     * instance.
     * @param args
     */

    public static void main(String[] args) {
        new DominoGame();
    }
}
