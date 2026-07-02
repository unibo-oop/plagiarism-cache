package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MainWindowController;
import view.util.language.LanguageManagerUtils;
import view.util.theme.ThemeUtils;

/**
 * Handler of the view of the application.
 */
public class MainViewHandler extends Application {

    private static final double HEIGHT_RATIO = 1.75;
    private static final double WIDTH_RATIO = 1.75;
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static FXMLLoader loader;
    private static MainWindowController mController;

    /**
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) {
        setPrimaryStage(primaryStage);

        final double width = Screen.getPrimary().getBounds().getWidth();
        final double height = Screen.getPrimary().getBounds().getHeight();
        primaryStage.setMinHeight(height / HEIGHT_RATIO);
        primaryStage.setMinWidth(width / WIDTH_RATIO);

        primaryStage.getIcons().add(new Image("/icons/mustashi.png"));

        MainViewHandler.primaryStage.titleProperty().bind(LanguageManagerUtils.createStringBinding("window.title"));
        primaryStage.setOnCloseRequest(e -> this.closeApplication(e));
        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public static void initRootLayout() {
        loader = new FXMLLoader();
        loader.setLocation(MainViewHandler.class.getResource("/scene/MainWindow.fxml"));
        try {
            rootLayout = loader.load();
            mController = loader.getController();
            primaryStage.setScene(new Scene(rootLayout));
            ThemeUtils.setTheme(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the effect controls in the view.
     * 
     * @param name URL of the effect controls FXML file
     */
    public static void showView(final String name) {
        try {
            final Pane rightOverview = FXMLLoader.load(MainViewHandler.class.getResource(name));
            rootLayout.setRight(rightOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the current primary stage .
     * 
     * @return the current primary stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Close the MainWindow.
     */
    public static void closeApplication() {
        primaryStage.close();
    }

    private void closeApplication(final WindowEvent e) {
        e.consume();
        mController.closeWindow();
    }

    private static void setPrimaryStage(final Stage primaryStage) {
        MainViewHandler.primaryStage = primaryStage;
    }
}
