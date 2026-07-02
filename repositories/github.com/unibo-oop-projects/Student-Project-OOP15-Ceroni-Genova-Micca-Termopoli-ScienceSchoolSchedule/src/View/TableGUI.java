package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import Controller.SaveController;
import Controller.SaveControllerInterface;
import Model.Days;
import Model.Hours;

/**
 * This class implements DefaultTableModel and overrides the methods. This is
 * the principal table that is added in PanelTable.
 * 
 * @author Galya Genova
 *
 *         Modify by Massimilano Micca
 */
public class TableGUI extends DefaultTableModel {

	private static final long serialVersionUID = -782099090803983602L;

	private SaveControllerInterface cont = new SaveController();
	private static int COLUMNS = Hours.values().length + 1;
	private int row = Days.values().length + (Days.values().length * this.cont.getObjToSave().getListRoom().size());

	private Map<Dimension, Object> dataTable = new HashMap<>();

	public TableGUI() {

	}

	/**
	 * @return the number of rows
	 */
	@Override
	public int getRowCount() {

		return row;
	}

	/**
	 * @return the number of columns that are static
	 */
	@Override
	public int getColumnCount() {

		return COLUMNS;
	}

	/**
	 * override the getValute
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return the specific value
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return this.dataTable.get(new Dimension(rowIndex, columnIndex));
	}

	/**
	 * set the value in specific cell
	 * 
	 * @param data
	 * @param row
	 * @param col
	 */
	public void setValueAt(Object data, int row, int col) {
		Dimension coord = new Dimension(row, col);
		this.dataTable.put(coord, data);
		fireTableCellUpdated(row, col);
	}

	/**
	 * update the table information with method fireTableDataChaged()
	 */
	public void update() {
		fireTableDataChanged();
	}

	/**
	 * set cells to be not editable
	 * 
	 * @return false
	 */
	public boolean isCellEditable(final int row, final int col) {
		return false;
	}

}