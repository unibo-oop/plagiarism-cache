package laterunner.graphics;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Panel is the interface that defines some common methods belonging to all GUI panels.
 */
public interface Panel {

    /**
     * Creates a new button with some features.
     * @param img
     *          button icon
     * @return
     *          new button with some features
     */
    JButton createButton(ImageIcon img);

    /**
     * Creates a new custom font.
     * @param name
     *          name of font file
     * @param size
     *          font size
     * @return
     *          new custom font
     */
    Font createFont(String name, float size);

}

