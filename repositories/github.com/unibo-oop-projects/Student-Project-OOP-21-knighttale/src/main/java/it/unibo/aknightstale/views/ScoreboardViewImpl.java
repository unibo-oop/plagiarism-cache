package it.unibo.aknightstale.views;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import it.unibo.aknightstale.controllers.interfaces.ScoreboardController;
import it.unibo.aknightstale.views.interfaces.ScoreboardView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class ScoreboardViewImpl extends BaseView<ScoreboardController> implements ScoreboardView {
    @FXML
    private MFXTableView<Entry<String, Integer>> scoreboardTableView;

    public ScoreboardViewImpl() {
        super("Scoreboard");
    }

    @FXML
    private void initialize() { //NOPMD - suppressed UnusedPrivateMethod - False positive (used by JavaFX)
        final var playerColumn = new MFXTableColumn<Entry<String, Integer>>("Player");
        final var scoreColumn = new MFXTableColumn<Entry<String, Integer>>("Score");

        playerColumn.setRowCellFactory(entry -> new MFXTableRowCell<>(Entry::getKey));
        scoreColumn.setRowCellFactory(entry -> new MFXTableRowCell<>(Entry::getValue));

        scoreboardTableView.getTableColumns().addAll(List.of(playerColumn, scoreColumn));
        final var filters = scoreboardTableView.getFilters();
        filters.add(new StringFilter<>("Player", Entry::getKey));
        filters.add(new IntegerFilter<>("Score", Entry::getValue));
    }

    /**
     * Event handler for the {@code MFXButton#onAction} event.
     */
    @FXML
    public void onMainMenuButtonClicked() {
        getController().returnToMainMenu();
    }

    /**
     * Updates the scoreboard table view.
     *
     * @param scoreboard the scoreboard set to update the scoreboard table
     */
    @Override
    public void updateScoreboard(final Set<Entry<String, Integer>> scoreboard) {
        final var list = FXCollections.observableList(List.copyOf(scoreboard));
        this.scoreboardTableView.setItems(list);
    }
}
