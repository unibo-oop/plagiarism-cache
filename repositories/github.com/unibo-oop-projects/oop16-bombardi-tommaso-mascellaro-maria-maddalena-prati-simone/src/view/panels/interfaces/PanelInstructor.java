package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * panel instructor interface.
 *
 */
public interface PanelInstructor {
    /**
     * 
     * @return panel instructor
     */
    JPanel getPanelInstructor();
    /**
     * 
     * @return button for the previous panel
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button for the panel cart
     */
    JButton getBtnCart();
}
