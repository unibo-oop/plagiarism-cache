package ViewBy;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerWorkers;
import Controller.Reservation;
import Model.Courses;
import Model.Days;
import Model.Hours;
import Model.Room;
import View.MyTableRenderer;

/**
 * This frame provides to show the table by specific course
 * 
 * @author Galya Genova
 * 
 *         Modify by Massimiliano Micca
 *
 */
public class ViewByCourse extends AbstractViewBy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerWorkers cntrWork = new ControllerWorkers();

	public ViewByCourse(final Object name) {

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
	
	protected DefaultTableModel fillCells(DefaultTableModel table, Object course) {

		int i = 0;
		for (Days days : Days.values()) {
			int y = 1;
			for (Hours hours : Hours.values()) {
				table.setValueAt(hours.getValue().toUpperCase(), i, y++);
			}
			table.setValueAt(days.getString(), i++, 0);
			for (Room room : this.cont.getObjToSave().getListRoom()) {
				table.setValueAt(room.getNameRoom(), i++, 0);
			}
		}

		Courses courses = null;
		for (Courses c : this.cntrWork.getCoursesFromFile()) {
			if (c.getName().equals((String) course)) {
				courses = c;
			}
		}

		for (Reservation res : this.cntrWork.getByCourses(courses)) {
			table.setValueAt(res, this.contr.getRow(res), this.contr.getColum(res));
			table.fireTableCellUpdated(this.contr.getRow(res), this.contr.getColum(res));
		}

		table.fireTableDataChanged();
		return table;
	}

}
