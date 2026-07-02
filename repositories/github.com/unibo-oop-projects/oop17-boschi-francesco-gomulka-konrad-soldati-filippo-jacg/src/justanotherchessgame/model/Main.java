package justanotherchessgame.model;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import justanotherchessgame.controller.ControllerImpl;
import justanotherchessgame.controller.MainMenuController;
import justanotherchessgame.controller.MainMenuControllerImpl;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.view.utils.ScreenController;

/**
 * Class used to start the application.
 */
public class Main extends Application {
    private static final int MINSIZE = 490;
    private static Stage mainStage;
    @Override
    public final void start(final Stage primaryStage) throws IOException {
        //keep track of the stage because will be used by other classes to get the size
        setStage(primaryStage);

        //Stage set up
        primaryStage.setTitle("JUST ANOTHER CHESS GAME");
        primaryStage.setMinHeight(MINSIZE);
        primaryStage.setMinWidth(MINSIZE);
        primaryStage.setResizable(true);
        ImageGenerator.iconGenerator(primaryStage, "Icon.png");
        //create main view controller
        final MainMenuController controller = new MainMenuControllerImpl();
        final Pane pane = controller.createMainMenuView();
        final Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        ScreenController.getIstance().setScene(scene);
        ScreenController.getIstance().addScreen("MainMenu", pane);
        ScreenController.getIstance().activate("MainMenu");

        //Resize event manage
        mainStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (ControllerImpl.getGame() != null) {
                ControllerImpl.getGame().resize();
            }
            controller.resize();
        });
        mainStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (ControllerImpl.getGame() != null) {
                ControllerImpl.getGame().resize();
            }
            controller.resize();
        });
        primaryStage.show();
    }
    /**
     * Function used to set the stage. Can't be done directly because would be a static assignment via non static method.
     * @param stage.
     */
    private static void setStage(final Stage stage) {
        mainStage = stage;
    }

    /**
     * Function used to get the width of the window.
     * @return window width.
     */
    public static double getStageWidth() {
        return mainStage.getWidth();
    }

    /**
     * Function used to get the height of the window.
     * @return window height.
     */
    public static double getStageHeight() {
        return mainStage.getHeight();
    }

    /**
     * Function used to get the stage.
     * @return the stage.
     */
    public static Stage getStage() {
        return mainStage;
    }

    /**
     * Function used to launch the program and start the application.
     * @param args the application.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
