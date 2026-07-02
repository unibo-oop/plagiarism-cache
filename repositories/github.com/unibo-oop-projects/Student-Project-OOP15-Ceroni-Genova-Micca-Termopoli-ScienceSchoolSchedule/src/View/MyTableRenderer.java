package View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import Controller.Reservation;
import Controller.SaveController;
import Controller.SaveControllerInterface;

/**
 * This Class implements TableCellRenderer that provides to customize every
 * single cell of the main table and the other tables. Extends JTextArea.
 * 
 * 
 * @author Galya Genova
 * 
 *         Modify by Massimiliano Micca
 *
 */
public class MyTableRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	protected ControllerGuiInterface contr;
	protected SaveControllerInterface cont = new SaveController();

	public MyTableRenderer() {
		this.setOpaque(true);
		contr = new ControllerGui();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Reservation res = null;
		if (value instanceof Reservation) {
			res = (Reservation) value;

			this.setText(res.getCourse().getName() + "\n" + res.getPerson().getName() + " "
					+ res.getPerson().getSurname());
		} else {
			this.setText((String) value);

		}

		if (row == 0 || column == 0 || ((row % (contr.getCont().getObjToSave().getListRoom().size() + 1) == 0))) {
			setBackground(new Color(171, 205, 239));

		} else if (value != null) {
			this.setBackground(res.getCourse().getType().getColor());
			table.getValueAt(row, 0).toString();
			table.getValueAt(0, column).toString();
			String str = res.toString();
			this.setToolTipText(str);
		} else {
			Color c = Color.white;
			this.setBackground(c);
		}

		return this;
	}

}
