package javarogue.generation.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javarogue.generation.rooms.Room;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class GenerationObjects {
	
	private Matrix<Tile> tiles;
	private Matrix<Tile> objects;

	private List<Room> rooms;

	private Random random;
	private int size;

	public GenerationObjects(int size, Matrix<Tile> tiles, Matrix<Tile> objects, List<Room> rooms, Random random) {
		this.tiles = tiles;
		this.objects = objects;
		this.rooms = rooms;
		this.random = random;
		this.size = size;
	}

	/**
	 * Generates doors, traps, treasure, stairs, monsters and special rooms.
	 * 
	 * @return Generated Matrix of objects.
	 */

	public Matrix<Tile> generateObjects() {
		// Tiles must be generated
		if (this.tiles == null) {
			throw new IllegalStateException("Cannot generate objects in an empty level");
		}
		// Create the objects layer
		this.objects = new Matrix<Tile>(this.size, this.size);
		// Prepare for overlaying
		this.objects.fill(Tile.ALPHA);
		// Make doors
		this.makeDoors();
		// Place Traps
		this.makeTraps();
		// Place loot
		this.makeLoot();
		// Place stairs
		this.makeStairs();
		// Place monsters
		this.makeMonsters();
		// Make special rooms
		this.makeSpecialRooms();
		return this.objects;
	}
	
	private void makeDoors() {
		// Prepare lists
		List<Tile> vertConnections = Arrays.asList(Tile.WALL_E, Tile.WALL_W);
		List<Tile> horzConnections = Arrays.asList(Tile.WALL_N, Tile.WALL_S);
		List<Tile> cornerConnections = Arrays.asList(Tile.CORNER_NE, Tile.CORNER_NW, Tile.CORNER_SE, Tile.CORNER_SW);
		// Scan level matrix for floor tiles adjacent to walls
		this.tiles.doubleFor((i, j) -> {
			if (this.tiles.get(i, j).equals(Tile.FLOOR)) {
				// Check N\S for verts
				if (i - 1 >= 0 && i + 1 < this.tiles.getRows()) {
					if ((vertConnections.contains(this.tiles.get(i - 1, j))
							|| cornerConnections.contains(this.tiles.get(i - 1, j)))
							&& (vertConnections.contains(this.tiles.get(i + 1, j))
									|| cornerConnections.contains(this.tiles.get(i + 1, j)))) {
						// 10% locked
						int roll = this.random.nextInt(100);
						if(roll < 10) {
							this.objects.set(i, j, Tile.DOOR_VERT_LOCKED);
						} else {
							this.objects.set(i, j, Tile.DOOR_VERT);
						}						
					}
				}
				// Check W\E for horz
				if (j - 1 >= 0 && j + 1 < this.tiles.getCols()) {
					if ((horzConnections.contains(this.tiles.get(i, j - 1))
							|| cornerConnections.contains(this.tiles.get(i, j - 1)))
							&& (horzConnections.contains(this.tiles.get(i, j + 1))
									|| cornerConnections.contains(this.tiles.get(i, j + 1)))) {
						// 10% locked
						int roll = this.random.nextInt(100);
						if(roll < 10) {
							this.objects.set(i, j, Tile.DOOR_HORZ_LOCKED);
						} else {
							this.objects.set(i, j, Tile.DOOR_HORZ);
						}
					}
				}
			}
		});
	}

	private void makeTraps() {
		// Calculate trap amount = 2% of rows x cols
		int trapNum = this.tiles.getRows() * this.tiles.getCols() * 2 / 100;
		this.seedObjects(Tile.TRAP, trapNum);
	}
	
	private void makeLoot() {
		// Calculate chest amount = 0.5% of rows x cols
		int chestNum = (int)(this.tiles.getRows() * this.tiles.getCols() * 0.5 / 100);
		// Calculate fountain amount = 0.5% of rows x cols
		int fountainNum = (int)(this.tiles.getRows() * this.tiles.getCols() * 0.25 / 100);
		// Calculate shrine amount = 0.5% of rows x cols
		int shrineNum = (int)(this.tiles.getRows() * this.tiles.getCols() * 0.175 / 100);
		// Place objects
		this.seedObjectsInRooms(Tile.CHEST, chestNum);
		this.seedObjectsInRooms(Tile.FOUNTAIN, fountainNum);
		this.seedObjectsInRooms(Tile.SHRINE, shrineNum);
	}
	
	private void makeStairs() {
		this.seedObjectsInRooms(Tile.STAIRS_DOWN, 1);
		this.seedObjectsInRooms(Tile.STAIRS_UP, 1);
	}
	
	private void makeMonsters() {
		// Randomize monster quantity.
		// Initial Monster quantity = 2.4% of row x col
		int monNum = (int)(this.tiles.getRows() * this.tiles.getCols() * 2 / 100);
		// Vary it by 25 %
		int var = -25 + this.random.nextInt(51);
		monNum += monNum * var / 100;
		// Divide into types:
		// Slime	35%
		// Rat		30%
		// Thief	15%
		// Goblin	15%
		// Golem	5%
		int slimeNum = (int)(monNum * 35 / 100);
		int ratNum = (int)(monNum * 30 / 100);
		int thiefNum = (int)(monNum * 15 / 100);
		int goblinNum = (int)(monNum * 15 / 100);
		int golemNum = (int)(monNum * 5 / 100);
		// Seed
		this.seedObjectsInRooms(Tile.SLIME, slimeNum);
		this.seedObjectsInRooms(Tile.RAT, ratNum);
		this.seedObjectsInRooms(Tile.THIEF, thiefNum);
		this.seedObjectsInRooms(Tile.GOBLIN, goblinNum);
		this.seedObjectsInRooms(Tile.GOLEM, golemNum);
	}

	private void seedObjects(Tile object, int num) {
		while (num > 0) {
			int row = this.random.nextInt(this.tiles.getRows());
			int col = this.random.nextInt(this.tiles.getCols());
			if (this.tiles.get(row, col).equals(Tile.FLOOR) && this.objects.get(row, col).equals(Tile.ALPHA)) {
				this.objects.set(row, col, object);
				num--;
			}
		}
	}
	
	private void seedObjectsInRooms(Tile object, int num) {
		while (num > 0) {
			Room room = this.rooms.get(this.random.nextInt(this.rooms.size()));
			Position selection = room.getFloor().get(this.random.nextInt(room.getFloor().size()));
			int row = selection.getX();
			int col = selection.getY();
			if (this.tiles.get(row, col).equals(Tile.FLOOR) && this.objects.get(row, col).equals(Tile.ALPHA)) {
				this.objects.set(row, col, object);
				num--;
			}
		}
	}
	
	// -- Special Rooms --
	
	private void makeSpecialRooms() {
		this.makeVaults();
		this.makePools();
	}

	private void makeVaults() {
		// If a room has at least 3 chests it becomes a vault
		for (Room room : this.rooms) {
			int chestCount = 0;
			for (Position pos : room.getFloor()) {
				if (this.objects.get(pos.getX(), pos.getY()).equals(Tile.CHEST)) {
					chestCount++;
				}
			}
			if (chestCount >= 3) {
				for (Entry<Position, Tile> entry : room.getTileCoordinates().entrySet()) {
					if (entry.getValue().equals(Tile.FLOOR)) {
						this.tiles.set(entry.getKey().getX(), entry.getKey().getY(), Tile.FLOOR_VAULT);
						if(this.objects.get(entry.getKey().getX(), entry.getKey().getY()).equals(Tile.ALPHA)) {
							this.objects.set(entry.getKey().getX(), entry.getKey().getY(), Tile.TRAP);
						}
					}
				}
			}
		}
	}	
	
	private void makePools() {
		for (Room room : this.rooms) {
			int objectCount = 0;
			for (Position pos : room.getFloor()) {
				if (!this.objects.get(pos.getX(), pos.getY()).equals(Tile.ALPHA)) {
					if(this.objects.get(pos.getX(), pos.getY()).equals(Tile.STAIRS_UP) ||
							this.objects.get(pos.getX(), pos.getY()).equals(Tile.STAIRS_DOWN)) {
						objectCount += 3;
					} else {
						objectCount++;
					}
				}
			}
			if(objectCount < 3) {
				for(Position pos : room.getFloor()) {
					if(this.objects.get(pos.getX(), pos.getY()).equals(Tile.ALPHA)) {
						this.tiles.set(pos.getX(), pos.getY(), Tile.WATER);
					}				
				}
			}
		}
	}
	
}
