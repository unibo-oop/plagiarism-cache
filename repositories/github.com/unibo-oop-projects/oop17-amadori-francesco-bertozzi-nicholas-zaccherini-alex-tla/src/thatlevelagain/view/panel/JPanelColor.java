package thatlevelagain.view.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * 
 * panel colored.
 *
 */
public class JPanelColor extends JPanel {
    /**
     * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * constructor.
     * @param layout
     *         layout
    */
    public JPanelColor(final FlowLayout layout) {
        super(layout);
        this.setBackground(Color.BLACK);
    }

}
