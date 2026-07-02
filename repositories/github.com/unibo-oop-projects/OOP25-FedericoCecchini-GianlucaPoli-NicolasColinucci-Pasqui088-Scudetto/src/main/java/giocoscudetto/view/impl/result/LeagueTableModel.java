package giocoscudetto.view.impl.result;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.CreateUpdateController;

/**
 * A model for displaying the table information in a JTable.
 */
@SuppressFBWarnings
public class LeagueTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final CreateUpdateController updateController;
    private final List<String> tableNames;
    private final List<Integer> tablePoints;
    private final List<Integer> tableNetDiff;
    private final String[] columns = {"Club", "Points", "Net Difference"};

    /**
     * Constructor for the table model.
     * 
     * @param updateController to get the table informations
     */
    public LeagueTableModel(final CreateUpdateController updateController) {
        this.updateController = updateController;
        this.tableNames = new ArrayList<>(this.updateController.getTableNames());
        this.tablePoints = new ArrayList<>(this.updateController.getTablePoints());
        this.tableNetDiff = new ArrayList<>(this.updateController.getTableNetDiff());
    }

    @Override 
    public final int getRowCount() {
        return tableNames.size();
    }

    @Override 
    public final int getColumnCount() {
        return columns.length;
    }

    @Override 
    public final String getColumnName(final int col) { 
        return columns[col];
    }

    @Override
    public final Object getValueAt(final int row, final int col) {
        return switch (col) {
            case 0 -> this.tableNames.get(row);
            case 1 -> this.tablePoints.get(row);
            case 2 -> this.tableNetDiff.get(row);
            default -> null;
        };
    }

}
