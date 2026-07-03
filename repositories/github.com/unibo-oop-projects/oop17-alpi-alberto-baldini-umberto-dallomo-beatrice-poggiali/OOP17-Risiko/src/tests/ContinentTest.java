package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lands.Lands;
import lands.LandsImpl;
import model.Continent;
import players.PlayerImpl;

class ContinentTest {
	
	private PlayerImpl p;
	private static Lands lands;
	
	ContinentTest() {
		lands = LandsImpl.getLandsImpl();
		p = new PlayerImpl("Alberto", 1);
		model.RandomicAssignmentCountry.RAC(lands.getTERR(), 6);

		for (int i = 0; i < 13; i++) {
			lands.getTERR().get(i).setOwner(1);
		}

		for (int i = 38; i < 42; i++) {
			lands.getTERR().get(i).setOwner(1);
		}
	}

	@Test
	void test() {
		assertTrue(Continent.totalAmericaN(lands.getTERR(), p));
		assertTrue(Continent.totalAmericaS(lands.getTERR(), p));
		assertFalse(Continent.totalAfrica(lands.getTERR(), p));
		assertFalse(Continent.totalAsia(lands.getTERR(), p));
		assertFalse(Continent.totalEuropa(lands.getTERR(), p));
		assertTrue(Continent.totalOceania(lands.getTERR(), p));
	}

}
