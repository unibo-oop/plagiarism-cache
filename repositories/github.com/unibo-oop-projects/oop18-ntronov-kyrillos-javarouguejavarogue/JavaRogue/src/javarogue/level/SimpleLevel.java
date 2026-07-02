package javarogue.level;

import javarogue.generation.algorithms.GenerationAlgorithm;
import javarogue.playablecharacter.PlayerManager;
import javarogue.playablecharacter.PlayerManagerImpl;
import javarogue.generation.algorithms.Generation;
import javarogue.tileengine.Tile;
import javarogue.utility.Direction;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class SimpleLevel implements Level {

	private Matrix<Tile> tileMap;
	private Matrix<Tile> objectMap;
	private GenerationAlgorithm generator;
	
	private PlayerManager pm;

	/**
	 * 
	 * Builds a level with provided size, room quantity and seed.
	 * 
	 * @param generator
	 * @param tileSet
	 */
	public SimpleLevel(int size, int roomNumber, long seed) {
		this.generator = new Generation(size, roomNumber, seed);
	}

	@Override
	public void generate() {
		this.tileMap = this.generator.generateTiles();
		this.objectMap = this.generator.generateObjects();
		this.pm = new PlayerManagerImpl(this);
		Position entry = null;
		for(int i = 0; i < this.objectMap.getRows(); i++) {
			for(int j = 0; j < this.objectMap.getRows(); j++) {
				if(this.objectMap.get(i, j).equals(Tile.STAIRS_UP)) {
					entry = new Position(i, j);
				}
			}
		}
		this.pm.makeCharacter(entry);
	}

	@Override
	public Matrix<Tile> getTileMap() {
		return this.tileMap;
	}

	@Override
	public Matrix<Tile> getObjectMap() {
		return this.objectMap;
	}

	@Override
	public Position getPlayerPos() {
		return this.pm.getPosition();
	}

	@Override
	public void moveCharacter(Direction dir) {
		this.pm.moveCharacter(dir);
	}

}
