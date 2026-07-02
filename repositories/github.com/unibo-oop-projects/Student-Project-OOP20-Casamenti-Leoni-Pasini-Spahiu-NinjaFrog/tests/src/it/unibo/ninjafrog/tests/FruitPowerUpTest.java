package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import it.unibo.ninjafrog.fruits.FruitBuilderImpl;

/**
 * Automated tests for the FruitPowerUp: cherry, melon, orange. 
 */
@RunWith(GdxTestRunner.class)
public class FruitPowerUpTest {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets" + FILE_SEPARATOR;
	/**
	 * Test for the asset file like .png and .pack .
	 * Asset file includes cherry, melon and orange Texture.
	 */
	@Test
	public void assetExists() {
		assertTrue("This test will only pass if the png and tsx files of the asset exist in the assets folder.", 
				Gdx.files.internal(ASSETS_PATH + "ninjaAndEnemies.png").exists());
		assertTrue(Gdx.files.internal(ASSETS_PATH + "ninjaAndEnemies.pack").exists());
	}
	/**
	 * {@link it.unibo.ninjafrog.fruits.FruitBuilderImpl FruitBuilderImpl} test.
	 * Exception test of the builder fruit class. 
	 */
	@Test (expected = IllegalStateException.class)
	public void buildThrowsException() {
		FruitBuilderImpl.newBuilder()
				.chooseXPosition(0)
				.chooseYPosition(0)
				.selectScreen(null)
				.selectFruitType(null)
				.build();
	}
	
}
