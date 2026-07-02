package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import it.unibo.ninjafrog.screens.LevelsMenu;
import it.unibo.ninjafrog.screens.MainMenu;
import it.unibo.ninjafrog.screens.SettingsMenu;
import it.unibo.ninjafrog.screens.WinScreen;

/**
 * Automated tests for all menu in the game.
 * MenuTests includes WinScreen and GameOverScreen.
 */
@RunWith(GdxTestRunner.class)
public class MenuTests {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets" + FILE_SEPARATOR;
	/**
	 * Test for the MainMenu background files.
	 */
	@Test
	public void FirstMenuExists() {
		assertTrue("This test will only pass if the jpg/png files of the backgrounds exist in the assets folder.",
				Gdx.files.internal( ASSETS_PATH + "Menu1background.png").exists());
	}
	/**
	 * Test for the SecondaryMenu background files.
	 * Secondary menu includes SettingsMenu and LevelsMenu.
	 */
	@Test
	public void SecondaryMenuExists() {
		assertTrue("This test will only pass if the jpg/png files of the backgrounds exist in the assets folder.",
				Gdx.files.internal(ASSETS_PATH + "Menu2background.png").exists());
	}
	/**
	 * Tests for the WinScreen background files.
	 */
	@Test
	public void WinScreenExists() {
		assertTrue("This test will only pass if the jpg/png files of the backgrounds exist in the assets folder.",
				Gdx.files.internal(ASSETS_PATH + "Win_Screen.png").exists());
	}
	/**
	 * Tests for the GameOverScreen background files.
	 */
	@Test
	public void GameOverExists() {
		assertTrue("This test will only pass if the jpg/png files of the backgrounds exist in the assets folder.",
				Gdx.files.internal(ASSETS_PATH + "GameOver_Screen.png").exists());
	}
	/**
	 * {@link it.unibo.ninjafrog.screen.MainMenu MainMenu} test.
	 */
	@Test(expected = NullPointerException.class)
	public void MainMenuException() {
		new MainMenu(null, null);
	}
	/**
	 * {@link it.unibo.ninjafrog.screen.SettingsMenu SettingsMenu} test.
	 */
	@Test(expected = NullPointerException.class)
	public void SettingsMenuException() {
		new SettingsMenu(null, null);
	}
	/**
	 * {@link it.unibo.ninjafrog.screen.LevelsMenu LevelsMenu} test.
	 */
	@Test(expected = NullPointerException.class)
	public void LevelsMenuException () {
		new LevelsMenu(null, null);
	}
	/**
	 * {@link it.unibo.ninjafrog.screen.WinScreen WinScreen} test.
	 */
	@Test(expected = NullPointerException.class)
	public void WinScreenException() {
		new WinScreen(null, 0, null);
	}
	/**
	 * {@link it.unibo.ninjafrog.screen.GameOverScreen GameOverScreen} test.
	 */
	@Test(expected = NullPointerException.class)
	public void GameOverScreenException() {
		new WinScreen(null, 0, null);
	}
}
