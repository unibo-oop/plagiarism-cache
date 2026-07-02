

import controller.GameController;
import controller.GameControllerImpl;
import model.Model;
import model.ModelImpl;
import model.entities.Player1;
import model.entities.SpecificEntityType;
import model.entitiesutil.Player;
import util.Pair;
import view.game.GameEvent;

import static org.junit.Assert.*;



import org.junit.Test;

/**
 * Test that checks the correct player movement.
 */

public class playerTest {

	@Test
	public void PlayerTest() {
		
		final GameController controller = new GameControllerImpl();
		final Model game = new ModelImpl(controller);
		final Player player = new Player1(SpecificEntityType.PLAYER_1, 0.0 , 0.0, game);
	    assertNotNull(player);
	    player.setMuX(5.0);
	    player.setMuY(5.0);
	    player.updateEntityPosition(GameEvent.RIGHT , 0);
	    assertTrue(player.getPos().equals(new Pair<>(0.0 + player.getMuX(), 0.0)));
	}
}
