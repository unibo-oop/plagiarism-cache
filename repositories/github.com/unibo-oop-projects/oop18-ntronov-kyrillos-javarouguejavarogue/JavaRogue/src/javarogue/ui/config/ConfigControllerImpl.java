package javarogue.ui.config;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javarogue.config.ConfigData;
import javarogue.config.ConfigGraphics;
import javarogue.ui.game.GameController;
import javarogue.ui.game.GameControllerImpl;
import javarogue.ui.game.GameModel;
import javarogue.ui.game.GameModelImpl;
import javarogue.ui.game.GameView;
import javarogue.ui.game.GameViewImpl;
import javarogue.utility.Resolution;

public class ConfigControllerImpl implements ConfigController {

	private ConfigModel model;

	@Override
	public void setModel(ConfigModel model) {
		this.model = model;
	}

	@Override
	public List<Resolution> getResolutionList() {
		return Arrays.asList(Resolution.values());
	}

	@Override
	public void saveResolution(Resolution resolution) {
		this.model.setResolution(resolution);
	}
	
	@Override
	public void saveFullscreen(boolean isFullscreen) {
		this.model.setFullscreen(isFullscreen);
	}
	
	@Override
	public List<String> getTileSets() {
		return Arrays.asList("Default", "Alt", "Custom_16", "Custom_32");
	}

	@Override
	public void saveTileSet(String tileset) {
		this.model.setTileset(tileset);
	}
	
	@Override
	public Long getRandomSeed() {
		long seed = new Random().nextLong();
		this.saveSeed(seed);
		return seed;
	}
	
	@Override
	public void saveSeed(long seed) {
		this.model.setSeed(seed);
	}

	@Override
	public boolean launchGame() {
		if (this.checkValidConfig()) {
			// Build config
			this.buildConfig();
			// launch game window
			// MVC init
			GameModel gameModel = new GameModelImpl();
			GameController gameController = new GameControllerImpl();
			GameView gameView = new GameViewImpl();
			gameController.setModel(gameModel);
			gameView.setController(gameController);
			// Show window
			gameView.open();
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkValidConfig() {
		boolean valid = true;
		if (!this.model.getResolution().isPresent()) {
			valid = false;
		}
		if(!this.model.getTileset().isPresent()) {
			valid = false;
		}
		return valid;
	}
	
	private void buildConfig() {
		new ConfigData(this.model.getSeed());
		new ConfigGraphics(
				this.model.getResolution().get(),
				this.model.getFullscreen(),
				16,
				1.75,
				5,
				this.model.getTileset().get());
	}
}
