package javarogue.ui.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javarogue.config.ConfigData;
import javarogue.generation.LevelFactory;
import javarogue.generation.LevelFactoryImpl;
import javarogue.level.Level;

public class GameModelImpl implements GameModel {

	private List<Level> levels;
	private Optional<Level> currentLevel;
	
	private LevelFactory levelFactory;
	
	public GameModelImpl() {
		long seed = ConfigData.seed;
		this.levelFactory = new LevelFactoryImpl(seed);
		this.levels = new LinkedList<>();
	}
	
	@Override
	public void changeLevel(int level) {
		this.currentLevel = Optional.of(this.levels.get(level));
	}
	
	@Override
	public void generateLevels() {
		for(int i = 0; i < 4; i++) {
			this.levels.add(i, this.levelFactory.generate(i));
			this.levels.get(i).generate();
		}
	}

	@Override
	public Optional<Level> getLevel() {
		return this.currentLevel;
	}
	
}
