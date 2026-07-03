package view.panels.interfaces;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object2;
/**
 * 
 * panel storage interface.
 *
 */
public interface PanelStorage {
    /**
     * 
     * @return panel storage
     */
    JPanel getPanelStorage();
    /**
     * 
     * @return button for previous panel
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button for panel cart
     */
    JButton getBtnCart();
    /**
     * 
     * @return map with buttons of the items to storage
     */
    Map<JButton, Object2> getBtnObj();
}
