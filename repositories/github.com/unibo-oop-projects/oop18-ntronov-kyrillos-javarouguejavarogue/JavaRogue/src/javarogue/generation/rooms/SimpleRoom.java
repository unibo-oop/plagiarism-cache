package javarogue.generation.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javarogue.generation.rooms.Room;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

/**
 * 
 * A simple rectangle room.
 *
 */
public class SimpleRoom implements Room {

	private Matrix<Tile> map;
	private Position origin;
	private int width;
	private int height;
	private Map<Position, Tile> tiles;
	private List<Position> floor;

	/**
	 * Builds a new rectangle room.
	 * @param origin The top-right corner of the room.
	 * @param map The referenced tile map.
	 */
	public SimpleRoom(Position origin, Matrix<Tile> map, Random generator) {
		tiles = new HashMap<>();
		this.map = map;
		this.origin = origin;
		this.width = 6 + generator.nextInt(3);
		this.height = 6 + generator.nextInt(3);
	}

	@Override
	public boolean canBeGenerated() {
		// Set collison flag to false.
		boolean collision = false;
		// Check if the room is in bounds.
		if (this.checkBounds()) {
			int originRow = origin.getX();
			int originCol = origin.getY();
			// Check if any candidate tile is occupied.
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					// If everything is inBounds, check for collision.
					if (!collision) {
						collision = this.checkCollision(new Position(originRow + i, originCol + j));
					}
				}
			}
		} else {
			collision = true;
		}
		// Collsion is false if no collision / out of bounds was detected.
		return !collision;
	}

	@Override
	public void generate() {
		// Fill with floor
		this.makeFloor();
		// Make corners
		this.makeCorners();
		// Make straight walls
		this.makeWalls();
	}

	@Override
	public Map<Position, Tile> getTileCoordinates() {
		return this.tiles;
	}
	
	@Override
	public List<Position> getFloor() {
		this.floor = this.mapFloor();
		return this.floor;
	}

	private boolean checkCollision(Position pos) {
		//Simply check if selected tile is empty on the map
		if(this.map.get(pos.getX(), pos.getY()) != Tile.VOID) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkBounds() {
		// Set flag to true.
		boolean isInBounds = true;
		// Separate origin rows and cols.
		int originRow = origin.getX();
		int originCol = origin.getY();
		// Check if origin is in bounds (should be handled by the caller...).
		if(originRow < 0 || originRow >= this.map.getRows() || originCol < 0 || originCol >= this.map.getCols()) {
			isInBounds = false;
		} else {
			// If origin is in bounds, check if max height and max width are in bounds.
			int maxRow = origin.getX() + this.height - 1;
			int maxCol = origin.getY() + this.width - 1;
			if(maxRow < 0 || maxRow >= this.map.getRows() || maxCol < 0 || maxCol >= this.map.getCols()) {
				isInBounds = false;
			}
		}
		// Finally, return.
		return isInBounds;
	}
	
	private void makeFloor() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				Position pos = new Position(this.origin.getX() + i, this.origin.getY() + j);
				this.tiles.put(pos, Tile.FLOOR);
			}
		}
	}
	
	private void makeCorners() {
		Position northWest = new Position(this.origin.getX(), this.origin.getY());
		this.tiles.put(northWest, Tile.CORNER_NW);
		Position northEast = new Position(this.origin.getX(), this.origin.getY() + this.width - 1);
		this.tiles.put(northEast, Tile.CORNER_NE);
		Position southWest = new Position(this.origin.getX() + this.height - 1, this.origin.getY());
		this.tiles.put(southWest, Tile.CORNER_SW);
		Position southEast = new Position(this.origin.getX() + this.height - 1, this.origin.getY() + this.width - 1);
		this.tiles.put(southEast, Tile.CORNER_SE);
	}
	
	private void makeWalls() {
		for (int i = 1; i < this.width - 1; i++) {
			Position posN = new Position(this.origin.getX(), this.origin.getY() + i);
			Position posS = new Position(this.origin.getX() + this.height - 1, this.origin.getY() + i);
			this.tiles.put(posN, Tile.WALL_N);
			this.tiles.put(posS, Tile.WALL_S);
		}
		for (int i = 1; i < this.height - 1; i++) {
			Position posW = new Position(this.origin.getX() + i, this.origin.getY());
			Position posE = new Position(this.origin.getX() + i, this.origin.getY() + this.width - 1);
			this.tiles.put(posW, Tile.WALL_W);
			this.tiles.put(posE, Tile.WALL_E);
		}
	}
	
	private List<Position> mapFloor() {
		List<Position> floorTiles = new ArrayList<>();
		for (Entry<Position, Tile> e : this.tiles.entrySet()) {
			if(e.getValue() == Tile.FLOOR) {
				floorTiles.add(e.getKey());
			}
		}
		return floorTiles;
	}

}
