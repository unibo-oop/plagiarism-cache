package tmw.controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tmw.controller.MainController;
import tmw.controller.StageController;
import tmw.controller.level.SelectLevelController;
import tmw.controller.world.WorldController;
import tmw.model.audio.AudioTracks;
import tmw.view.menu.EndLevelView;
import tmw.view.menu.GenericMenuView;
import utils.Rooms;

/**
 * Class that manage end level.
 *
 */
public class EndLevelControllerImpl implements EndLevelController {

    private final StageController stageController;
    private final WorldController worldController;
    private final MainController mainController;
    private final SelectLevelController selectLevel;
    private final GenericMenuView endlevelView;
    @FXML
    private Button nextButton;
    @FXML
    private Button menuButton;
    @FXML
    private javafx.scene.control.Label pointsLabel;

    /**
     * Public constructor.
     * 
     * @param stageController the stage controller {@link StageController}
     * @param worldController the world controller {@link WorldController}
     * @param mainController  the main controller {@link MainController}
     * @param selectLevel     the select level controller
     *                        {@link SelectLevelController}
     */
    public EndLevelControllerImpl(final StageController stageController, final WorldController worldController,
            final MainController mainController, final SelectLevelController selectLevel) {
        this.stageController = stageController;
        this.worldController = worldController;
        this.mainController = mainController;
        this.selectLevel = selectLevel;
        this.endlevelView = new EndLevelView();
        this.endlevelView.getLoader().setController(this);
        this.endlevelView.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(endlevelView.getScene());
        finalScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finalScore() {
        pointsLabel.setText(String.valueOf(worldController.getActualScore()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMenu() {
        new MenuControllerImpl(stageController, mainController).start();
        mainController.getAudioController().setBackgroudMusic(AudioTracks.MAINMENU_TRACK);
        mainController.getAudioController().setVolume(mainController.getOptionsSettings().getVolume());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        if (worldController.getRoom().getRoomType().equals(Rooms.BOSS_ROOM)) {
            new CreditControllerImpl(stageController, mainController).start();
        } else {
            this.selectLevel.toBossLevel();
        }
    }

}
