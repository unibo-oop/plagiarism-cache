package view;

import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalImages;

/**
 * Class that permise switch of the scenes in stage.
 * */
public final class SceneLoader {

    private SceneLoader() {

    }

    /**
     * @param stage - the current stage that use to switch scene
     * @param path - the path where the scene content
     * @param title - the title of the stage
     * @param width - the width of the stage 
     * @param height - the height of the stage
     * @param cssStylePath - the css applied to the current scene
     */
    public static void switchScene(final Stage stage, final URL path, final String title, 
                                    final double width, final double height, final String cssStylePath)  {
        try {
            final Parent parent = FXMLLoader.load(path);
            final Scene newScene = new Scene(parent, width, height);
            newScene.setRoot(parent);
            //Apply Style
            parent.getStylesheets().add(cssStylePath);

            //Animation
            final FadeTransition fadeIn = new FadeTransition(Duration.millis(ScreenUtilities.ANIMATION_DURATION), newScene.getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
            //Load Stage Property
            stage.getIcons().add(new Image(PersonalImages.GAME_ICON_IMG.getResourceAsStream()));
            stage.setScene(newScene);
            stage.setTitle(title);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
