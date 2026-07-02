package giocoscudetto.view.impl.result;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.CreateUpdateController;

/**
 * A model for displaying the fixture in a JTable.
 */
@SuppressFBWarnings
public class FixtureModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final List<String> matches;
    private final List<String> scores;
    private final CreateUpdateController updateController;
    private final String[] columns = {"Match", "Score"};

    /**
     * Constructor for the fixture model.
     * 
     * @param updateController to get the fixture information
     */
    public FixtureModel(final CreateUpdateController updateController) {
        this.updateController = updateController;
        this.matches = new ArrayList<>(this.updateController.getFixtureMatchesString());
        this.scores = new ArrayList<>(this.updateController.getFixtureScoresString());
    }

    @Override 
    public final int getRowCount() {
        return matches.size();
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
            case 0 -> matches.get(row);
            case 1 -> scores.get(row);
            default -> null;
        };
    }

}
