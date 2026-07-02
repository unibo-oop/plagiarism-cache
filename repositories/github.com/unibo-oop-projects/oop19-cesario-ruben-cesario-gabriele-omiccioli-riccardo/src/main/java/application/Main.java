package application;

import controller.Binder;
import controller.Controller;
import controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.View;
import view.ViewImpl;
import view.display.ScreenUtilities;
import view.menu.InitialMenuUIController;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static Scene scene;

    // Used to store a reference to the stage
    private static Stage stageRef;

    // Used to store stage position when a mouse button is pressed
    private double stageX, stageY;
    // Used to store mouse position when a mouse button is pressed
    private double mouseX, mouseY;

    /**
     * Override of the start method in Application class.
     */
    @Override
    public void start(final Stage stage) throws Exception {

        // Saving a reference of the stage in the variable stageRef
        setStage(stage);

        // Calculating best initial resolution of the game
        ScreenUtilities.initializeScreenSize();

        // Loading first FXML in the root panel
        final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layouts/initialMenu.fxml"));
        final Pane root = fxmlLoader.load();
        ((InitialMenuUIController) fxmlLoader.getController()).draw();

        scene = new Scene(root, ScreenUtilities.getCurrentWidth(), ScreenUtilities.getCurrentHeight(), Color.BLACK);

        // Setting the style of the stage to UNDECORATED and positioning it in the center of the screen
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();

        // Disabled full screen text and exit full screen with ESC key
        stage.fullScreenExitHintProperty().set("");
        stage.fullScreenExitKeyProperty().set(KeyCombination.NO_MATCH);

        stage.setScene(scene);
        stage.show();

        // When a mouse button is pressed the mouse position and the stage position is saved
        final EventHandler<MouseEvent> mousePressedHandler = event -> {
            mouseX = event.getScreenX();
            mouseY = event.getScreenY();
            stageX = stage.getX();
            stageY = stage.getY();
        };
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedHandler);

        /* The new position of the stage is set starting from the initial position of 
         * the stage and adding the current distance of the mouse from the position of the mouse 
         * at the instant that the mouse button was pressed
         */
        final EventHandler<MouseEvent> mouseDraggedHandler = event -> {
            // If the stage is in full screen it is not possible to move it by mouse drag
            if (!stage.isFullScreen()) {
                stage.setX(stageX + (event.getScreenX() - mouseX));
                stage.setY(stageY + (event.getScreenY() - mouseY));
            }
        };
        stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler);

        final Controller controller = new MainController();
        final View view = new ViewImpl();
        Binder.bind(view, controller);

        // Keyboard keyPressed event handler
        final EventHandler<KeyEvent> keyPressedHandler = event -> {
            view.getKeyEvent().keyPressed(event.getCode());
        };
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedHandler);
        // Keyboard keyReleased event handler
        final EventHandler<KeyEvent> keyReleasedHandler = event -> {
            view.getKeyEvent().keyReleased(event.getCode());
        };
        stage.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedHandler);

    }

    /**
     * @param args unused
     */
    public static void main(final String[] args) {
        Application.launch(Main.class);
    }

    /**
     * Returns the JavaFX scene.
     * @return scene
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Sets the reference to the stage.
     * @param stage The stage to be set
     */
    public static void setStage(final Stage stage) {
        stageRef = stage;
    }

    /**
     * Returns the reference to the stage.
     * @return stageRef
     */
    public static Stage getStage() {
        return stageRef;
    }

}
