import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import libs.CircularList;

import model.Logics;
import model.Player;
import model.SimpleTable;
import model.deck.SimpleDeck;

public class TestLogics {

	@Test
	public void testGetTarget() {
		var table = new SimpleTable(new SimpleDeck(), 4);
		CircularList<Player> list = table.getPlayers();
		Logics l = new Logics(table);
		var p = table.getCurrentPlayer();
		var p1 = list.getNextOf(p);
		var p2 = list.getNextOf(p1);
		var p3 = list.getPrevOf(p);

		var set = l.getTargets();
		assertEquals(Set.of(p3, p1), set);

		p1.modifyRetreat(1);
		var set1 = l.getTargets();
		assertEquals(Set.of(p3), set1);

		p.modifySight(2);
		var set2 = l.getTargets();
		assertEquals(Set.of(p3, p1, p2), set2);

	}

}