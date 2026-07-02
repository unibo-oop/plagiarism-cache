package view.scenecontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import view.utilities.Scenes;

/**
 * 
 * Rastaldi Martina.
 *
 */
public class RankingController extends SceneControllerImpl {

    private static final Integer LETTERS = 29; 

    @FXML
    private TextArea area;

    @FXML
    private void initialize() {
        final String prova = this.getController().getRanking().substring(LETTERS);
        area.appendText(prova);
    }

    @FXML
    private void returnStartMenu() throws IOException {
        this.getSceneLoader().load(Scenes.STARTMENU);
    }
}
