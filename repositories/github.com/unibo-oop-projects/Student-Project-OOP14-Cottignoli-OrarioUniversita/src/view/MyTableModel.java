package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.interfaces.IDailyTime;

/**
 * Class that extends {@link AbstractTableModel} to manage table model using a {@link List}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MyTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NUM = IDailyTime.HOURS + 1;
	
	private List<Object> list = new ArrayList<>();
	
	@Override
	public int getRowCount() {
		return list.size() / COL_NUM;
	}

	@Override
	public int getColumnCount() {
		return COL_NUM;
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		return list.get(COL_NUM * rowIndex + columnIndex);
	}
	
	@Override
	public boolean isCellEditable(final int row, final int col) {
		return false;
	}
	
	/**
	 * Method to set the table model using a {@link List} passed as parameter.
	 * 
	 * @param l New table model.
	 * @throws IllegalArgumentException if l is null.
	 */
	public void setModel(final List<Object> l) {
		if (l == null) {
			throw new IllegalArgumentException("The TableModel can't be null!");
		}
		list = l;
		fireTableDataChanged();
	}
	
	
}
