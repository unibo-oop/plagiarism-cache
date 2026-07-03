package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * panel purchase interface.
 *
 */
public interface PanelPurchase {
    /**
     * 
     * @return panel purchase
     */
    JPanel getPanelPurchase();
    /**
     * 
     * @return button for the previous panel
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button login
     */
    JButton getBtnLogin();
    /**
     * 
     * @return button registration
     */
    JButton getBtnReg();
    /**
     * 
     * @param n
     *          name user
     */
    void addLabel(String n);
    /**
     * delete label name.
     */
    void deleteLabel();
    /**
     * 
     * @return button payment
     */
    JButton getBtnPay();
    /**
     * 
     * @return textField user name
     */
    JTextField getTextUser();
    /**
     * 
     * @return textFielf password
     */
    JTextField getTextPass();
    /**
     * 
     * @return textField user name new user
     */
    JTextField getTextUser2();
    /**
     * 
     * @return textField password new user
     */
    JTextField getTextPass2();
    /**
     * 
     * @return textField name user
     */
    JTextField getTextName();
    /**
     * 
     * @return textField surname user
     */
    JTextField getTextSurname();
    /**
     * 
     * @return textField Card
     */
    JTextField getTextCard();
    /**
     * 
     * @return textField Owner
     */
    JTextField getTextOwner();
    /**
     * 
     * @return textField date
     */
    JTextField getTextDate();
    /**
     * 
     * @return textField CVC
     */
    JTextField getTextCvc();
}
