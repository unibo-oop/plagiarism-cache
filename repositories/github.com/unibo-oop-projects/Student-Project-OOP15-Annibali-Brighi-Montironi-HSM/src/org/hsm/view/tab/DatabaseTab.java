package org.hsm.view.tab;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.PlantCreateDialog;
import org.hsm.view.enumeration.PlantModelCharacteristics;
import org.hsm.view.utility.MyGUIFactory;

/**
 * This tab contains all the information about the database of plants.
 *
 */
public class DatabaseTab implements Table<String> {

    private final JTable table;
    private final JPanel panel;

    /**
     * Create the tab for the plant database.
     * 
     * @param frame
     *            the main frame of the app
     */
    public DatabaseTab(final JFrame frame) {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        // insert columns in table
        this.table = new MyGUIFactory().createTable(PlantModelCharacteristics.getNameList().toArray());
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());
        this.table.setRowSorter(sorter);
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        // buttons
        final JButton createPlant = new JButton("Insert new type of plant");
        createPlant.addActionListener(e -> new PlantCreateDialog(frame).start());
        final JButton removePlant = new JButton("Remove selected plant");
        removePlant.addActionListener(e -> ControllerImpl.getController().deleteDbPlant());
        southPanel.add(createPlant);
        southPanel.add(removePlant);
        southPanel.setSize(southPanel.getPreferredSize());
        final JScrollPane scrollPane = new JScrollPane(this.table);
        this.panel.add(scrollPane);
        this.panel.add(southPanel);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

    @Override
    public String getSelectedRowIdentifier() throws IllegalStateException {
        if (this.table.getSelectedRow() == -1) {
            throw new IllegalStateException();
        }
        final int selectedRowIndex = this.table.getSelectedRow();
        final int modelRow = this.table.convertRowIndexToModel(selectedRowIndex);
        final String botanicalName = (String) this.table.getModel().getValueAt(modelRow,
                PlantModelCharacteristics.BOTANICAL_NAME.ordinal());
        return botanicalName;
    }

    @Override
    public void insertRow(final Object... row) {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(row);
    }

    @Override
    public void removeSelectedRow() {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        final int row = this.table.getSelectedRow();
        final int modelRow = this.table.convertRowIndexToModel(row);
        model.removeRow(modelRow);
    }

    @Override
    public void clean() {
        final DefaultTableModel dm = (DefaultTableModel) table.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
    }

}
