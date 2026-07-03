package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.admin.Pair;
/**
 * 
 * panel cart interface.
 *
 */
public interface PanelCart {

    /**
     * 
     * @return panel cart
     */
    JPanel getPanelCart();
    /**
     * 
     * @return button for the previous panel
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button for finish operation
     */
    JButton getBtnFinishOp();
    /**
     * 
     * @return button to empty the cart
     */
    JButton getBtnDeleteCart();
    /**
     * 
     * @return button to delete operation
     */
    JButton getBtnDeleteOperation();
    /**
     * 
     * @return operation number
     */
    JTextField getOp();
    /**
     * 
     * @param num
     *          operation number
     * @param price
     *          description, information, price
     */
    void addTableRow(Integer num,  Pair<String, Pair<String, String>> price);
    /**
     * 
     * @param i
     *          operation
     */
    void deleteOp(Integer i);
    /**
     * empty table.
     */
    void deleteTable();
    /**
     * 
     * @return operation to delete
     */
    String getOperationDelete();
    /**
     * 
     * @param price
     *          label with total price
     */
    void addLabelPrice(String price);

}
