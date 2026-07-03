package view.panels.interfaces;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object2;
/**
 * 
 *panel rent interface.
 *
 */
public interface PanelRent {
    /**
     * 
     * @return panel rent
     */
    JPanel getPanelRent();
    /**
     * 
     * @return button for the previous panel
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button for panel cart
     */
    JButton getBtnCart();
    /**
     * 
     * @return map with buttons of the items to rent
     */
    Map<JButton, Object2> getBtnObj();
}
