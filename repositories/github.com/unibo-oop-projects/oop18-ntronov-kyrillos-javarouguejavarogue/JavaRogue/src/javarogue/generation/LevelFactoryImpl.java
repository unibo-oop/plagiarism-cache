package javarogue.generation;

import javarogue.level.BossLevel;
import javarogue.level.Level;
import javarogue.level.SimpleLevel;

public class LevelFactoryImpl implements LevelFactory {

	private long seed;
		
	public LevelFactoryImpl(long seed) {
		this.seed = seed;
	}
	
	@Override
	public Level generate(int depth) {
		Level level = null;
		switch(depth) {
		case 0:
			level = new SimpleLevel(30, 8, this.seed);
			break;
		case 1:
			level = new SimpleLevel(40, 10, this.seed);
			break;
		case 2:
			level = new SimpleLevel(50, 12, this.seed);
			break;
		case 3:
			level = new BossLevel();
			break;
		}
		return level;
	}
	
}
