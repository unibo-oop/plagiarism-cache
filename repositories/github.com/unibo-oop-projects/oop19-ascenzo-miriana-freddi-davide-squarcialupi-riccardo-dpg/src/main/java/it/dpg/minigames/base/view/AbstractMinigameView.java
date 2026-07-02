package it.dpg.minigames.base.view;

import it.dpg.maingame.view.View;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Abstract implementation of MinigameView
 * @author Davide Picchiotti
 * @see View
 * */

public abstract class AbstractMinigameView implements View {

    private Stage stage;

    @Override
    public void setView() {
        setViewUsingAppThread();
    }

    @Override
    public void closeView() {
        Platform.runLater(() -> stage.close());
    }

    /**
     * Create the scene to set on the view
     * */
    protected abstract Scene createScene();

    private void setViewUsingAppThread() {
        Scene scene = createScene();
        Platform.runLater(() -> {
            stage = new Stage();
            stage.setOnCloseRequest(Event::consume);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }
}
