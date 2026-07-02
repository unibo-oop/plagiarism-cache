package view.controller;

import controller.utility.Score;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.utility.ViewUtils;

/**
 * Controller class for the LeaderboardView file.
 *
 */
public class LeaderboardController extends AbstractControllerFXML {

    @FXML private TableView<Score> table;
    @FXML private TableColumn<Score, String> nicknameColumn;
    @FXML private TableColumn<Score, String> pointsColumn;
    @FXML private TableColumn<Score, String> timeColumn;
    @FXML private TableColumn<Score, String> modeColumn;
    @FXML private BorderPane contentPane;

    /**
     * Event method for the back button.
     */
    @FXML
    public void backButtonClick() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return this.contentPane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() {
        table.getItems().clear();
        table.getItems().addAll(ViewUtils.getScoreBoard());
        nicknameColumn.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getName()));
        pointsColumn.setCellValueFactory(x -> new SimpleStringProperty(Integer.toString(x.getValue().getPoint())));
        timeColumn.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getTime().toString()));
        modeColumn.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getMode().toString()));
    }

}
