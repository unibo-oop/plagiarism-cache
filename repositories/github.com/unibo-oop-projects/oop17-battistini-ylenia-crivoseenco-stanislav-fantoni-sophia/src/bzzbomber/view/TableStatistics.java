package bzzbomber.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * to rename the name and the values (and his type) of the column and row.
 */
public class TableStatistics extends AbstractTableModel {

    private static final long serialVersionUID = 7501339001517090629L;
    /**
     * the number of the column in the table, it is always the same.
     */
    private static final int NUM_COLUMN = 3;
    private final List<StatisticsLine> lines;

    /**
     * constructor.
     * 
     * @param list
     *            list of line that the table must have.
     */
    public TableStatistics(final List<StatisticsLine> list) {
        super();
        this.lines = new ArrayList<>(list);
    }

    @Override
    public final int getRowCount() {
        return this.lines.size();
    }

    @Override
    public final int getColumnCount() {
        return NUM_COLUMN;
    }

    @Override
    public final Object getValueAt(final int rowIndex, final int columnIndex) {

        final StatisticsLine s = this.lines.get(rowIndex);

        switch (columnIndex) {
        case 0:
            return rowIndex + 1;
        case 1:
            return s.getNickName();
        case 2:
            return s.getScore();
        default:
        }
        return null;
    }

    @Override
    public final String getColumnName(final int column) {

        switch (column) {
        case 0:
            return "Position";
        case 1:
            return "NickName";
        case 2:
            return "Score";
        default:
        }

        return "";
    }

    @Override
    public final Class<?> getColumnClass(final int column) {

        switch (column) {
        case 0:
            return Integer.class;
        case 1:
            return String.class;
        case 2:
            return Integer.class;
        default:
        }
        return Object.class;
    }

}
