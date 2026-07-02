package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for instruction.
 */
public class InstructionController extends ViewController {

    private Controller controller;

    /**
     * Allow to return to the settings stage.
     * 
     * @param event
     *            the mouse event.
     */
    @FXML
    public void exitAction(final MouseEvent event) {
        try {
            ViewScenes.SETTING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                    controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void init(final Controller controller) {
        this.controller = controller;

    }

}
