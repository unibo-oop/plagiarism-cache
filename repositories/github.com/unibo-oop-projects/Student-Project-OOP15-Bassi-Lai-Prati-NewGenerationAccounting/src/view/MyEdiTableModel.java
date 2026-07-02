/**
 * 
 */
package view;

import java.util.List;

import dataModel.IEdiTableDataModel;
import dataModel.Operation;

/**
 * classe astratta che estende MyTableModel per la gestione delle JTable
 * modificabili
 * 
 * @author Pentolo
 */
public abstract class MyEdiTableModel<E extends IEdiTableDataModel> extends MyTableModel<E> {
	private static final long serialVersionUID = 423854890085035434L;

	/**
	 * @param headerList
	 *            intestazioni delle colonne
	 * @param list
	 *            lista degli elementi
	 */
	public MyEdiTableModel(final String headerList[], final List<E> list) {
		super(headerList, list);
	}

	@Override
	public abstract Class<?> getColumnClass(final int column);

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		Operation operation = (Operation) objectsList.get(row);
		if (operation != null) {
			operation.setValueAt(value, column);
		}
	}
}
