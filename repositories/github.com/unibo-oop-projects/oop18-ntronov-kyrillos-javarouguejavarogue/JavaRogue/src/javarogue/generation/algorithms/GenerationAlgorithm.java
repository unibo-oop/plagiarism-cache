package javarogue.generation.algorithms;

import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;

/**
 * 
 * Algorithm for generation of rooms and objects in a level.
 *
 */
public interface GenerationAlgorithm {

	/**
	 *  Generate static background tiles.
	 * @return
	 */
	public Matrix<Tile> generateTiles();
	
	/**
	 *  Generate dynamic objects.
	 * @return
	 */
	public Matrix<Tile> generateObjects();
	
}
