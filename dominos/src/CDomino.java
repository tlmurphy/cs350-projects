/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import java.awt.*;

/**
 * CDomino class Represents the individual dominoes.
 */

public class CDomino {

    private int type;          // Which domino piece.

    private int x;             // Pivot Point X.
    private int y;             // Pivot Point Y.
    private int width = 40;    // Width and height of domino.
    private int height = 80;

    private int state = 0;     // Orientation of domino, starts upright (0)
    private int resetX;        // Used in resetting position.
    private int resetY;

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setState(int state) { this.state = state; }
    public int getState() { return this.state; }

    /**
     * CDomino constructor.
     * @param type Which domino piece.
     * @param x    Pivot point x.
     * @param y    Pivot point y.
     */

    public CDomino(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        resetX = x;
        resetY = y;
    }

    /**
     * Copy constructor.
     * @param copy A copy of CDomino.
     */

    public CDomino(CDomino copy) {
        this.type = copy.type;
        this.x = copy.x;
        this.y = copy.y;
        resetX = copy.x;
        resetY = copy.y;
    }

    // Various drawing methods for drawing
    // the pips on the dominoes. Contains
    // switch statement because, based on
    // domino orientation, pip needs to
    // be rotated.

    private void drawTopLeft1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + 4;
                break;
            case 1:
                pipX = this.x - 12;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - 12;
                break;
            case 3:
                pipX = this.x + 4;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMidLeft1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + 16;
                break;
            case 1:
                pipX = this.x - 24;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - 24;
                break;
            case 3:
                pipX = this.x + 16;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawBotLeft1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + 28;
                break;
            case 1:
                pipX = this.x - 36;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - 36;
                break;
            case 3:
                pipX = this.x + 28;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawTopLeft2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + (height/2 + 4);
                break;
            case 1:
                pipX = this.x - (height/2) - 12;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - (height/2) - 12;
                break;
            case 3:
                pipX = this.x + (height/2) + 4;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMidLeft2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + (height/2 + 16);
                break;
            case 1:
                pipX = this.x - (height/2) - 24;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - (height/2) - 24;
                break;
            case 3:
                pipX = this.x + (height/2) + 16;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawBotLeft2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 4;
                pipY = this.y + (height/2 + 28);
                break;
            case 1:
                pipX = this.x - (height/2) - 36;
                pipY = this.y + 4;
                break;
            case 2:
                pipX = this.x - 12;
                pipY = this.y - (height/2) - 36;
                break;
            case 3:
                pipX = this.x + (height/2) + 28;
                pipY = this.y - 12;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawTopRight1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + 4;
                break;
            case 1:
                pipX = this.x - 12;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - 12;
                break;
            case 3:
                pipX = this.x + 4;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMidRight1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + 16;
                break;
            case 1:
                pipX = this.x - 24;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - 24;
                break;
            case 3:
                pipX = this.x + 16;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawBotRight1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + 28;
                break;
            case 1:
                pipX = this.x - 36;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - 36;
                break;
            case 3:
                pipX = this.x + 28;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawTopRight2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + (height/2 + 4);
                break;
            case 1:
                pipX = this.x - (height/2) - 12;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - (height/2) - 12;
                break;
            case 3:
                pipX = this.x + (height/2) + 4;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMidRight2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + (height/2 + 16);
                break;
            case 1:
                pipX = this.x - (height/2) - 24;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - (height/2) - 24;
                break;
            case 3:
                pipX = this.x + (height/2) + 16;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawBotRight2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = (this.x + width) - 12;
                pipY = this.y + (height/2 + 28);
                break;
            case 1:
                pipX = this.x - (height/2) - 36;
                pipY = (this.y + width) - 12;
                break;
            case 2:
                pipX = (this.x - width) + 4;
                pipY = this.y - (height/2) - 36;
                break;
            case 3:
                pipX = this.x + (height/2) + 28;
                pipY = (this.y - width) + 4;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMid1(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 16;
                pipY = this.y + 16;
                break;
            case 1:
                pipX = this.x - 24;
                pipY = this.y + 16;
                break;
            case 2:
                pipX = this.x - 24;
                pipY = this.y - 24;
                break;
            case 3:
                pipX = this.x + 16;
                pipY = this.y - 24;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void drawMid2(Graphics g) {
        int pipX = 0;
        int pipY = 0;
        switch (state) {
            case 0:
                pipX = this.x + 16;
                pipY = (this.y + height) - 24;
                break;
            case 1:
                pipX = (this.x - height) + 16;
                pipY = this.y + 16;
                break;
            case 2:
                pipX = this.x - 24;
                pipY = (this.y - height) + 16;
                break;
            case 3:
                pipX = (this.x + height) - 24;
                pipY = this.y - 24;
                break;
        }
        g.fillOval(pipX, pipY, 8, 8);
    }

    private void draw1Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawMid1(g);
        } else {
            drawMid2(g);
        }
    }

    private void draw2Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawTopLeft1(g);
            drawBotRight1(g);
        } else {
            drawTopLeft2(g);
            drawBotRight2(g);
        }
    }

    private void draw3Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawTopLeft1(g);
            drawMid1(g);
            drawBotRight1(g);
        } else {
            drawTopLeft2(g);
            drawMid2(g);
            drawBotRight2(g);
        }
    }

    private void draw4Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawTopLeft1(g);
            drawBotRight1(g);
            drawTopRight1(g);
            drawBotLeft1(g);
        } else {
            drawTopLeft2(g);
            drawBotRight2(g);
            drawTopRight2(g);
            drawBotLeft2(g);
        }
    }

    private void draw5Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawTopLeft1(g);
            drawBotRight1(g);
            drawTopRight1(g);
            drawBotLeft1(g);
            drawMid1(g);
        } else {
            drawTopLeft2(g);
            drawBotRight2(g);
            drawTopRight2(g);
            drawBotLeft2(g);
            drawMid2(g);
        }

    }

    private void draw6Pip(Graphics g, int topOrBottom) {
        if (topOrBottom == 1) {
            drawTopLeft1(g);
            drawMidLeft1(g);
            drawBotLeft1(g);

            drawTopRight1(g);
            drawMidRight1(g);
            drawBotRight1(g);
        } else {
            drawTopLeft2(g);
            drawMidLeft2(g);
            drawBotLeft2(g);

            drawTopRight2(g);
            drawMidRight2(g);
            drawBotRight2(g);
        }
    }

    /**
     * Draw the Domino's divider line.
     * @param g Graphics
     */

    private void drawDivider(Graphics g) {
        switch (state) {
            case 0:
                g.drawLine(this.x, this.y + height/2, this.x + width, this.y + height/2);
                break;
            case 1:
                g.drawLine(this.x - height/2, this.y, this.x - height/2, this.y + width);
                break;
            case 2:
                g.drawLine(this.x, this.y - height/2, this.x - width, this.y - height/2);
                break;
            case 3:
                g.drawLine(this.x + height/2, this.y, this.x + height/2, this.y - height/2);
                break;
        }
    }

    /**
     * Draw Domino.
     * @param g
     */

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);

        Point p1 = new Point(this.x, this.y);
        Point p2 = new Point(p1.x+width, p1.y);
        Point p3 = new Point(p1.x+width, p1.y+height);
        Point p4 = new Point(p1.x, p1.y+height);

        if (state == 1) {
            p2 = new Point(p1.x, p1.y+width);
            p3 = new Point(p1.x - height, p1.y+width);
            p4 = new Point(p1.x - height, p1.y);
        } else if (state == 2) {
            p2 = new Point(p1.x-width, p1.y);
            p3 = new Point(p1.x - width, p1.y - height);
            p4 = new Point(p1.x, p1.y - height);
        } else if (state == 3) {
            p2 = new Point(p1.x, p1.y-width);
            p3 = new Point(p1.x + height, p1.y-width);
            p4 = new Point(p1.x +height, p1.y);
        }
        int pointsX[] = {p1.x, p2.x, p3.x, p4.x};
        int pointsY[] = {p1.y, p2.y, p3.y, p4.y};
        g.drawPolygon(pointsX, pointsY, 4);
        g.setColor(Color.RED);
        drawDivider(g);

        // 28 possible types of domino.
        switch (this.type) {
            case 0:
                break;
            case 1:
                draw1Pip(g, 2);
                break;
            case 2:
                draw1Pip(g, 1);
                draw1Pip(g, 2);
                break;
            case 3:
                draw2Pip(g, 2);
                break;
            case 4:
                draw1Pip(g, 1);
                draw2Pip(g, 2);
                break;
            case 5:
                draw2Pip(g, 1);
                draw2Pip(g, 2);
                break;
            case 6:
                draw3Pip(g, 2);
                break;
            case 7:
                draw1Pip(g, 1);
                draw3Pip(g, 2);
                break;
            case 8:
                draw2Pip(g, 1);
                draw3Pip(g, 2);
                break;
            case 9:
                draw3Pip(g, 1);
                draw3Pip(g, 2);
                break;
            case 10:
                draw4Pip(g, 2);
                break;
            case 11:
                draw1Pip(g, 1);
                draw4Pip(g, 2);
                break;
            case 12:
                draw2Pip(g, 1);
                draw4Pip(g, 2);
                break;
            case 13:
                draw3Pip(g, 1);
                draw4Pip(g, 2);
                break;
            case 14:
                draw4Pip(g, 1);
                draw4Pip(g, 2);
                break;
            case 15:
                draw5Pip(g, 2);
                break;
            case 16:
                draw1Pip(g, 1);
                draw5Pip(g, 2);
                break;
            case 17:
                draw2Pip(g, 1);
                draw5Pip(g, 2);
                break;
            case 18:
                draw3Pip(g, 1);
                draw5Pip(g, 2);
                break;
            case 19:
                draw4Pip(g, 1);
                draw5Pip(g, 2);
                break;
            case 20:
                draw5Pip(g, 1);
                draw5Pip(g, 2);
                break;
            case 21:
                draw6Pip(g, 2);
                break;
            case 22:
                draw1Pip(g, 1);
                draw6Pip(g, 2);
                break;
            case 23:
                draw2Pip(g, 1);
                draw6Pip(g, 2);
                break;
            case 24:
                draw3Pip(g, 1);
                draw6Pip(g, 2);
                break;
            case 25:
                draw4Pip(g, 1);
                draw6Pip(g, 2);
                break;
            case 26:
                draw5Pip(g, 1);
                draw6Pip(g, 2);
                break;
            case 27:
                draw6Pip(g, 1);
                draw6Pip(g, 2);
                break;
        }
    }

    /**
     * Check if point(x, y) is inside the domino.
     * @param x x coordinate of point.
     * @param y y coordinate of point.
     * @return
     */

    public boolean isInside(int x, int y) {
        if (this.state == 0) {
            if ((x >= this.x && x <= (this.x +width)) &&
                    y >= this.y && y <= (this.y +height)) {
                return true;
            }
        } else if (this.state == 1) {
            if ((x <= this.x && x >= (this.x - height)) &&
                    y >= this.y && y <= (this.y + width)) {
                return true;
            }
        } else if (this.state == 2) {
            if ((x <= this.x && x >= (this.x - width)) &&
                    y <= this.y && y >= (this.y - height)) {
                return true;
            }
        } else if (this.state == 3) {
            if ((x >= this.x && x <= (this.x + height)) &&
                    y <= this.y && y >= (this.y - width)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Change the Domino's position by dx and dy amounts.
     * @param dx Amount to translate in this.x direction.
     * @param dy Amount to translate in this.y direction.
     */

    public void translate(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * Rotate Domino -90 degrees around pivot point.
     * @param xp this.x coordinate of pivot point.
     * @param yp this.y coordinate of pivot point.
     */

    public void rotate(int xp, int yp) {
        double angle = Math.toRadians(-90);

        this.x = (int) (this.x + (xp-this.x)*Math.cos(angle) - (yp-this.y)*Math.sin(angle));
        this.y = (int) (this.y + (xp-this.x)*Math.sin(angle) + (yp-this.y)*Math.cos(angle));
    }

    /**
     * Reset the position of the domino
     */

    public void reset() {
        this.x = resetX;
        this.y = resetY;
        state = 0;
    }
}
