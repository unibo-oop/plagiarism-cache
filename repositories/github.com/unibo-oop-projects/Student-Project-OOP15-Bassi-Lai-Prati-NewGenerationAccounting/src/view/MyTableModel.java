/**
 * 
 */
package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataModel.IDataTableModel;

/**
 * classe che estende AbstractTableModel e che permette di gestire una tabella
 * popolata tramite lista di oggetti. CONSIGLIATO uso tramite
 * AbstractAnagraficaView.
 * 
 * @author Pentolo
 *
 */
public class MyTableModel<E extends IDataTableModel> extends AbstractTableModel {
	private static final long serialVersionUID = -9056625553908580890L;
	private final String headerList[];
	protected List<E> objectsList;

	/**
	 * @param headerList
	 *            array di intestazioni
	 * @param list
	 *            lista degli elementi
	 */
	public MyTableModel(final String headerList[], final List<E> list) {
		this.objectsList = list;
		this.headerList = headerList;
	}

	@Override
	public int getColumnCount() {
		return this.headerList.length;
	}

	@Override
	public String getColumnName(final int col) {
		return headerList[col];
	}

	/**
	 * ritorna l'oggetto data la riga della tabella
	 * 
	 * @param row
	 *            la riga
	 * @return l'oggetto presente
	 */
	public E getObjectAt(final int row) {
		return objectsList.get(row);
	}

	@Override
	public int getRowCount() {
		return this.objectsList.size();
	}

	@Override
	public Object getValueAt(final int row, final int column) {
		return objectsList.get(row).getValueAt(column);
	}

	/**
	 * setta la lista degli oggetti e aggiorna la JTable
	 * 
	 * @param objectsList
	 *            lista degli oggetti
	 */
	public void setList(final List<E> objectsList) {
		this.objectsList = objectsList;
		fireTableDataChanged();
	}
}