package view;

import java.util.ArrayList; 
import java.util.List;
import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Hour;

/**
 * 
 * Class which manages the model of the table and contains his content.
 *
 */

public class MyTableModel extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 9137709835141518432L;
    private List<List<Object>> baseModel = new ArrayList<>();
    private static int nCOL = Hour.values().length + 1;

    @Override
    public int getColumnCount() {
        if (baseModel.isEmpty()) {
            return nCOL;
        } else {
            return baseModel.get(0).size();
        }
    }

    @Override
    public int getRowCount() {
        return baseModel.size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return baseModel.get(rowIndex).get(columnIndex);
    }
    
    @Override
    public void setValueAt(final Object value, final int row, final int column) {
        this.baseModel.get(row).set(column, value);
        fireTableDataChanged();
    }
    
    /**
     * Method which set the model of the table.
     * @param list The list used as model.
     */
    
    public void setModel(final List<List<Object>> list) {
        if (list == null) {
                Controller.getController().errorMessage("The table model can't be null!");
        }
        baseModel = list;
        fireTableDataChanged();
    }
    
}
