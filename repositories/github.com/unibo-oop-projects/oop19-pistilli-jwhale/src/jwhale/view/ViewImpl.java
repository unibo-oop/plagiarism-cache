package jwhale.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jwhale.controller.Controller;
import jwhale.view.controller.SceneController;

public class ViewImpl implements View {

    private final Double minWidth = Screen.getPrimary().getVisualBounds().getWidth() / 3;
    private final Double minHeight = Screen.getPrimary().getVisualBounds().getHeight() / 2;
    private static final String TITLE = "JWhale";
    private final Stage stage;
    private final Controller controller;

    public ViewImpl(final Stage stage, final Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    @Override
    public final void launch() {
        loadScene(AppScene.MAIN_SCENE);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.png")));
        stage.show();
    }

    @Override
    public final void loadScene(final AppScene scene) {
        try {
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(scene.getScenePath()));
            final Parent root = loader.load();
            final SceneController sc = loader.getController();
            final Scene template = this.getStage().getScene();
            stage.setScene(getDimensionedScene(root, template));
            sc.setView(this);
            sc.setController(this.controller);
        } catch (IOException e) {
            DialogUtils.fileErrorDialog(e, stage);
        }
    }
    @Override
    public final Stage getStage() {
        return stage;
    }

    private Scene getDimensionedScene(final Parent root, final Scene scene) {
        return scene == null ? new Scene(root, stage.getMinWidth(), stage.getMinHeight())
                : new Scene(root, scene.getWidth(), scene.getHeight());
    }

}
