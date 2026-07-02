package tmw.controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tmw.controller.MainController;
import tmw.controller.StageController;
import tmw.model.audio.AudioTracks;
import tmw.view.menu.CreditView;
import tmw.view.menu.GenericMenuView;

/**
 * Class that handle credits display.
 *
 */
public class CreditControllerImpl implements CreditController {

    private final StageController stageController;
    private final MainController mainController;
    private final GenericMenuView creditView;
    @FXML
    private Button backButton;

    /**
     * Public constructor.
     * 
     * @param stageController stage controller
     * @param controller main controller 
     */
    public CreditControllerImpl(final StageController stageController, final MainController controller) {
        this.stageController = stageController;
        this.mainController = controller;
        creditView = new CreditView();
        creditView.getLoader().setController(this);
        creditView.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(creditView.getScene());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBack() {
        new MenuControllerImpl(stageController, mainController).start();
        mainController.getAudioController().setBackgroudMusic(AudioTracks.MAINMENU_TRACK);
        mainController.getAudioController().setVolume(mainController.getOptionsSettings().getVolume());
    }

}
