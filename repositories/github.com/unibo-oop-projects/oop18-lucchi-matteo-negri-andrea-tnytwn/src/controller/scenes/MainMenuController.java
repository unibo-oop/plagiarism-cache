package controller.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/*import javafx.util.Pair;
import model.building.Building;
import model.building.ConstructionType;*/
import model.game.GameImpl;
import view.scenecreator.GamePlan;

/**
 * Controller of the view MainMenu.
 */
public class MainMenuController {

    @FXML // fx:id="play"
    private Button play; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    /**
     * Close the application.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void closeApplication(final ActionEvent event) {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Start the game.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void startGame(final ActionEvent event) {
        GameImpl.getGameImpl();
        new GamePlan(event);
    }
}
