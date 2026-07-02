package it.unibo.arkanoid.view.controllers;

import java.util.List;

import it.unibo.arkanoid.controller.Controller;
import it.unibo.arkanoid.controller.ScoreEntry;
import it.unibo.arkanoid.view.SubView;
import it.unibo.arkanoid.view.View;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * The Controller for the score scene.
 *
 */
public final class ScoresController extends SubViewController {

    @FXML
    private TableView<ScoreEntry> scoreTable;
    @FXML
    private TableColumn<ScoreEntry, String> player;
    @FXML
    private TableColumn<ScoreEntry, Integer> score;

    @Override
    public void init(final Controller controller, final View view) { //NOPMD
        super.init(controller, view);
        final List<ScoreEntry> records = controller.getScore().getSortedScores();
        this.player.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getName()));
        this.score.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getScore()));
        this.scoreTable.getItems().setAll(records);
    }

    @FXML
    private void backClicked() { //NOPMD
        this.getView().setSubView(SubView.HOME);
    }

}
