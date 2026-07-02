package zombieversity.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zombieversity.file.FXMLContainer;

public class SceneSwapperImpl implements SceneSwapper {

    private Stage main;
    private Map<String, Scene> scenes;
    private Map<String, FXMLContainer> fileScenes;
    /**
     * Constructor, no parameters beacause if stage not given creates new one with empty properties.
     */
    public SceneSwapperImpl() {
        this(new Stage());
    }
    /**
     * Constructor, uses as main the given stage.
     * @param stage
     */
    public SceneSwapperImpl(final Stage stage) {
        this.main = stage;
        this.scenes = new HashMap<>();
        this.fileScenes = new HashMap<>();
    }

    @Override
    public final void buildScene(final String name, final int w, final int h, final Parent root) {
        if (this.scenes == null) {
            this.scenes = new HashMap<>();
        }
        this.scenes.put(name, new Scene(root, w, h));
        if (this.main.getScene() == null) {
            this.swapTo(name);
        }
    }

    @Override
    public final void addScene(final String name, final Scene scene) {
        if (scene != null && name != null) {
            this.scenes.putIfAbsent(name, scene);
            if (this.getCurrent() == null) {
                this.main.setScene(scene);
            }
        }
    }

    @Override
    public final void removeScene(final String name) {
        if (this.scenes.containsKey(name)) {
            this.scenes.get(name).disposePeer();
            this.scenes.remove(name);
        } else if (this.fileScenes.containsKey(name)) {
            this.fileScenes.get(name).getScene().disposePeer();
            this.fileScenes.remove(name);
        }
        if (this.fileScenes.size() == 0 && this.scenes.size() == 0) {
            this.main.setScene(null);
        }
    }

    @Override
    public final boolean swapTo(final String name) {
        Scene swap;
        if (this.scenes.containsKey(name)) {
            swap = this.scenes.get(name);
            this.main.setScene(swap);
            return true;
        } else if (this.fileScenes.containsKey(name)) {
            swap = this.fileScenes.get(name).getScene();
            this.main.setScene(swap);
            return true;
        } else {
            swap = this.main.getScene();
            this.main.setScene(swap);
            return false;
        }
    }

    @Override
    public final Scene getCurrent() {
        return this.main.getScene();
    }

    @Override
    public final Stage getMain() {
        return this.main;
    }

    @Override
    public final boolean loadFromFile(final String filename) {
        if (fileScenes == null) {
            this.fileScenes = new HashMap<>();
        }
        final FXMLContainer container = new FXMLContainer(filename);
        if (container != null) {
            this.fileScenes.put(filename, container);
            return true;
        }
        return false;
    }

    @Override
    public final Optional<Initializable> getFXMLController(final String filename) {
        if (this.fileScenes.containsKey(filename)) {
            return Optional.of(this.fileScenes.get(filename).getController());
        }
        return Optional.empty();
    }

    @Override
    public final void swapToFXML(final String name) {
        this.main.setScene(this.fileScenes.get(name).getScene());
    }

    /**
     * feature not working yet.
     */
    @Override
    public void addSubScene(final String name) {

    }

}
