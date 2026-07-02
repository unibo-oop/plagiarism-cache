package javarogue.generation.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javarogue.generation.corridor.Corridor;
import javarogue.generation.corridor.CorridorImpl;
import javarogue.generation.rooms.Room;
import javarogue.generation.rooms.SimpleRoom;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class GenerationTiles {

	private Matrix<Tile> tiles;
	
	private List<Room> rooms;
	
	private Random random;
	private int size;
	private int roomNumber;
	
	public GenerationTiles(int size, int roomNumber, Matrix<Tile> tiles, List<Room> rooms, Random random) {
		this.size = size;
		this.tiles = tiles;
		this.rooms = rooms;
		this.random = random;
		this.roomNumber = roomNumber;
	}

	/**
	 * Generates rooms, corridors and walls.
	 * 
	 * @return Generated Matrix of Tiles.
	 */
	public Matrix<Tile> generateTiles() {
		// Allocate resources
		this.tiles = new Matrix<Tile>(this.size, this.size);
		// Prepare for generation
		this.tiles.fill(Tile.VOID);
		// Make some simple rooms
		this.generateRooms(this.roomNumber);
		// Connect all the rooms with corridors
		this.connectRooms();
		// Add missing walls
		this.makeWalls();
		// Finally, return.
		return tiles;
	}

	// -- Rooms --
	
	private void generateRooms(int num) {
		// Fail safe for faulty or impossible generations.
		int failsafe = 0;
		// Random origin to generate a room.
		Position randomOrigin;
		// While generated rooms are less than target room number...
		while(rooms.size() < num) {
			// Select a random point within game boundaries.
			randomOrigin = new Position(1 + random.nextInt(this.size - 2), 1 + random.nextInt(this.size - 2));
			// Init a room
			Room room = new SimpleRoom(randomOrigin, this.tiles, this.random);
			// Try to make a room
			if(room.canBeGenerated()) {
				room.generate();
				this.addTiles(room.getTileCoordinates());
				this.rooms.add(room);
			}
			// Catch impossible generations.
			failsafe++;
			if(failsafe > 1000) {
				// Hard reset.
				this.tiles.fill(Tile.VOID);
				this.rooms.clear();
				failsafe = 0;
			}
		}
	}
		
	private void addTiles(Map<Position, Tile> tiles) {
		// Simply iterate the map and copy tiles on the matrix accordingly
		for(Entry<Position, Tile> entry : tiles.entrySet()) {
			this.tiles.set(entry.getKey().getX(), entry.getKey().getY(), entry.getValue());
		}
	}

	// -- Connect Rooms --
	
	private void connectRooms() {
		// Make a corridor between every room pair
		for(int i = 0; i < this.rooms.size(); i++) {
			Position start = this.getRandomPoint(this.rooms.get(i));
			Position finish = this.getRandomPoint(this.rooms.get((i + 1) % this.rooms.size()));
			Corridor corridor = new CorridorImpl(start, finish, this.tiles);
			corridor.generate();
		}
	}
	
	private Position getRandomPoint(Room room) {
		// Simply select a random floor tile
		return room.getFloor().get(random.nextInt(room.getFloor().size()));
	}

	// -- Clean up --

	private void makeWalls() {
		// Iterate over matrix
		this.tiles.doubleFor((i, j) -> {
			// If a tile is FLOOR or WATER
			if (this.tiles.get(i, j).equals(Tile.FLOOR) || this.tiles.get(i, j).equals(Tile.WATER)) {
				// Check its neighbors
				for (Position pos : this.getNeighbors(new Position(i, j))) {
					// If a neighbor is VOID, set it to BLOCK
					if (this.tiles.get(pos.getX(), pos.getY()).equals(Tile.VOID)) {
						this.tiles.set(pos.getX(), pos.getY(), Tile.BLOCK);
					}
				}
			}
		});
	}

	private List<Position> getNeighbors(Position pos) {
		List<Position> neighbors = new LinkedList<>();
		//Check Moor neighbors:
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				// Handle bounds
				if (pos.getX() - 1 + i >= 0 && pos.getY() - 1 + j >= 0 && pos.getX() - 1 + i < this.tiles.getRows()
						&& pos.getY() - 1 + j < this.tiles.getCols()) {
					neighbors.add(new Position(pos.getX() - 1 + i, pos.getY() - 1 + j));
				}
			}
		}
		return neighbors;
	}

	
}
