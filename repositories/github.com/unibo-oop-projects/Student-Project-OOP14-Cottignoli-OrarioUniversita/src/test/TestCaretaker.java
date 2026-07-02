package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

import controller.Caretaker;
import controller.interfaces.ICaretaker;

/**
 * Class for testing the correct working of the class {@link Caretaker}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class TestCaretaker {

	/**
	 * Necessary test to control that the class Caretaker follows the correct working 
	 * specified in the methods of the interface {@link ICaretaker}.
	 */
	@Test
	public void test() {
		final ICaretaker<Integer> ct = new Caretaker<>();
		assertFalse(ct.prevExist());
		assertFalse(ct.succExist());
		try {
			ct.getPrev();
			fail("there no prev element!");
		} catch (NoSuchElementException e) {
			System.out.println("Success!");
		}
		
		try {
			ct.getSucc();
			fail("there no succ element!");
		} catch (NoSuchElementException e) {
			System.out.println("Success!");
		}
		ct.add(1);
		assertFalse(ct.prevExist());
		assertFalse(ct.succExist());
		ct.add(2);
		assertTrue(ct.prevExist());
		assertFalse(ct.succExist());
		assertEquals(ct.getPrev(), Integer.valueOf(1));
		assertTrue(ct.succExist());
		
		assertEquals(ct.getSucc(), Integer.valueOf(2));
		ct.add(3);
		System.out.println(ct.toString());
		assertEquals(ct.getPrev(), Integer.valueOf(2));
		assertEquals(ct.getPrev(), Integer.valueOf(1));
		System.out.println(ct.toString());
		ct.add(4);
		System.out.println(ct.toString());
		
		final ICaretaker<Integer> c = new Caretaker<>();
		assertSame(c.getCurrentPos(), -1);
		c.add(1);
		assertSame(c.getCurrentPos(), 0);
		System.out.println(c.toString());
		c.add(2);
		assertSame(c.getCurrentPos(), 1);
		System.out.println(c.toString());
		c.add(3);
		assertSame(c.getCurrentPos(), 2);
		System.out.println(c.toString());
		c.add(4);
		assertSame(c.getCurrentPos(), 3);
		System.out.println(c.toString());
		c.add(5);
		assertSame(c.getCurrentPos(), 4);
		System.out.println(c.toString());
		c.add(6);
		assertSame(c.getCurrentPos(), 5);
		System.out.println(c.toString());
		c.add(7);
		assertSame(c.getCurrentPos(), 6);
		System.out.println(c.toString());
		c.add(8);
		assertSame(c.getCurrentPos(), 7);
		System.out.println(c.toString());
		c.add(9);
		assertSame(c.getCurrentPos(), 8);
		System.out.println(c.toString());
		c.add(10);
		assertSame(c.getCurrentPos(), 9);
		System.out.println(c.toString());
		c.add(11);
		assertSame(c.getCurrentPos(), 9);
		System.out.println(c.toString());
		c.add(12);
		assertSame(c.getCurrentPos(), 9);
		System.out.println(c.toString());
	}

}
