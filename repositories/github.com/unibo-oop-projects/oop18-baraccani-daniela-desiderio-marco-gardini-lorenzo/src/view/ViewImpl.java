package view;

import java.util.Objects;

import controller.gameloop.GameController;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.audio.AudioManager;
import view.audio.AudioManagerImpl;
import view.audio.AudioTrack;
import view.settings.ScreenSettings;
import view.viewmanager.ViewManager;

/**
 * Implementation of the interface View. It has the role to handle all graphic
 * aspects.
 */
public class ViewImpl implements View {

    private GameController controller;
    private final Stage stage;
    private final AudioManager music;
    private final ViewManager loader;

    /**
     * Create an initial View with default settings.
     * 
     * @param stage of the GUI
     */
    public ViewImpl(final Stage stage) {
        super();
        this.stage = Objects.requireNonNull(stage);
        this.music = new AudioManagerImpl();
        this.loader = new ViewManager(this, stage, this.music);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final GameController controller) {
        this.controller = Objects.requireNonNull(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showUI() {
        this.stage.setResizable(false);
        this.setSizes();
        this.stage.setScene(new Scene(new AnchorPane()));
        this.loader.openMenuScene();
        // this.loader.openGameScene("Prova di nome");
        this.stage.show();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getController() {
        if (Objects.isNull(this.controller)) {
            throw new IllegalStateException();
        }
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setScene(final Parent scene) {
        Objects.requireNonNull(scene);
        this.stage.getScene().setRoot(scene);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void close() {
        Platform.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showError(final String message, final boolean critical) {
        if (critical) {
            this.music.stop();
            this.stage.hide();
            this.close();
            this.controller.stop();
        }

        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Whoops...");
        alert.setHeaderText(null);
        alert.setContentText(message.equals("") ? "Unexpected error, retry." : message);
        this.music.play(AudioTrack.ERROR);
        alert.showAndWait();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewManager getViewManager() {
        return this.loader;
    }

    private void setSizes() {
        final double width = ScreenSettings.getSettings().getDefaultSizeWidth();
        final double height = ScreenSettings.getSettings().getDefaultSizeHeight();

        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }

}