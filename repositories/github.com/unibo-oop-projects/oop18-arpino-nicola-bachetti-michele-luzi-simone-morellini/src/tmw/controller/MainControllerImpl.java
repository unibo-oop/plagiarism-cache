package tmw.controller;

import javafx.stage.Screen;
import tmw.controller.audio.AudioController;
import tmw.controller.audio.AudioControllerImpl;
import tmw.controller.menu.MenuControllerImpl;
import tmw.controller.menu.OptionsSettings;
import tmw.view.MainView;

/**
 * class that handle the elements in the menus.
 *
 */
public class MainControllerImpl implements MainController {

    private final MainView mainView;
    private final AudioController audioController;
    private final StageController stageController;
    private OptionsSettings optionsSettings;

    /**
     * Public constructor.
     * 
     * @param view main view
     */
    public MainControllerImpl(final MainView view) {
        this.mainView = view;
        this.audioController = new AudioControllerImpl();
        this.stageController = new StageControllerImpl(this.mainView.getStage());
        this.optionsSettings = new OptionsSettings(this.audioController.getDefaultVolume(), false, false,
                Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.audioController.setVolume(optionsSettings.getVolume());
        this.audioController.muteVolume(this.optionsSettings.isMute());
        new MenuControllerImpl(this.stageController, this).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        this.mainView.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AudioController getAudioController() {
        return this.audioController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainView getView() {
        return this.mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionsSettings getOptionsSettings() {
        return this.optionsSettings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StageController getStageController() {
        return this.stageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOptionsSettings(final OptionsSettings settings) {
        this.optionsSettings = settings;
    }

}
