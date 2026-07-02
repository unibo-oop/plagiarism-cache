package model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 *The model for all the table of the project
 *@author francesco 
 **/
public abstract class MyTableModel implements TableModel {
	
	
	protected IModel model;
	private String[] columnNames;
	public int parametro;
	
	public MyTableModel(IModel model) {
	    this.model = model;
        }

    @Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	protected void setColumnNames(String []names) {
		this.columnNames = names;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
	
	
}