package controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Pair;
import models.Scores;
import models.ScoresImpl;

/**
 * ScoreController is a presenter class (we called it a controller to make its role more clear)
 * that shows the user each saved score.
 */
public class ScoreController {
    private final Scores s = new ScoresImpl();

    @FXML
    private Stage boardStage;

    @FXML
    private Pane scorePane;

    @FXML
    private TableView<List<Pair<String, Integer>>> scoreTable;

    @FXML
    private TableColumn<String, String> score;

    @FXML
    private TableColumn<String, String> name;

    /**
     * Calls for initialization of score controller.
     */
    @FXML
    private void initialize() {
        System.out.println(s.getAllScores());
        Platform.runLater(() -> this.initializeView());
    }

    /**
     * This is the actual initialization of score controller.
     */
    private void initializeView() {
        List<String> n = new ArrayList<>();
        List<String> l = new ArrayList<>();
        for (var e : s.getAllScores()) {
            n.add(e.getX());
            l.add(e.getY().toString());
        }
        ObservableList<Pair<String, Integer>> data = FXCollections.<Pair<String, Integer>>observableArrayList();
        ObservableList<String> dataName = FXCollections.<String>observableArrayList(n);
        ObservableList<String> dataScore = FXCollections.<String>observableArrayList(l);
        data.addAll(s.getAllScores());
        dataName.forEach(s -> {
            name.setCellValueFactory(new PropertyValueFactory<String, String>(s));
        });
        dataScore.forEach(s -> {
            score.setCellValueFactory(new PropertyValueFactory<String, String>(s));
        });
    }
}


