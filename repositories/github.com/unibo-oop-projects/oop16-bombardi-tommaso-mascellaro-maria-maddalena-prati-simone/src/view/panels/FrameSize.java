package view.panels;

import java.awt.Toolkit;
/**
 * 
 * enum for size.
 *
 */
public enum FrameSize {
    /**
     * width.
     */
    WIDTH,
    /**
     * height.
     */
    HEIGHT;
    /**
     * 
     * @return value of size screen for panel
     */
    public int getValue() {
        switch(this) {
        case WIDTH: return Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        default:    return (Toolkit.getDefaultToolkit().getScreenSize().height * 2) / 3;
        }
    }

}
