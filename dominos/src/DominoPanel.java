/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class DominoPanel acts as a JPanel that
 * implements various event listeners.
 * This is where the drawing happens.
 */

public class DominoPanel extends JPanel implements
        MouseListener, MouseMotionListener, KeyListener {

    private ArrayList<CDomino> doms;

    private CDomino dominoToBeMoved;
    private int m_nOffsetX; // Diff between cursor and top-left corner
    private int m_nOffsetY;

    // double buffering
    private Image backBuffer;
    private Graphics gBackBuffer;

    private boolean isInitialized;

    /**
     * DominoPanel constructor.
     * Adds event listeners.
     */

    public DominoPanel() {
        isInitialized = false;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }

    /**
     * Initialize Domino Panel and
     * create CDomino objects.
     */

    public void init() {
        int x = 80;
        int y1 = 35;                 // Player 1 Start
        int y2 = getHeight() - 115;  // Player 2 Start
        int gap1 = 0;
        int gap2 = 0;

        // Create and shuffle random nums to choose random dominoes.
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 27; i++)
        {
            randoms.add(i);
        }
        Collections.shuffle(randoms);

        doms = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            int dType = randoms.get(i);
            // Player 1 Dominoes
            if (i < 7) {
                CDomino firstDom = new CDomino(dType, x + gap1, y1);
                doms.add(firstDom);
                gap1 += 80;
            }

            // Player 2 Dominoes
            else {
                CDomino firstDom = new CDomino(dType, x + gap2, y2);
                doms.add(firstDom);
                gap2 += 80;
            }
        }
        dominoToBeMoved = null;

        // Create back buffer.
        backBuffer  = createImage(getSize().width, getSize().height);
        gBackBuffer = backBuffer.getGraphics();
    }

    /**
     * Handles the drawing of all objects.
     * @param g
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInitialized) {
            isInitialized = true;
            init();
        }

        // Clear back buffer
        gBackBuffer.setColor(Color.white);
        gBackBuffer.clearRect(0, 0, getSize().width, getSize().height);

        // Draw all dominoes to back buffer.
        for (int i = 0; i < doms.size(); i++) {
            CDomino d = doms.get(i);
            d.draw(gBackBuffer);
        }

        // Copy from back buffer to front.
        g.drawImage(backBuffer, 0, 0, null);

        // Draw division lines on game board.
        int firstLineY  = getHeight()/5;
        int secondLineY = getHeight() - firstLineY;

        g.setColor(Color.BLUE);
        g.drawLine(0, firstLineY, getWidth(), firstLineY);
        g.drawLine(0, secondLineY, getWidth(), secondLineY);
    }

    /**
     * Handles if right click is pressed on domino.
     * Domino will then rotate -90 degrees.
     *
     * Also determines if player is about to drag
     * the domino by focusing on a clicked domino.
     * @param e
     */

    @Override
    public void mousePressed(MouseEvent e) {

        // Rotate domino if right clicked.
        if (e.isMetaDown()) {
            for (int i = 0; i < doms.size(); i++) {
                CDomino d = doms.get(i);
                if (d.isInside(e.getX(), e.getY())) {
                    d.setState((d.getState() + 1) % 4);
                    d.rotate(d.getX(), d.getY());
                    repaint();
                    break;
                }
            }
        }

        // Focus on clicked domino.
        for (int i = 0; i < doms.size(); i++) {
            CDomino d = doms.get(i);
            if (d.isInside(e.getX(), e.getY())) {
                dominoToBeMoved = d;
                m_nOffsetX = e.getX() - dominoToBeMoved.getX();
                m_nOffsetY = e.getY() - dominoToBeMoved.getY();
                repaint();
                break;
            }
        }
    }

    /**
     * Move Domino based on mouse movement.
     * @param e
     */

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.isMetaDown()) return;

        if (dominoToBeMoved != null) {
            dominoToBeMoved.translate(e.getX() - m_nOffsetX,
                    e.getY() - m_nOffsetY);
            repaint();
        }
    }

    /**
     * Handles if "r" key is pressed, game is reset.
     * @param e
     */

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // 82 is the key code for "r".
        if (code == 82) {
            for (int i = 0; i < doms.size(); i++) {
                CDomino d = doms.get(i);
                d.reset();
            }
            repaint();
        }
    }

    /**
     * No domino is selected anymore.
     * @param e
     */

    @Override
    public void mouseReleased(MouseEvent e) {
        dominoToBeMoved = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }



    @Override
    public void keyReleased(KeyEvent e) {
    }
}
