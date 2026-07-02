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
 * This is the frame that shows the general table with all of the informations.
 * 
 * @author Galya Genova
 * 
 *         Modify by Anna Termopoli
 *
 */
public class ViewGeneral extends AbstractViewBy {

	private static final long serialVersionUID = 1L;

	public ViewGeneral(final Object name) {

		super(name);
		this.columns = Hours.values().length + 1;
		this.rows = Days.values().length + (Days.values().length * this.cont.getObjToSave().getListRoom().size());
		this.defaultTable = new DefaultTableModel(rows, columns);
		this.fillCells(defaultTable, name);
		this.panel = new JPanel(new BorderLayout());
		this.table = new JTable(defaultTable);
		this.scroll = new JScrollPane(table);
		this.table.setDefaultRenderer(Object.class, new MyTableRenderer());
		this.table.setTableHeader(null);
		this.table.setFillsViewportHeight(true);
		this.panel.add(scroll, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);
	}

	protected DefaultTableModel fillCells(DefaultTableModel table, Object day) {

		for (int r = 0; r < contr.getTable().getRowCount(); r++) {
			for (int c = 0; c < contr.getTable().getColumnCount(); c++) {

				table.setValueAt(this.contr.getTable().getValueAt(r, c), r, c);
				table.fireTableCellUpdated(r, c);

			}
		}
		table.fireTableDataChanged();
		return table;
	}

}
