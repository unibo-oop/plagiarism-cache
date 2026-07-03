package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.Pair;
/**
 * 
 * panel administrator one interface.
 *
 */
public interface PanelAdminOne {
    /**
     * 
     * @return panelAdminOne
     */
    JPanel getPanelAdminOne();
    /**
     * 
     * @return button for the previous panel 
     */
    JButton getBtnPrev();
    /**
     * 
     * @return button for the all operation table
     */
    JButton getBtnAllOperation();
    /**
     * 
     * @return button for the user operation table
     */
    JButton getBtnOperationUser();
    /**
     * 
     * @return button for the type operation table
     */
    JButton getBtnOperationType();
    /**
     * 
     * @return button for the user and type operation table
     */
    JButton getBtnOperationUserType();
    /**
     * 
     * @return button for the admin logout
     */
    JButton getBtnLogout();
    /**
     * 
     * @param gain
     *          gain and taking 
     */
    void setLabel(Pair<String, String> gain);
    /**
     * 
     * @param name
     *          name of administrator
     */
    void addLabelAmm(String name);
}
