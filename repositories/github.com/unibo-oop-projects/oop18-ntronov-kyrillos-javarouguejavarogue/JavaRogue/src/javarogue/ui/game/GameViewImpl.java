package javarogue.ui.game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javarogue.config.ConfigGraphics;
import javarogue.level.Level;
import javarogue.ui.game.components.Camera;
import javarogue.ui.game.components.GUI;
import javarogue.ui.game.components.MiniMap;
import javarogue.utility.Position;
import javarogue.utility.Direction;

public class GameViewImpl implements GameView {

	private GameController controller;
	private GraphicsContext context;
	private Stage stage;
	
	private Camera camera;
	private MiniMap minimap;
	private GUI gui;
	
	//TMP
	private int curr = 0;

	@Override
	public void setController(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void render() {
		Level level;
		if (!this.controller.getCurrentLevel().isPresent()) {
			throw new IllegalStateException("Cannot render non-existing level!");
		} else {
			level = this.controller.getCurrentLevel().get();
		}
		this.context.clearRect(0, 0, this.stage.getWidth(), this.stage.getHeight());
		this.camera.setLevel(level);
		this.camera.draw();
		this.minimap.setLevel(level);
		this.minimap.draw();
		this.gui.draw();
	}

	@Override
	public void open() {
		// Make window
		this.stage = new Stage();
		this.stage.setScene(this.buildScene());
		this.stage.setFullScreen(ConfigGraphics.fullscreen);
		this.stage.setFullScreenExitHint("");
		this.stage.show();
		// Init components
		this.camera = new Camera(this.context);
		this.camera.setOrigin(new Position(0,0));
		this.minimap = new MiniMap(this.context);
		this.gui = new GUI(this.context);
	}

	@Override
	public void close() {
		this.stage.close();
	}

	private Scene buildScene() {
		Canvas canvas = new Canvas(ConfigGraphics.resolutionWidth, ConfigGraphics.resolutionHeight);
		this.context = canvas.getGraphicsContext2D();
		GridPane pane = new GridPane();
		pane.add(canvas, 0, 0);
		Scene scene = new Scene(pane);
		// TODO tmp implementation
		controller.generateLevels();
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case SPACE:
				controller.changeLevel(curr);
				curr = (curr  + 1) % 4;
				break;
			case UP:
				this.controller.getCurrentLevel().get().moveCharacter(Direction.UP);
				this.render();
				break;
			case DOWN:
				this.controller.getCurrentLevel().get().moveCharacter(Direction.DOWN);
				this.render();
				break;
			case LEFT:
				this.controller.getCurrentLevel().get().moveCharacter(Direction.LEFT);
				this.render();
				break;
			case RIGHT:
				this.controller.getCurrentLevel().get().moveCharacter(Direction.RIGHT);
				this.render();
				break;
			case I:
				this.gui.changeInventoryState();
				break;
			case ESCAPE:
				this.stage.close();
				break;
			default:
			}
			this.render();
		});
		return scene;
	}

}

