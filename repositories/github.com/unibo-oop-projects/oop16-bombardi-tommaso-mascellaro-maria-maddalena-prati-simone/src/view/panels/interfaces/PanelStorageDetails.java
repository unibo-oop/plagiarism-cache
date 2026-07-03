package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object2;
/**
 * 
 * panel storage interface.
 *
 */
public interface PanelStorageDetails {
    /**
     * 
     * @return panel storage details
     */
    JPanel getPanelStorageDetails();
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
     * @param ob
     *          object
     */
    void setObject(Object2 ob);
    /**
     * delete object.
     */
    void resetObject();
    /**
     * add details.
     */
    void addDetails();
    /**
     * remove details.
     */
    void removeDetails();

}
