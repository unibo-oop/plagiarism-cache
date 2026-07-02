package view;

import java.io.IOException;

import controller.Controller;
import model.utility.Pair;
import view.controller.GameWorldController;

/**
 * View Interface.
 */
public interface View {

    /**
     * Getter of the dimension of the {@link View}.
     * 
     * @return a {@link Pair} containing the dimension.
     */
    Pair<Double, Double> getBounds();

    /**
     * Getter of the {@link GameWorldController}.
     * 
     * @return the game world controller.
     * @throws IOException
     *          throw a new {@link IOException}.
     * 
     */
    GameWorldController getGameWorldController() throws IOException;

    /**
     * Launcher of the view.
     * 
     * @param controller
     *            the game {@link Controller}.
     */
    void launchView(Controller controller);

}
