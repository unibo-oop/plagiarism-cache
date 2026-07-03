package breakout.view;

import breakout.view.utils.Utils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class creates the Main window of the application. The Window is 1270x768
 * and is not resizable.
 */

public class MainWindow extends Application {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setWidth(Utils.STAGE_WIDTH);
        primaryStage.setHeight(Utils.STAGE_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("BreakOut");
        primaryStage.getIcons().add(new Image(Utils.getPath("/Images/Icon.jpg")));
        primaryStage.setScene(new MainMenu(primaryStage));
        primaryStage.show();
    }
}
