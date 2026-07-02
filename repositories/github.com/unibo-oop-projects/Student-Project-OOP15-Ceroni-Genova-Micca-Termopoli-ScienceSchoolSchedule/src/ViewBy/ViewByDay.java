package ViewBy;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Hours;
import View.MyTableRenderer;

/**
 * This frame provides to show the table by specific day of the week
 * 
 * @author Galya Genova
 *
 */
public class ViewByDay extends AbstractViewBy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewByDay(final Object name) {

		super(name);
		this.columns = Hours.values().length + 1;
		this.rows = this.cont.getObjToSave().getListRoom().size() + 1;
		this.defaultTable = new DefaultTableModel(rows, columns);
		this.fillCells(defaultTable, name);
		this.panel = new JPanel(new BorderLayout());
		this.table = new JTable(defaultTable);
		this.scroll = new JScrollPane(table);
		this.table.setDefaultRenderer(Object.class, new MyTableRenderer());
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.resizeColumnWidth(table);
		this.table.setTableHeader(null);
		this.table.setFillsViewportHeight(true);
		this.panel.add(scroll, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);

	}

	protected DefaultTableModel fillCells(DefaultTableModel table, Object day) {

		for (int r = 0; r < contr.getTable().getRowCount(); r++) {
			if (contr.getTable().getValueAt(r, 0).toString().equals(day)) {

				for (int c = 0; c < contr.getTable().getColumnCount(); c++) {
					for (int row = r; row < contr.getTable().getRowCount()
							&& row < r + this.cont.getObjToSave().getListRoom().size() + 1; row++) {

						Object obj = contr.getTable().getValueAt(row, c);
						table.setValueAt(obj, row - r, c);
					}
				}
				table.fireTableDataChanged();
				return table;
			}
		}
		return null;
	}

}
