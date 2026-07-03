package view.panels.interfaces;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.products.Object1;
/**
 * 
 * panel buy interface.
 *
 */
public interface PanelBuy {
    /**
     * 
     * @return panelBuy
     */
    JPanel getPanelBuy();
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
     * @return map with buttons of the items to buy
     */
    Map<JButton, Object1> getBtnObj();
}
