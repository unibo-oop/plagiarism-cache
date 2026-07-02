package javarogue.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import javarogue.utility.Position;

public class PositionTest {

	@Test
	public void testPositionGetters() {
		Position pos = new Position(1, 2);
		assertEquals(1, pos.getX());
		assertEquals(2, pos.getY());
	}
	
	@Test
	public void testPositionEquals() {
		Position pos1 = new Position(2, 3);
		Position pos2 = new Position(2, 3);
		Position pos3 = new Position(-1, 10);
		assertTrue(pos1.equals(pos2));
		assertFalse(pos1.equals(pos3));
	}
	
	@Test
	public void testHashig() {
		HashMap<Position, String> testMap = new HashMap<>();
		Position pos1 = new Position(2, 3);
		Position pos2 = new Position(3, 2);
		testMap.put(pos1, "A");
		testMap.put(pos2, "B");
		assertEquals("A", testMap.get(new Position(2, 3)));
		assertEquals("B", testMap.get(new Position(3, 2)));
		assertNotEquals(pos1.hashCode(), pos2.hashCode());
	}
	
	@Test
	public void testToString() {
		Position test = new Position(1, 2);
		String expected = "(1,2)";
		assertEquals(expected, test.toString());
	}
	
}
