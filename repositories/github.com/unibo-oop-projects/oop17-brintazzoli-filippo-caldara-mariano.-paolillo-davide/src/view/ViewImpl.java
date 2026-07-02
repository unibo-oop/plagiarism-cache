package view;

import java.io.IOException;
import controller.Controller;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.utility.Pair;
import view.controller.GameWorldController;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Concrete implementation of the {@link View} interface.
 */
public class ViewImpl implements View {

    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth() / 2;
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 2;

    /**
     * Instance a new {@link ViewImpl}.
     * 
     * @param primaryStage
     *            the first stage.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    public ViewImpl(final Stage primaryStage) throws IOException {
        ViewUtils.setPrimaryStage(primaryStage);
    }

    @Override
    public final Pair<Double, Double> getBounds() {
        return new Pair<>(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight());
    }

    @Override
    public final GameWorldController getGameWorldController() throws IOException {
        return (GameWorldController) ViewScenes.GAME_WORLD.getGameStage().getController();
    }

    @Override
    public final void launchView(final Controller controller) {
        try {
            ViewScenes.MENU.setGameStage(WIDTH, HEIGHT, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
