package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

import model.interfaces.ILesson;
import view.utility.ColorUtility;

/**
 * 
 * Class used to render the cells of the table.
 *
 */

public class MyRenderer extends DefaultTableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 6439423550121913327L;
    
    private final JTextPane cell = new JTextPane();
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value instanceof ILesson) {
            cell.setText(((ILesson) value).getSubject().getName() + System.lineSeparator() + ((ILesson) value).getProfessor().getName());
            ColorUtility.getColorsByYear().forEach(x -> {
                if (x.getX().equals(((ILesson) value).getSubject().getYear().getYear())) {
                    cell.setBackground(x.getY());
                }
            });
        } else {
            cell.setText(value.toString());
            cell.setBackground(Color.WHITE);
        }
        
        final int h = cell.getPreferredSize().height;
        final int w = cell.getPreferredSize().width;
        
        if (table.getColumnModel().getColumn(column).getWidth() < w) {
            table.getColumnModel().getColumn(column).setPreferredWidth(w + Character.SIZE);
        }
        
        if (table.getRowHeight(row) < h) { 
            table.setRowHeight(row, h);
        }
        if (isSelected) {
            if (cell.getBackground().equals(Color.WHITE)) {
                if (cell.getText().equals("")) {
                    cell.setBackground(Color.GREEN);
                } else {
                    cell.setBackground(Color.BLACK);
                }
            } else {
                cell.setBackground(Color.WHITE);
            }
        }
        return this.cell;
    }

}
