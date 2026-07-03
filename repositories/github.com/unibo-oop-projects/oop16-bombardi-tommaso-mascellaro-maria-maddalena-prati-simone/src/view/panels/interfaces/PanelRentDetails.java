package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object2;
/**
 * 
 * panel rent details interface.
 *
 */
public interface PanelRentDetails {
    /**
     * 
     * @return panel rent details
     */
    JPanel getPanelRentDetails();
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
    /**
     * 
     * @param ob
     *          object
     */
    void setObject(Object2 ob);
    /**
     * reset object.
     */
    void resetObject();
    /**
     * add details to panel.
     */
    void addDetails();
    /**
     * remove details panel.
     */
    void removeDetails();
}
