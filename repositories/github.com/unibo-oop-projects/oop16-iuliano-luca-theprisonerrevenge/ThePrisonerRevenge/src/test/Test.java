package test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;

import static org.hamcrest.CoreMatchers.*;

import controller.Controller;
import controller.ControllerImpl;
import elements.AnimationEnum;
import model.Model;
import model.ModelImpl;
import states.*;
import view.ViewImpl;
import view.View;

/**
 * This is a Test class, it use junit to test the principal features of the
 * application.
 * 
 * @author Luca
 *
 */
public class Test {
	
	private final Model model = new ModelImpl();
	private final View view = new ViewImpl(model);
	private final Controller controller = new ControllerImpl(model, view);

	@org.junit.Test
	public void testModelInitialization() {		
		// Start thread who update the view constantly:
		controller.startControllerThread();			
		
		// Check if the ModelImpl have only the possible State:
		assertEquals(model.getStateMap().size(), StateEnum.values().length);
		
		// Check if the Map is correctly initialized with all the Keys:
		assertThat(model.getStateMap().keySet(), hasItems(StateEnum.MENU_STATE, 
														  StateEnum.GAME_STATE,
														  StateEnum.OPTION_STATE,
														  StateEnum.WIN_STATE,
														  StateEnum.GAMEOVER_STATE));
		
		// Check if the model correctly set the menu state first:
		assertEquals(model.getStateMap().get(StateEnum.MENU_STATE), model.getState());
		
		// Check if the stateMap is unmodifiable, no more State can be added.
		try {
			model.getStateMap().put(StateEnum.GAME_STATE, new StateGame(model));
			fail("Can't be put other State on stateMap.");
		} catch (UnsupportedOperationException e) {}
	}
	
	@org.junit.Test
	public void testControllerStateNavigation() {		
		// KeyEvents to simulate different key press:
		KeyEvent keyEscape = new KeyEvent((Component) view, KeyEvent.KEY_PRESSED, 500, 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED );
		KeyEvent keyEnter = new KeyEvent((Component) view, KeyEvent.KEY_PRESSED, 500, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED );
		KeyEvent keyUp = new KeyEvent((Component) view, KeyEvent.KEY_PRESSED, 500, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
		KeyEvent keyDown = new KeyEvent((Component) view, KeyEvent.KEY_PRESSED, 500, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
		
		// Press ENTER to go in GAME_STATE:
		controller.keyPressed(keyEnter);
		assertEquals(model.getStateMap().get(StateEnum.GAME_STATE), model.getState());
		
		// Simulate a keys pressed to move between StateEnum.
		// Move on MENU_STATE to OPTION STATE and turn back on GAME_STATE.
		controller.keyPressed(keyEscape);
		assertEquals(model.getStateMap().get(StateEnum.MENU_STATE), model.getState());
		controller.keyPressed(keyDown);
		controller.keyPressed(keyEnter);
		assertEquals(model.getStateMap().get(StateEnum.OPTION_STATE), model.getState());
		controller.keyPressed(keyEscape);
		controller.keyPressed(keyUp);
		controller.keyPressed(keyEnter);
		assertEquals(model.getStateMap().get(StateEnum.GAME_STATE), model.getState());
	}
	
	@org.junit.Test
	public void testGameState() {				
		// testing game state features:
		model.setState(StateEnum.GAME_STATE);
		
		// check game state initialization:
		assertTrue(((StateGame) model.getState()).getLevel().getBulletList().isEmpty());
		assertFalse(((StateGame) model.getState()).getLevel().getEnemies().isEmpty());
		assertTrue(((StateGame) model.getState()).getLevel().getPlayer().isAlive());	
		
		// check if player start with full life points and wait animation:		
		assertEquals(((StateGame) model.getState()).getLevel().getPlayer().getLifePoints(), 100);
		assertEquals(((StateGame) model.getState()).getLevel().getPlayer().getAnimation(), AnimationEnum.WAIT);
		
		// Try reset game before the end:
		try {
			((StateGame) model.getState()).getLevel().resetLevel();
			fail("Level can't be reset if there's Enemies alive or the Player is alive.");
		} catch (IllegalStateException e) {}
		
		// check damage on player:
		((StateGame) model.getState()).getLevel().getPlayer().sufferDamage(100);
		assertFalse(((StateGame) model.getState()).getLevel().getPlayer().isAlive());
		
		// Try generate enemies wrong initialization:
		try {
			((StateGame) model.getState()).getLevel().generateEnemies(31);
			fail("Level can't have less then 1 or more then 30 Enemies.");
		} catch (IllegalArgumentException e) {}
		
		// Try generate enemies good initialization:
		((StateGame) model.getState()).getLevel().generateEnemies(30);
		assertEquals(((StateGame) model.getState()).getLevel().getEnemies().size(), 30);
				
		// check if level can be reset after player dead:
		((StateGame) model.getState()).getLevel().resetLevel();
		assertTrue(((StateGame) model.getState()).getLevel().getPlayer().isAlive());
	}
	
}
