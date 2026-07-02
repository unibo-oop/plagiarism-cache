package javarogue.ui.game;

import java.util.Optional;

import javarogue.level.Level;

public class GameControllerImpl implements GameController {

	private GameModel model;
	
	@Override
	public void setModel(GameModel model) {
		this.model = model;
	}

	@Override
	public void generateLevels() {
		this.model.generateLevels();
		this.changeLevel(0);
	}
	
	@Override
	public void changeLevel(int level) {
		this.model.changeLevel(level);
	}

	@Override
	public Optional<Level> getCurrentLevel() {
		return this.model.getLevel();
	}

}
