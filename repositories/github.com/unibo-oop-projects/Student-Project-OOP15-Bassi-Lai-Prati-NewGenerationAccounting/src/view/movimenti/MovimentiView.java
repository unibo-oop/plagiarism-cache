/**
 * 
 */
package view.movimenti;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import dataModel.Movement;
import view.AnagraficaView;

/**
 * la view della finestra di gestione dei movimenti
 * 
 * @author Pentolo
 *
 */
public class MovimentiView extends AnagraficaView<Movement> {

	private static final long serialVersionUID = -7682380373297678954L;

	/**
	 * @param list
	 *            lista dei valori da inserire nella JTable
	 * @param title
	 *            titolo della finestra
	 */
	public MovimentiView(final LinkedList<Movement> list, final String title) {
		super(list, Movement.getIntestazione(), title);
		getTable().getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 344922533977603652L;

			@Override
			public void setValue(Object value) {
				setText(new SimpleDateFormat("dd/MM/yyyy").format((Date) value));
			}
		});
		getTable().getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
		getTable().getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		getTable().getColumnModel().getColumn(3).setCellRenderer(new MultiLineCellRenderer());
	}
}

class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7815180422642482612L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		table.setRowHeight(row, 115);
		this.setText((String) value);
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}
		return this;
	}
}
