package start;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainMenu;

/**
 * @author Colavita Ilaria - Gnoli Mirco
 *
 */
public class ArkanoidApp extends Application {

    /**
     * @param args 
     */
    public static void main(final String[] args) {
        System.out.println("Main!");
        launch(args);
    }

    @Override
    public final void start(final Stage arg0) {
        new MainMenu(arg0);
    }
}
