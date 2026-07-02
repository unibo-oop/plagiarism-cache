package view;

import controller.SceneManager;
import controller.SoundManager;
import controller.SoundManagerImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.Utilities;
import view.menu.StartMenu;

/**
 * Starting class.
 *
 */
public final class MainWindow extends Application {

    private final SoundManager soundManager;

    /**
     * Constructor.
     */
    public MainWindow() {
        super();
        this.soundManager = SoundManagerImpl.get();
    }

    @Override
    public void start(final Stage stage) {
        final Stage defaultStage = stage;

        SceneManager.get().setApplicationStage(defaultStage);

        this.soundManager.loadSoundVolume();

        defaultStage.getIcons().add(new Image("images/icon.png", 100, 100, false, false));
        defaultStage.setTitle("Watch Your Way");
        defaultStage.setWidth(Utilities.W);
        defaultStage.setHeight(Utilities.H);
        defaultStage.centerOnScreen();
        defaultStage.setResizable(false);
        SceneManager.get().sceneSetter(StartMenu.get().getScene());
        defaultStage.show();
    }
}
