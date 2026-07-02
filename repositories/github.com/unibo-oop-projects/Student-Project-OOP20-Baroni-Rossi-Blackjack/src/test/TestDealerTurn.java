/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.DealerDraw;
import controller.DealerDrawImpl;
import controller.Game;
import controller.GameImpl;

/**
 * @author bon98
 *
 */

	
public class TestDealerTurn {
	Game game = new GameImpl();
	DealerDraw dealerDraw = new DealerDrawImpl();
	@Test
	public void turnDealer() {
		dealerDraw.DrawCard();
		assertEquals(dealerDraw.getDealerHand().size(),1);
		dealerDraw.DrawCard();
		assertEquals(dealerDraw.getDealerHand().size(),2);
		dealerDraw.ResetDealer();
		assertEquals(dealerDraw.getDealerHand().size(),0);
		
		
	}

}
