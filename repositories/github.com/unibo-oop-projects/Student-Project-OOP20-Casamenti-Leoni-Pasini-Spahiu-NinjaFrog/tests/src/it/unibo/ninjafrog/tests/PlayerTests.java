package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.Gdx;

import it.unibo.ninjafrog.frog.FrogControllerImpl;
import it.unibo.ninjafrog.frog.FrogModelImpl;
import it.unibo.ninjafrog.frog.FrogViewImpl;

public class PlayerTests {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets" + FILE_SEPARATOR;
	
	public void FrogAssetsExists() {
		assertTrue("This test will only pass if the png file of the ninja frog exist in the assets folder.",
				Gdx.files.internal( ASSETS_PATH + "ninjaAndEnemies.png").exists());
	}
	@Test(expected = NullPointerException.class)
	public void FrogViewThrowsException() {
		new FrogViewImpl(null, null);
	}
	@Test(expected = NullPointerException.class)
	public void FrogControllerThrowsException() {
		new FrogControllerImpl(null);
	}
	@Test(expected = NullPointerException.class)
	public void FrogModelThrowException() {
		new FrogModelImpl(null);
	}
	

}
