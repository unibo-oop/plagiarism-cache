package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.imageloader.ImageLoader;
import ballblast.view.scenecontroller.AbstractSceneController;
import ballblast.view.scenes.GameScenes;
import ballblast.view.scenes.SceneLoader;
import ballblast.view.scenes.SceneWrapper;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A simple implementation of {@link View}.
 */
public class ViewImpl implements View {

    private static final String GAME_TITLE = "BALL BLAST";
    private static final double MIN_WIDTH = 384;
    private static final double MIN_HEIGHT = 200;
    private final Stage stage;
    private Controller controller;
    private AbstractSceneController currentSceneController;
    private boolean viewStarted;
    private boolean gameover;

    /**
     * 
     * @param primaryStage primaryStage
     */
    public ViewImpl(final Stage primaryStage) {
        super();
        this.stage = primaryStage;
        this.viewStarted = false;
    }

    @Override
    public final void launch(final Controller controller) {
        this.controller = controller;
        this.stage.setTitle(GAME_TITLE);
        this.stage.getIcons().add(new Image(ViewImpl.class.getResourceAsStream("/view/icon.png")));
        this.stage.setMinHeight(MIN_HEIGHT);
        this.stage.setMinWidth(MIN_WIDTH);
        this.stage.setMaximized(true);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.gameover = false;
        this.loadScene(GameScenes.MAIN);
        ImageLoader.getLoader().loadAll();
    }

    @Override
    public final void render() {
        Platform.runLater(() -> this.currentSceneController.render());
    }

    @Override
    public final void loadScene(final GameScenes scene) {
        try {
            final SceneWrapper wrapper = SceneLoader
                    .getLoader()
                    .getScene(gameover ? this.currentSceneController.getNextScene() : scene);
            wrapper.getController().init(controller, this);
            this.currentSceneController = wrapper.getController();

            final Parent root = wrapper.getScene().getRoot();
            root.requestFocus();
            root.setOnKeyPressed(wrapper.getController()::onKeyPressed);

            Platform.runLater(() -> this.initStage(wrapper));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initStage(final SceneWrapper wrapper) {
        final double oldWidth = this.stage.getWidth();
        final double oldHeigth = this.stage.getHeight();
        this.stage.setScene(wrapper.getScene());
        this.stage.setWidth(oldWidth);
        this.stage.setHeight(oldHeigth);
        if (!this.viewStarted) {
            this.stage.show();
            this.viewStarted = true;
        }
    }

    @Override
    public final void setGameOver(final boolean gameover) {
        this.currentSceneController.setGameover(gameover);
        this.gameover = gameover;
        if (gameover) {
            this.currentSceneController.nextScene();
        }
        this.gameover = false;
    }

}
