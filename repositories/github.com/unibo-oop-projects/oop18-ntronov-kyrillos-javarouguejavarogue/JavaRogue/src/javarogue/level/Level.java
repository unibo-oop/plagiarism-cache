package javarogue.level;

import javarogue.tileengine.Tile;
import javarogue.utility.Direction;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public interface Level {

	/**
	 * Run the generation procedures.
	 */
	public void generate();

	/**
	 * Get matrix of tiles.
	 * 
	 * @return
	 */
	public Matrix<Tile> getTileMap();

	/**
	 * Get matrix of objects.
	 * 
	 * @return
	 */
	public Matrix<Tile> getObjectMap();

	public Position getPlayerPos();
	
	public void moveCharacter(Direction dir);
	
}
