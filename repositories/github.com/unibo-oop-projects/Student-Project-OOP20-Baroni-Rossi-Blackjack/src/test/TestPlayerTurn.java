package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.PlayerDraw;
import controller.PlayerDrawImpl;

public class TestPlayerTurn {
	
	@Test
	public void testTurnPlayer() {
		final PlayerDraw playerDraw = new PlayerDrawImpl();
		
		playerDraw.DrawCard();
		assertEquals(1, playerDraw.getPlayerHand().size());
		playerDraw.DrawCard();
		assertEquals(2,playerDraw.getPlayerHand().size());
		playerDraw.DrawCard();
		assertEquals(3,playerDraw.getPlayerHand().size());
		playerDraw.ResetPlayer();
		assertEquals(0,playerDraw.getPlayerHand().size());
	}
}
