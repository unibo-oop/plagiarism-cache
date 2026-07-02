package jAlienInvasion;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import controller.ScoreManager;
import controller.ScoreManagerImpl;
import model.PersonalScore;
import model.PersonalScoreImpl;


public class ScoreManagerTest {
	
	 @Test 
	public void  testScoreManagerTest() {
		ScoreManager sm = ScoreManagerImpl.getInstance();
		PersonalScore ps = new PersonalScoreImpl(1, "prova", 10, 100);
		sm.addPersonalScore(ps);
		ScoreManagerImpl.writeListToFile();
		sm.getScoreList().clear();
		assertEquals(sm.getScoreList().size(), 0);
		ScoreManagerImpl.getListFromFile();
		assertEquals(sm.getScoreList().size(), 1);
		assertEquals(ps.toString(), sm.getScoreList().get(0).toString());
	}
}
