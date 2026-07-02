package view.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.score.Score;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.viewmanager.ViewManager;

/**
 * SceoreBoard to control the scoreboard scene. It shows the scoreboard list
 * with players informations. It uses items from javafx.
 */
public class ScoreboardController extends AbstractController implements Initializable {
    @FXML
    private TableView<Score> scoreboard;
    @FXML
    private TableColumn<Score, String> name;
    @FXML
    private TableColumn<Score, Integer> points;
    @FXML
    private Button back;

    /**
     * Initialize the ScoreboardController.
     * 
     * @param loader the {@link ViewManager} shared by all the controllers.
     * @throws IOException if the file misses.
     */
    public ScoreboardController(final ViewManager loader) throws IOException {
        super(loader);
    }

    /*
     * It opens the Menu scene in the View.
     */
    @FXML
    private void backToMenu() {
        this.getViewManager().openMenuScene();
    }

    /**
     * Default initializer method required by {@link Initializable} interface.
     */
    @Override
    public final void initialize(final URL arg0, final ResourceBundle arg1) {
        this.name.setCellValueFactory(new PropertyValueFactory<Score, String>("Name"));
        this.points.setCellValueFactory(new PropertyValueFactory<Score, Integer>("Points"));
        this.scoreboard.getItems().setAll(this.getViewManager().getView().getController().getScoreboard());
    }
}
