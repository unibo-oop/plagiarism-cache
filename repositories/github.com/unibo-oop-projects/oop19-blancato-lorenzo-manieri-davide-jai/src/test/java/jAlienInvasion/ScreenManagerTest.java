package jAlienInvasion;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.junit.Test;
import controller.ScreenManager;

public class ScreenManagerTest {
	
	@Test
	public void testScreenManager() throws IOException {
		assertNotNull( ScreenManager.getImage("Player.png"));
	
	}
}
