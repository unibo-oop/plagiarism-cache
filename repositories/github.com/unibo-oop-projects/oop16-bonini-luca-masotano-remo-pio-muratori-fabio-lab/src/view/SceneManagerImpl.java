package view;

import java.util.Stack;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Pair;
import view.util.ViewStaticUtilities;

/**
 * Implementation of SceneManager.
 */
public class SceneManagerImpl implements SceneManager {

    private final Stage stage;
    private final StackPane root;
    private final Stack<Region> regionStack = new Stack<>();

    /**
     * Constructor the SceneManager class.
     * 
     * @param stage
     *            the javafx Stage object
     */
    public SceneManagerImpl(final Stage stage) {
        this.stage = stage;
        root = new StackPane();
        stage.setResizable(false);
        stage.setTitle("Like A Bullet");
        this.root.setPrefSize(ViewStaticUtilities.getSelectedResolution().getX(),
                ViewStaticUtilities.getSelectedResolution().getY());
        stage.setScene(new Scene(root));

        Font.loadFont(this.getClass().getClassLoader().getResourceAsStream("fonts/upheavtt.ttf"), 10);
        stage.getScene().getStylesheets().add("stylesheets/style.css");
    }

    @Override
    public void setStackHead(final Region firstLayer) {
        this.regionStack.clear();
        this.root.getChildren().removeAll(this.root.getChildren());
        this.pushLayer(firstLayer);
    }

    @Override
    public void pushLayer(final Region layer) {
        if (!this.regionStack.contains(layer)) {
            this.regionStack.push(layer);
            this.root.getChildren().add(layer);
            this.scaleRootContents();
        }
    }

    @Override
    public void popLayer() {
        if (!this.regionStack.isEmpty()) {
            this.root.getChildren().remove(this.regionStack.lastElement());
            this.regionStack.pop();
        }
    }

    @Override
    public void scaleRootContents() {
        final Pair<Integer, Integer> res = ViewStaticUtilities.getSelectedResolution();
        this.root.setPrefSize(res.getX(), res.getY());

        this.root.getChildren().stream().forEach(e -> {
            e.setScaleX(res.getX() / (double) ViewStaticUtilities.getStandardResolution().getX());
            e.setScaleY(res.getY() / (double) ViewStaticUtilities.getStandardResolution().getY());
        });
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
    }
}
