package Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Controller;
import game.ID;
import game.Player;

public class ControllerTest {

	@Test
	public final void testGetInstance() {
		assertNotNull(Controller.getInstance());
	}

	@Test
	public final void testAddObject() {
		Controller.getInstance().clear();
		Controller.getInstance().addObject(new Player(400, 400, ID.Player));
		Controller.getInstance().addObject(new Player(500, 400, ID.Player));
		Controller.getInstance().addObject(new Player(400, 500, ID.Player));
		assertTrue(Controller.getInstance().getAll().stream().count() == 3);
	}

	@Test
	public final void testRemoveObject() {
		Controller.getInstance().clear();
		Player o = new Player(400, 500, ID.Player);
		Controller.getInstance().addObject(o);
		Controller.getInstance().removeObject(o);
		assertTrue(Controller.getInstance().getAll().isEmpty());
	}

	@Test
	public final void testFailState() {
		Controller.getInstance().clear();
		assertTrue(Controller.getInstance().getAll().isEmpty());
	}

	@Test
	public final void testSuccessState() {
		for(int i = (int)Controller.getInstance().getAll().stream().count(); i > 0; i--){
			Controller.getInstance().SuccessState();
		}
		assertTrue(Controller.getInstance().getAll().isEmpty());
	}

}
