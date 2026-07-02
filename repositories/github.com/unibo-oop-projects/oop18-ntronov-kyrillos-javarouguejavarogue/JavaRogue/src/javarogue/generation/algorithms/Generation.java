package javarogue.generation.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javarogue.generation.rooms.Room;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;

/**
 * Simple generation algorithm, creates connected rooms and fills map with
 * objects.
 * 
 **/
public class Generation implements GenerationAlgorithm {

	// -- Matrixes --
	private Matrix<Tile> tiles;
	private Matrix<Tile> objects;
	// -- Data Structures --
	private List<Room> rooms;
	// -- Parameters --
	private int roomNumber;
	private int size;
	// -- Seeded random number generator --
	private Random random;
	/**
	 * 
	 * Generates a dungeon of provided width and height with provided number of rooms.
	 * 
	 * @param width			Dungeon width.
	 * @param height		Dungeon height.
	 * @param roomNumber	Number of rooms.
	 */
	public Generation(int size, int roomNumber, long seed) {
		this.rooms = new LinkedList<>();
		this.size = size;
		this.roomNumber = roomNumber;
		this.random = new Random();
		this.random.setSeed(seed);
	}

	@Override
	public Matrix<Tile> generateTiles() {
		GenerationTiles gen = new GenerationTiles(this.size, this.roomNumber, this.tiles, this.rooms, this.random);
		this.tiles = gen.generateTiles();
		return this.tiles;
	}

	@Override
	public Matrix<Tile> generateObjects() {
		GenerationObjects gen = new GenerationObjects(this.size, this.tiles, this.objects, this.rooms, this.random);
		this.objects = gen.generateObjects();
		return this.objects;
	}
}