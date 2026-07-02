package View;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Controller.Reservation;

/**
 * This class contains the JPanel with the main table (TableGUI) It instance the
 * TableGUI, JTable, JScrollPane, ControllerGui to provide the structure of the
 * table.
 * 
 * @author Galya Genova
 * 
 *         Modify by Massimiliano Micca
 *
 */
public class PanelTable {

	private JPanel panelTable;
	private JTable mainTable;
	private JScrollPane scroll;
	private DefaultTableModel t = new TableGUI();
	private ControllerGuiInterface controller = new ControllerGui();

	public PanelTable() {

		this.panelTable = new JPanel(new BorderLayout());
		this.panelTable.setBorder(new TitledBorder(" Ricorda di lascare un ora libera per pausa pranzo :)"));
		this.t = controller.getTable();
		this.mainTable = new JTable(t);
		this.scroll = new JScrollPane(mainTable);
		this.mainTable.setDefaultRenderer(Object.class, new MyTableRenderer());
		this.mainTable.setAutoscrolls(true);
		this.mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.resizeColumnWidth(mainTable);
		mainTable.setTableHeader(null);
		mainTable.setFillsViewportHeight(true);
		this.panelTable.add(scroll);
	}

	public JPanel getPanelTable() {
		return this.panelTable;
	}

	private void resizeColumnWidth(JTable table) {

		for (int column = 0; column < table.getColumnCount(); column++) {
			final TableColumn columnModel = table.getColumnModel().getColumn(column);
			int width = columnModel.getMinWidth();

			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				int height = table.getRowHeight(row);
				width = Math.max(comp.getPreferredSize().width + 1, width);
				height = Math.max(comp.getPreferredSize().height + 1, height);
				table.setRowHeight(height);
			}
			columnModel.setPreferredWidth(width);
		}
	}

	/**
	 * allows to update the table any time that some item changes
	 * 
	 * @param p
	 */
	public void update(TableGUI p) {
		this.t = p;
		this.t.fireTableDataChanged();

	}

	/**
	 * allows to set new value in the table
	 * 
	 * @param res
	 * @param row
	 * @param colum
	 */
	public void setNew(Reservation res, int row, int colum) {
		t.setValueAt(res, row, colum);
		this.resizeColumnWidth(mainTable);
	}

	/**
	 * allows to remove the item in specific cell
	 *
	 * @param row
	 * @param column
	 */
	public void remove(int row, int column) {
		t.setValueAt(null, row, column);
		this.resizeColumnWidth(mainTable);
	}

}
