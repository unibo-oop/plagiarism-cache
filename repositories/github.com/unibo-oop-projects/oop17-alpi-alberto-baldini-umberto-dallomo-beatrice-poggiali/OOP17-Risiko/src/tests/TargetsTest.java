package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lands.Lands;
import lands.LandsImpl;
import model.Continent;
import model.TargetsStaticControls;
import players.PlayerImpl;

class TargetsTest {
	
	private PlayerImpl p;
	private static Lands lands;
	
	TargetsTest() {
		lands = LandsImpl.getLandsImpl();
		p = new PlayerImpl("Alberto", 1);
		model.RandomicAssignmentCountry.RAC(lands.getTERR(), 6);

		for (int i = 13; i < 20; i++) {
			lands.getTERR().get(i).setOwner(1);
		}
		
		for (int i = 26; i < 42; i++) {
			lands.getTERR().get(i).setOwner(1);
		}
	}

	@Test
	void test() {
		assertTrue(TargetsStaticControls.As_Oc(lands.getTERR(), p));	
		assertTrue(TargetsStaticControls.Eu_Oc_smt(lands.getTERR(), p));
		assertFalse(TargetsStaticControls.As_Af(lands.getTERR(), p));
		assertFalse(TargetsStaticControls.AmN_Oc(lands.getTERR(), p));
	}

}
