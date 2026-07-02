package ViewBy;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Days;
import Model.Hours;
import View.MyTableRenderer;

/**
 * This frame provides to show the table by specific room
 * 
 * @author Galya Genova
 *
 */
public class ViewByRoom extends AbstractViewBy {

	private static final long serialVersionUID = 1L;

	public ViewByRoom(final Object name) {
		super(name);
		this.columns = Hours.values().length + 1;
		this.rows = Days.values().length + 1;
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

	protected DefaultTableModel fillCells(DefaultTableModel table, Object room) {
		int row = 1;
		for (Days day : Days.values()) {
			table.setValueAt(day.getString(), row, 0);
			row++;
		}

		int column = 1;
		for (Hours hour : Hours.values()) {
			table.setValueAt(hour.getValue(), 0, column);
			column++;
		}

		row = 1;

		for (int r = 0; r < contr.getTable().getRowCount(); r++) {
			if (contr.getTable().getValueAt(r, 0).toString().equals(room)) {
				for (int c = 0; c < contr.getTable().getColumnCount(); c++) {
					if (r != 0 && c != 0) {
						Object obj = contr.getTable().getValueAt(r, c);
						table.setValueAt(obj, row, c);
					}
				}
				row++;
			}
		}
		table.fireTableDataChanged();
		return table;
	}

}
