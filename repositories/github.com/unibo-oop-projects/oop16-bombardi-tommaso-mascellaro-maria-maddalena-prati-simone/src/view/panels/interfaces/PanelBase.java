package view.panels.interfaces;

import javax.swing.JButton;

import javax.swing.JPanel;
/**
 * 
 * panel base interface.
 *
 */
public interface PanelBase {

    /**
     * 
     * @return panelBase
     */
    JPanel getPanelBase();
    /**
     * 
     * @return the panelWelcome
     */
    JPanel getPanelWelcome();
    /**
     * 
     * @return button for the panel User
     */
    JButton getBtnUserPanel();
    /**
     * 
     * @return button for panel administrator
     */
    JButton getBtnAdminPanel();
}
