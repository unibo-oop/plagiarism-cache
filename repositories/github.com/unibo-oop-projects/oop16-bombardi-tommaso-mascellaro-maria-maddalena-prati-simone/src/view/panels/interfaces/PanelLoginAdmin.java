package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * panel login administrator interface.
 *
 */
public interface PanelLoginAdmin {
    /**
     * 
     * @return panel login administrator
     */
    JPanel getLoginAdminPanel();
    /**
     * 
     * @return user name
     */
    JTextField getUsername();
    /**
     * 
     * @return password
     */
    JTextField getPassword();
    /**
     * 
     * @return button login
     */
    JButton getBtnLogin();
    /**
     * 
     * @return button for the previous panel
     */
    JButton getBtnPrev();
}
