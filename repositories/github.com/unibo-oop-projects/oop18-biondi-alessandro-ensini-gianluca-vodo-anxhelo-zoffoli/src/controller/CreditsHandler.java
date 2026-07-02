package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.util.language.LanguageManagerUtils;
import view.util.theme.ThemeUtils;

/**
 * Handler of the view of the credits window.
 */
public final class CreditsHandler {

    private static Stage creditsStage;
    private static VBox creditsLayout;
    private static FXMLLoader fxmlLoader;

    /**
     * Show the credits view.
     */
    public static void showCredits() {
        creditsLayout = new VBox();
        fxmlLoader = new FXMLLoader();
        creditsStage = new Stage();
        creditsStage.getIcons().add(new Image("/icons/mustashi.png"));

        creditsStage.initModality(Modality.APPLICATION_MODAL);
        creditsStage.titleProperty().bind(LanguageManagerUtils.createStringBinding("credits.title"));

        try {
            fxmlLoader.setResources(LanguageManagerUtils.getResourceBundele());
            fxmlLoader.setLocation(MainViewHandler.class.getResource("/scene/Credits.fxml"));
            fxmlLoader.setRoot(null);
            creditsLayout = fxmlLoader.load();
            creditsStage.setScene(new Scene(creditsLayout));
            ThemeUtils.setTheme(creditsStage);
            creditsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the settings view.
     */
    public void closeCredits() {
        creditsStage.close();
    }

}
