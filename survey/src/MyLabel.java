/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import javax.swing.*;
import java.awt.*;

/**
 * Reduces redundancy of constant label creation
 */
public class MyLabel extends JLabel {

    public MyLabel(String text) {
        super(text);
        setSize(400, 50);
        setForeground(Color.BLUE);
    }
}
