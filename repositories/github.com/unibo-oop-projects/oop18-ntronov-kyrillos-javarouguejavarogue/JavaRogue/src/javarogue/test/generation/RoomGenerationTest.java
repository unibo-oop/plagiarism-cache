package javarogue.test.generation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.Random;

import org.junit.Test;

import javarogue.generation.rooms.SimpleRoom;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class RoomGenerationTest {

	// -- Room Generation Tests --
	
	// Minimum room size: 6 x 6
	// Maximum room size: 8 x 8
	
	@Test
	public void testRoomCanBeGenerated() {
		// Test whether a room can be generated in:
		// 6x6, 8x8, 7x6, 9x9 space
		Matrix<Tile> map = new Matrix<>(6, 6);
		map.fill(Tile.VOID);
		SimpleRoom room = new SimpleRoom(new Position(0, 0), map, new Random());
		while(!room.canBeGenerated()) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
		}
		assertTrue(room.canBeGenerated());
		
		map = new Matrix<>(8, 8);
		map.fill(Tile.VOID);
		room = new SimpleRoom(new Position(0, 0), map, new Random());
		while(!room.canBeGenerated()) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
		}
		assertTrue(room.canBeGenerated());
		
		map = new Matrix<>(7, 6);
		map.fill(Tile.VOID);
		room = new SimpleRoom(new Position(0, 0), map, new Random());
		while(!room.canBeGenerated()) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
		}
		assertTrue(room.canBeGenerated());
		
		map = new Matrix<>(9, 9);
		map.fill(Tile.VOID);
		room = new SimpleRoom(new Position(0, 0), map, new Random());
		while(!room.canBeGenerated()) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
		}
		assertTrue(room.canBeGenerated());
	}
	
	@Test
	public void testRoomCanNotBeGenerated() {
		// test in matrix less than minimum size
		// for 100 iterations
		Matrix<Tile> map = new Matrix<>(5, 5);
		map.fill(Tile.VOID);
		SimpleRoom room;
		for(int i = 0; i < 100; i++) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
			assertFalse(room.canBeGenerated());
		}
	}
		
	@Test
	public void testRoomTiles() {
		Matrix<Tile> map = new Matrix<>(6, 6);
		map.fill(Tile.VOID);
		SimpleRoom room = new SimpleRoom(new Position(0, 0), map, new Random());
		while(!room.canBeGenerated()) {
			room = new SimpleRoom(new Position(0, 0), map, new Random());
		}
		room.generate();
		assertFalse(room.getTileCoordinates().isEmpty());
		
		for(Entry<Position, Tile> e : room.getTileCoordinates().entrySet()) {
			map.set(e.getKey().getX(), e.getKey().getY(), e.getValue());
		}
		String expected = "CORNER_NW WALL_N WALL_N WALL_N WALL_N CORNER_NE\n"
				+ 	  	  "WALL_W FLOOR FLOOR FLOOR FLOOR WALL_E\n"
				+ 	  	  "WALL_W FLOOR FLOOR FLOOR FLOOR WALL_E\n"
				+ 	  	  "WALL_W FLOOR FLOOR FLOOR FLOOR WALL_E\n"
				+ 	  	  "WALL_W FLOOR FLOOR FLOOR FLOOR WALL_E\n"
				+	      "CORNER_SW WALL_S WALL_S WALL_S WALL_S CORNER_SE";
		assertEquals(expected, map.toString());
		
		assertEquals(4 * 4, room.getFloor().size());
	}
	
}
