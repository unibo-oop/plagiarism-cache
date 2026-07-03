package tests;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import lands.Lands;
import lands.LandsImpl;
import lands.Lands.MyJButton;
import model.Actions;
import model.Attack;
import players.CircList;
import players.PlayerImpl;
import org.junit.jupiter.api.Test;

class AttackTest {
	
	private CircList<PlayerImpl> players = new CircList<PlayerImpl>();
	private int init;
	private int esc;
	private static Lands lands;

	AttackTest() {
		lands = LandsImpl.getLandsImpl();

		players.add(new PlayerImpl("Alberto", 1));
		players.add(new PlayerImpl("Marco", 2));

		lands.getTERR().get(0).setOwner(1);
		lands.getTERR().get(0).setArmies(4);

		lands.getTERR().get(1).setOwner(2);
		lands.getTERR().get(1).setArmies(4);

		lands.getTERR().get(2).setOwner(2);

	}

	@Test
	void test() {
		init = lands.getTERR().get(0).getArmies() + lands.getTERR().get(1).getArmies();
		Attack.attack(lands.getTERR().get(0), lands.getTERR().get(1), players);
		esc = lands.getTERR().get(0).getArmies() + lands.getTERR().get(1).getArmies();
		assertTrue(init - 3 == esc);
	}
}
