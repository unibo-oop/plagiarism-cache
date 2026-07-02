package controller.movimenti;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dataModel.Account;

public class OperationCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 8786624563355722501L;

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		if (value instanceof Account) {
			setText(((Account) value).getName());
		}
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}
		return this;
	}
}