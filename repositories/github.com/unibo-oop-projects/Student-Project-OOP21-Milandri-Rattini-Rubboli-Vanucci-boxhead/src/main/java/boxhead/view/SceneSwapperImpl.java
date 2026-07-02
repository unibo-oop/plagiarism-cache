package boxhead.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import boxhead.file.FXMLContainer;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwapperImpl implements SceneSwapper {

	private Stage mainStage;
	private Map<String, Scene> scenes;
	private Map<String, FXMLContainer> scenesFromFile;
	
	/**
	 * Constructor of the swapper.
	 * @param stage
	 */
	public SceneSwapperImpl(final Stage stage) {
		this.mainStage = stage;
		this.scenes = new HashMap<>();
		this.scenesFromFile = new HashMap<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void buildScene(final String name, final int width, final int height, final Parent root) {
		this.scenes.put(name, new Scene(root, width, height));
		if (this.getCurrentScene() == null) {
			this.swapTo(name);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addScene(final String name, final Scene scene) {
		this.scenes.put(name, scene);
		if (this.getCurrentScene() == null) {
			this.swapTo(name);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean swapTo(final String name) {
		if (this.scenes.containsKey(name)) {
			this.mainStage.setScene(this.scenes.get(name));
			return true;
		} else if (this.scenesFromFile.containsKey(name)) {
			this.mainStage.setScene(this.scenesFromFile.get(name).getScene());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Scene getCurrentScene() {
		return this.mainStage.getScene();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Stage getMainStage() {
		return this.mainStage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void loadFromFile(final String filename) {
		final FXMLContainer container = new FXMLContainer(filename);
		if (container != null) {
			this.scenesFromFile.put(filename, container);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Optional<Initializable> getFXMLController(String filename) {
		if (this.scenesFromFile.containsKey(filename)) {
			return Optional.of(this.scenesFromFile.get(filename).getController());
		}
		return Optional.empty();
	}
}
