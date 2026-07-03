package view.panels.interfaces;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.admin.Pair;

/**
 * 
 * panel table interface.
 *
 */
public interface PanelTable {
    /**
     * 
     * @param oper
     *          pair for num operation and type operation
     * @param descr
     *          description
     * @param detail
     *          detail operation
     * @param gain
     *          gain operation
     */
    void addTableRow(Pair<Integer, String> oper, String descr, String detail, Pair<String, String> gain);
    /**
     * delete table.
     */
    void deleteTable();
    /**
     * 
     * @return panel table
     */
    JPanel getPanelTable();
    /**
     * 
     * @return button for previous panel
     */
    JButton getBtnPrev();
}
