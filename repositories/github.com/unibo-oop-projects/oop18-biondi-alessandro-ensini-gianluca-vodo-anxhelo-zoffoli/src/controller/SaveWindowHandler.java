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
 * Handle the view of SaveWindow.
 */
public class SaveWindowHandler {

    private static boolean response;
    private static Stage saveStage;
    private static VBox saveLayout;
    private static FXMLLoader fxmlLoader;

    /**
     * show the save window, giving the chance to choose to save, don't save or
     * cancel the action.
     */
    public static void showSaveWindow() {
        saveStage = new Stage();
        saveLayout = new VBox();
        fxmlLoader = new FXMLLoader();

        saveStage.getIcons().add(new Image("/icons/mustashi.png"));
        saveStage.initModality(Modality.APPLICATION_MODAL);
        saveStage.titleProperty().bind(LanguageManagerUtils.createStringBinding("window.title"));

        try {
            fxmlLoader.setResources(LanguageManagerUtils.getResourceBundele());
            fxmlLoader.setLocation(MainViewHandler.class.getResource("/scene/SaveWindow.fxml"));
            fxmlLoader.setRoot(null);
            saveLayout = fxmlLoader.load();
            saveStage.setScene(new Scene(saveLayout));
            ThemeUtils.setTheme(saveStage);
            saveStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * close the SaveWindowView.
     */
    public void save() {
        IOHistoryHandler.saveActualHistoryStatus(false);
        setResponse(true);
        getStage().close();
    }

    /**
     * close the SaveWindowView.
     */
    public void dSave() {
        setResponse(true);
        getStage().close();
    }

    /**
     * close the SaveWindowView.
     */
    public void cancel() {
        setResponse(false);
        getStage().close();
    }

    /**
     * Get the response of the SavwWindow, this will be true if you can continue
     * form where you invoked the method false otherwise.
     * 
     * @return response
     */
    public static boolean canContinue() {
        return response;
    }

    private static void setResponse(final boolean response) {
        SaveWindowHandler.response = response;
    }

    private static Stage getStage() {
        return saveStage;
    }
}
