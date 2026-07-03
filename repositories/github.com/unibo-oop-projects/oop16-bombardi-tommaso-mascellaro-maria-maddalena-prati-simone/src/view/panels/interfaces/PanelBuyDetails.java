package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object1;
/**
 * 
 * panel by details interface.
 *
 */
public interface PanelBuyDetails {
    /**
     * 
     * @return panel whit details of buy
     */
    JPanel getPanelBuyDetails();
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
     *          object to buy
     */
    void setObject(Object1 ob);
    /**
     * reset object.
     */
    void resetObject();
    /**
     *  add details to the panel.
     */
    void addDetails();
    /**
     *  remove detail in the panel.
     */
    void removeDetails();
}
