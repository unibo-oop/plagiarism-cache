package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import util.MenuVariablesUtils;
import util.WindowLauncherUtils;

/**
 * Class that starts the first menu.
 */
public class MainMenu extends Application {

    /** {@inheritDoc} **/
    @Override
    public void start(final Stage stage) throws Exception {
        WindowLauncherUtils.mainMenu(
                new Pair<Double, Double>(MenuVariablesUtils.HEIGHT_DEFAULT, MenuVariablesUtils.WIDTH_DEFAULT));
    }

    /**
     * The main method.
     * 
     * @param args not used
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
