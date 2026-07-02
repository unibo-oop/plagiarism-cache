package it.unibo.aknightstale.views;

import com.simtechdata.sceneonefx.SceneOne;
import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public final class JavaFXApp extends Application {
    @Override
    public void start(final Stage stage) {
        SceneOne.disableNotice();
        final var controllerFactory = Controller.of(MainMenuController.class, MainMenuView.class);
        final var mainMenuController = controllerFactory.get();
        mainMenuController.showView();
    }
}
