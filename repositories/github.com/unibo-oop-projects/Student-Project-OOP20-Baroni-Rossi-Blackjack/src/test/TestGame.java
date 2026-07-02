package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import controller.Game;
import controller.GameImpl;
import model.State;

public class TestGame {

	@Test
	public void testInitialBalance() {
		final Game game = new GameImpl();
		assertFalse(game.checkBalance());
	}
	
	@Test
	public void testBet() {
		Game game = new GameImpl();
		
		assertEquals(0, game.getBet());		
	}
	
	@Test
	public void testStateOfGame() {
		final Game game = new GameImpl();
		
		game.setState(State.bet);
		assertEquals(State.bet, game.getState());
		game.setState(State.nobet);
		assertEquals(State.nobet, game.getState());
		game.setState(State.playerTurn);
		assertEquals(State.playerTurn, game.getState());
		game.setState(State.dealerTurn);
		assertEquals(State.dealerTurn, game.getState());
		game.setState(State.win);
		assertEquals(State.win, game.getState());
		game.setState(State.lose);
		assertEquals(State.lose, game.getState());
		game.setState(State.drow);
		assertEquals(State.drow, game.getState());
		game.setState(State.broke);
		assertEquals(State.broke, game.getState());
	}
	
}
