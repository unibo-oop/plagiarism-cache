package it.unibo.arkanoid.view;

import java.io.IOException;

import it.unibo.arkanoid.view.controllers.EndGameController;
import it.unibo.arkanoid.view.controllers.SubViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * 
 * SubviewFactory implementation.
 *
 */
public final class SubviewFactoryImpl implements SubViewFactory {

    private static final String PATH = "/Views/";
    private static final String PATH_END = ".fxml";

    private SubViewController getParent(final String viewName) {
        final FXMLLoader loader = new FXMLLoader();
        final String path = PATH + viewName + PATH_END;
        try {
            final Parent parent = loader.load(this.getClass().getResourceAsStream(path));
            final SubViewController controller = loader.<SubViewController>getController();
            controller.setRoot(parent);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SubViewController createHome() {
        return this.getParent("Home");
    }

    @Override
    public SubViewController createGameScreen() {
        return this.getParent("GameScreen");
    }

    @Override
    public SubViewController createScoresView() {
        return this.getParent("Scores");
    }

    @Override
    public SubViewController createLevelWin() {
        return this.getParent("Win");
    }

    @Override
    public SubViewController createGameOver() {
        final EndGameController controller = (EndGameController) this.getParent("GameEnd");
        controller.setMessage("GAME OVER");
        return controller;
    }

    @Override
    public SubViewController createGameFinished() {
        final EndGameController controller = (EndGameController) this.getParent("GameEnd");
        controller.setMessage("YOU WON THE GAME!");
        return controller;
    }

}
