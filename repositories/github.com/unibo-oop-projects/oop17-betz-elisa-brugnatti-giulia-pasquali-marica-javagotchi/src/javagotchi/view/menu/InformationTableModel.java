package javagotchi.view.menu;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import javagotchi.controller.menu.MenuController;
/**
 * Simple class to manage stats with JTables.
 * @author giulia
 *
 */
public class InformationTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 434851830275214191L;
    private static final int COLUMNS_NUM = 5;
    private final List<InformationTableLine> values;
    private final MenuController controller;
    /**
     * This is the constructor for this class.
     * @param list **this is the list of rows to show in the table**
     * @param controller **this is the MenuController**
     */
    public InformationTableModel(final List<InformationTableLine> list, final MenuController controller) {
        super();
        this.controller = controller;
        this.values = list;
    }

    @Override
    public final int getRowCount() {
        return controller.getListJavagotchi().size();
    }

    @Override
    public final int getColumnCount() {
        return COLUMNS_NUM;
    }

    @Override
    public final Object getValueAt(final int rowIndex, final int columnIndex) {

        final InformationTableLine s = this.values.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return s.getName();
        case 1:
            return s.getAvatar();
        case 2:
            return s.getGender();
        case 3:
            return s.getAge();
        case 4:
            return s.getAlive();
        default:
        }
        return 0;
    }

    @Override
    public final String getColumnName(final int column) {

        switch (column) {
        case 0:
            return "Name";
        case 1:
            return "Avatar";
        case 2:
            return "Gender";
        case 3:
            return "Age";
        case 4: 
            return "Alive";
        default:
        }

        return "";
    }

    @Override
    public final Class<?> getColumnClass(final int column) {

        switch (column) {
        case 0:
            return String.class;
        case 1:
            return String.class;
        case 2:
            return String.class;
        case 3:
            return Integer.class;
        case 4:
            return String.class;
        default:
        }
        return Object.class;
    }
}
